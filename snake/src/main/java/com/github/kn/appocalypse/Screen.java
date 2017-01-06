package com.github.kn.appocalypse;

public class Screen {
  public static void print(final char[][] board) {
    for (int i = 0; i < board.length; i++) {
      System.out.print("\033[1A"); // Move up
      System.out.print("\033[2K"); // Erase line content
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }
}
