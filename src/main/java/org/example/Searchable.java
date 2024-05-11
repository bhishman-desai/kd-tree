package org.example;

import java.util.Set;

public interface Searchable<T extends Comparable<T>> {
  public boolean build(Set<Point<T>> points);

  public boolean add(Point<T> point);

  public boolean find(Point<T> point);

  public int size();

  public Set<Point<T>> findInRange(Point<T> minCorner, Point<T> maxCorner);
}
