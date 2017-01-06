package com.github.kn.appocalypse;

import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

public class Snake {
  private static volatile int keyRead = 0;

  @SneakyThrows
  public static void main(final String[] args) {
    Terminal.clear();

    Terminal.setup();

    final Thread readInputThread = new Thread(Snake::readInput);
    readInputThread.start();

    // Example: of using Screen, cycling through grid: 9x9 and print x moving
    final char[][] board = new char[9][9];
    for (int i = 0; i < 10000; i++) {
      if (i != 0) {
        final int j = i-1;
        board[(j/9)%9][j%9] = ' ';
      }
      board[(i/9)%9][i%9] = (char)('a' + keyRead%26);
      Screen.print(board);
      TimeUnit.MILLISECONDS.sleep(100);
    }

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        readInputThread.interrupt();
      }
    });
  }

  @SneakyThrows
  private static void readInput() {
    while (!Thread.interrupted()) {
      if (System.in.available() != 0) {
        keyRead = System.in.read();
      }
    }
  }

  public static void initialize(final char[][] board) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = ' ';
      }
    }
  }
}
