package com.github.kn.appocalypse;

public interface Game {
  /**
   * @return millis delay between loops
   */
  public long getCurrentGameLoopMillis();

  /**
   * @return width of screen
   */
  public int getWidth();

  /**
   * @return height of screen
   */
  public int getHeight();

  /**
   * @return char at (r, c) of the current game loop
   */
  public char getChar(int r, int c);

  /**
   * Call per game loop to update game process
   */
  public void update();

  /**
   * @return current game state
   */
  public GameState getState();
}
