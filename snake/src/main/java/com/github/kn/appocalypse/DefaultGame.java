package com.github.kn.appocalypse;

import java.util.Optional;
import java.util.Random;

public class DefaultGame implements Game {
  private static final long MIN_GAME_LOOP_IN_MILLIS = 100;
  private static final char BOUNDARY_CHAR = '#';
  private static final char EMPTY_SPACE_CHAR = ' ';
  private static final char FOOD_CHAR = '+';
  private static final char SNAKE_CHAR = '*';

  private final Random random = new Random(System.currentTimeMillis());
  private final int width;
  private final int height;
  private final int size;
  private final int maxSnakeSize;
  private final Snake snake;

  private long currentGameLoopMillis;
  private int currentFoodIndex;;
  private GameState state;

  public DefaultGame(final int width, final int height, final long currentGameLoopMillis) {
    this.width = width;
    this.height = height;
    this.size = width * height;
    this.maxSnakeSize = size - 2*width - 2*height + 4;
    this.snake = new DefaultSnake(new int[] { toIndex(height/2, width / 2), toIndex(height / 2 + 1, width/2) });
    this.currentGameLoopMillis = currentGameLoopMillis;
    this.state = GameState.IN_PROGRESS;
    generateFoodIndex();
  }

  @Override
  public long getCurrentGameLoopMillis() {
    return currentGameLoopMillis;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public GameState getState() {
    return state;
  }

  @Override
  public char getChar(final int r, final int c) {
    if (r == 0 || c == 0 || r == height-1 || c == width-1) {
      return BOUNDARY_CHAR;
    }

    final int index = toIndex(r, c);
    if (index == currentFoodIndex) {
      return FOOD_CHAR;
    }

    if (index == snake.getHeadIndex()) {
      return snake.getCurrentDirection().getSymbol();
    }

    if (snake.isOccupied(index)) {
      return SNAKE_CHAR;
    }

    return EMPTY_SPACE_CHAR;
  }

  @Override
  public void update() {
    state = GameState.IN_PROGRESS;

    final Direction currentSnakeDirection = snake.getCurrentDirection();
    final Direction newSnakeDirection = toDirection(GameIO.getCurrentKey())
        .orElse(currentSnakeDirection);

    // only update if new direction is not opposite of current direction
    if (!currentSnakeDirection.isOpposite(newSnakeDirection)) {
      snake.setCurrentDirection(newSnakeDirection);
    }

    final Direction snakeDirection = snake.getCurrentDirection();
    final int currentSnakeHeadIndex = snake.getHeadIndex();
    final int newSnakeHeadIndex = computeNewIndexForDirection(currentSnakeHeadIndex, snakeDirection);

    if (isOccupied(newSnakeHeadIndex, false)) {
      state = GameState.LOST;
      return;
    }

    snake.addHead(newSnakeHeadIndex);

    if (newSnakeHeadIndex == currentFoodIndex) {
      if (snake.getSize() == maxSnakeSize) {
        state = GameState.WON;
        return;
      } else {
        // grow by not removing tail
        generateFoodIndex();

        // speed up the game
        currentGameLoopMillis = Math.min(MIN_GAME_LOOP_IN_MILLIS, currentGameLoopMillis-1);
      }
    } else {
      snake.removeTail();
    }
  }

  private Optional<Direction> toDirection(final int charCode) {
    // Hax!! KeyEvent does not seem to work
    switch (charCode) {
      case 65:
        return Optional.of(Direction.UP);
      case 66:
        return Optional.of(Direction.DOWN);
      case 68:
        return Optional.of(Direction.LEFT);
      case 67:
        return Optional.of(Direction.RIGHT);
      default:
        return Optional.empty();
    }
  }

  private void generateFoodIndex() {
    currentFoodIndex = -1;
    while (currentFoodIndex == -1) {
      final int index = random.nextInt(size);

      if (!isOccupied(index, true)) {
        currentFoodIndex = index;
        return;
      }
    }
  }

  private int computeNewIndexForDirection(final int index, final Direction direction) {
    int r = toR(index);
    int c = toC(index);
    switch (direction) {
      case UP: r--; break;
      case DOWN: r++; break;
      case LEFT: c--; break;
      case RIGHT: c++; break;
    }

    return toIndex(r, c);
  }

  private boolean isOccupied(final int index, final boolean snakeIncluded) {
    final int r = toR(index);
    final int c = toC(index);
    return r <= 0 || c <= 0 || r >= height-1 || c >= width-1 || (snakeIncluded && snake.isOccupied(index));
  }

  private int toIndex(final int r, final int c) {
    return r*width + c;
  }

  private int toR(final int index) {
    return index / getWidth();
  }

  private int toC(final int index) {
    return index % getWidth();
  }
}
