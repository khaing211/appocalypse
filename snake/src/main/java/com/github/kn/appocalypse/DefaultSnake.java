package com.github.kn.appocalypse;

import java.util.LinkedList;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class DefaultSnake implements Snake {
  // Using List to keep order of insertion
  private final LinkedList<Integer> snakeIndices = Lists.newLinkedList();

  // Using Set for query isOccupied
  private final Set<Integer> snakeHash = Sets.newHashSet();

  // Direction defaults to up
  private Direction direction = Direction.UP;

  public DefaultSnake(final int[] initialLocations) {
    for (int i = initialLocations.length-1; i >= 0 ; i--) {
      addHead(initialLocations[i]);
    }
  }

  @Override
  public int getHeadIndex() {
    return snakeIndices.getFirst();
  }

  @Override
  public boolean isOccupied(final int index) {
    return snakeHash.contains(index);
  }

  @Override
  public void removeTail() {
    snakeHash.remove(snakeIndices.removeLast());
  }

  @Override
  public void addHead(final int index) {
    snakeIndices.addFirst(index);
    snakeHash.add(index);
  }

  @Override
  public Direction getCurrentDirection() {
    return direction;
  }

  @Override
  public int getSize() {
    return snakeHash.size();
  }

  @Override
  public void setCurrentDirection(final Direction direction) {
    this.direction = direction;
  }
}
