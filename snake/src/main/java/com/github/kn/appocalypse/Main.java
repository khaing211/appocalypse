package com.github.kn.appocalypse;

import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

public class Main {
  private static final int DEFAULT_WIDTH = 80;
  private static final int DEFAULT_HEIGHT = 50;
  private static final int DEFAULT_GAME_LOOP_IN_MILLIS = 500;

  @SneakyThrows
  public static void main(final String[] args) {
    GameIO.clear();
    GameIO.setup();
    final Game game = new DefaultGame(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_GAME_LOOP_IN_MILLIS);

    while (game.getState() == GameState.IN_PROGRESS) {
      game.update();
      GameIO.print(game);
      TimeUnit.MILLISECONDS.sleep(game.getCurrentGameLoopMillis());
    }

    System.out.println();
    switch (game.getState()) {
      case LOST: System.out.println("You lost"); break;
      case WON: System.out.println("You won"); break;
      default: break;
    }
  }
}
