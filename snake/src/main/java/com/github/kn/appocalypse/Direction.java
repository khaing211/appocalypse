package com.github.kn.appocalypse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Direction {
  UP('^'),
  DOWN('v'),
  LEFT('<'),
  RIGHT('>'),
  ;

  private final char symbol;

  public boolean isOpposite(final Direction direction) {
    switch (this) {
      case UP: return Direction.DOWN == direction;
      case DOWN: return Direction.UP == direction;
      case LEFT: return Direction.RIGHT == direction;
      case RIGHT: return Direction.LEFT == direction;
    }
    return false;
  }
}
