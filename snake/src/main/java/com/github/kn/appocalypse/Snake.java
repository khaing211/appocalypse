package com.github.kn.appocalypse;

public interface Snake {
  public int getHeadIndex();

  public boolean isOccupied(int index);

  public void removeTail();

  public void addHead(int index);

  public Direction getCurrentDirection();

  public void setCurrentDirection(Direction direction);

  public int getSize();
}
