package org.example;

import java.util.Comparator;

/* Used as a custom Comparator when creating a TreeSet. This comparator will essentially define how TreeSet should order the Point object */
public class PointComparator<T extends Comparable<T>> implements Comparator<Point<T>> {
  /**
   * Custom Comparator
   *
   * @param pointOne the first object to be compared.
   * @param pointTwo the second object to be compared.
   * @return 1, -1, 0 based upon the comparison
   */
  @Override
  public int compare(Point<T> pointOne, Point<T> pointTwo) {
    /* Comparing the points on first axis - 0 as this would be used while we input the points, and so we always begin by splitting from the first axis */
    return pointOne.getCoordinates().get(0).compareTo(pointTwo.getCoordinates().get(0));
  }
}
