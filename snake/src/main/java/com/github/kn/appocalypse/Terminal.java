package com.github.kn.appocalypse;

import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
public class Terminal {
  @SneakyThrows
  public static void clear() {
    Preconditions.checkArgument(0 == new ProcessBuilder("clear").start().waitFor(), "clear failed");
  }

  public static void setup() {
    final String ttyConfig = stty(ImmutableList.of("-g"));
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        Terminal.restore(ttyConfig);
      }
    });
    stty(ImmutableList.of("-icanon", "min", "1"));
    stty(ImmutableList.of("-echo"));
  }

  public static void restore(final String ttyConfig) {
    stty(ImmutableList.of(ttyConfig));
  }

  @SneakyThrows
  private static String stty(final ImmutableList<String> args) {
    final Path output = Files.createTempFile("ttyConfig", ".txt");
    final ImmutableList<String> cli = ImmutableList.<String>builder()
        .add("stty")
        .addAll(args)
        .build();

    Preconditions.checkArgument(0 == new ProcessBuilder(cli)
        .redirectOutput(Redirect.to(output.toFile()))
        .redirectInput(Paths.get("/dev/tty").toFile())
        .redirectError(Redirect.INHERIT)
        .start()
        .waitFor(), "CLI failed " + cli);

    return String.join("\n", Files.readAllLines(output));
  }
}
