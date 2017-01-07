package com.github.kn.appocalypse;

import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * {@link GameIO} is a singleton global-scoped class dealing with all IO of the game
 */
@NoArgsConstructor
public class GameIO {
  private static volatile int currentKey;

  public static void log(final String message) {
    // Use stderr, because stdin/stdout are occupied
    System.err.println(message);
  }

  @SneakyThrows
  public static void clear() {
    Preconditions.checkArgument(0 == new ProcessBuilder("clear").start().waitFor(), "clear failed");
  }

  public static void setup() {
    final String ttyConfig = stty(ImmutableList.of("-g"));
    Runtime.getRuntime().addShutdownHook(new Thread(() -> GameIO.restore(ttyConfig)));
    stty(ImmutableList.of("-icanon", "min", "1"));
    stty(ImmutableList.of("-echo"));

    final Thread readInputThread = new Thread(GameIO::readInput);
    readInputThread.setName("Read-Input-Thread");
    readInputThread.setDaemon(true);
    readInputThread.start();
    Runtime.getRuntime().addShutdownHook(new Thread(readInputThread::interrupt));
  }

  public static void print(final Game game) {
    for (int i = 0; i < game.getHeight(); i++) {
      System.out.print("\033[1A"); // Move up
      System.out.print("\033[2K"); // Erase line content
    }

    for (int r = 0; r < game.getHeight(); r++) {
      for (int c = 0; c < game.getWidth(); c++) {
        System.out.print(game.getChar(r, c));
      }
      System.out.println();
    }
  }

  public static int getCurrentKey() {
    return currentKey;
  }

  @SneakyThrows
  private static void readInput() {
    while (!Thread.interrupted()) {
      if (System.in.available() != 0) {
        currentKey = System.in.read();
      }
    }
  }

  private static void restore(final String ttyConfig) {
    stty(ImmutableList.of(ttyConfig));
  }

  @SneakyThrows
  private static String stty(final ImmutableList<String> args) {
    final Path output = Files.createTempFile("ttyConfig", ".txt");
    final ImmutableList<String> cli = ImmutableList.<String>builder()
        .add("stty")
        .addAll(args)
        .build();

    final int status = new ProcessBuilder(cli)
        .redirectOutput(Redirect.to(output.toFile()))
        .redirectInput(Paths.get("/dev/tty").toFile())
        .redirectError(Redirect.INHERIT)
        .start()
        .waitFor();

    Preconditions.checkArgument(status == 0, "CLI failed " + cli);

    return String.join("\n", Files.readAllLines(output));
  }
}
