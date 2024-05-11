package org.example;

import java.util.List;
import java.util.ArrayList;

public class Point<T extends Comparable<T>> {
  private List<T> coordinates = new ArrayList<>();

  /**
   * Create a point from a given set of coordinates. The dimension of the point will be inferred
   * from the list
   *
   * @param source The input list
   */
  public Point(List<T> source) {
    coordinates = new ArrayList<T>(source);
  }

  /**
   * Clone an existing point
   *
   * @param copyFrom -- the point to copy
   */
  public Point(Point<T> copyFrom) {
    coordinates = new ArrayList<>(copyFrom.coordinates);
  }

  /**
   * Compare two points based on a single dimension of the point. Use the built-in comparator from
   * the coordinates to make the comparison.
   *
   * <p>For now, we're implementing without exceptions, so we'll arrange for the dimension to be
   * within the range of dimensions for the point. Not a great solution, but it keeps us relatively
   * safe. The return value is using the full integer range, so we don't have a means to signal an
   * error other than by the exception.
   *
   * @param compareWithMe -- the point to compare with
   * @param dimension -- the dimension across which to compare
   * @return Zero if the dimensions are the same, positive if this object is bigger than
   *     compareWithMe in the given dimension, and negative otherwise
   */
  public int compareTo(Point<T> compareWithMe, int dimension) {
    /* Without exceptions for now, we'll assume that the dimension is appropriate for the Point */

    int d = coordinates.size();
    return coordinates
        .get(dimension % d)
        .compareTo((T) compareWithMe.coordinates.get(dimension % d));
  }

  /**
   * Return the number of dimensions for which this point stores data
   *
   * @return the dimensions of the point.
   */
  public int dimension() {
    return coordinates.size();
  }

  /**
   * Provide all the coordinates of this point.
   *
   * @return the point's coordinates.
   */
  public List<T> getCoordinates() {
    return new ArrayList<>(coordinates);
  }

  /**
   * Gets the coordinates in a string format. Each coordinate is separated by a space
   *
   * @return string of coordinates
   */
  public String getCoordinatesAsString() {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < coordinates.size(); i++) {
      stringBuilder.append(coordinates.get(i));

      if (i < coordinates.size() - 1) {
        stringBuilder.append(" ");
      }
    }
    return stringBuilder.toString();
  }
}
