package org.example;

import java.util.HashSet;
import java.util.Set;

/* This class handles the Find points in range part */
public class FindInRange<T extends Comparable<T>> {
  /**
   * Method to find points in given range
   * @param tree Tree in which points are to be found
   * @param minCorner min corner of the rectangle
   * @param maxCorner max corner of the rectangle
   * @param currentDepth the current depth
   * @param outputSet set of the points which are to be returned
   * @return Set of points in range
   */
  protected Set<Point<T>> findInLimit(
      Node<T> tree,
      Point<T> minCorner,
      Point<T> maxCorner,
      int currentDepth,
      Set<Point<T>> outputSet) {
    if (tree == null) {
      return new HashSet<>();
    }

    /* Get the current splitting axis */
    int currentAxis = currentDepth % minCorner.dimension();

    /* Whether the current node is in the given range and if so then add it to the output Set */
    boolean isInRange = true;
    for (int i = 0; i < minCorner.dimension(); i++) {
      if (tree.getPoint().getCoordinates().get(i).compareTo(minCorner.getCoordinates().get(i)) < 0
          || tree.getPoint().getCoordinates().get(i).compareTo(maxCorner.getCoordinates().get(i))
              > 0) {
        isInRange = false;
        break;
      }
    }

    if (isInRange) {
      outputSet.add(tree.getPoint());
    }

    /* Checking with recursive approach on either side of the tree,
     *  This is to basically know on which side of the tree should we move further with respect to the current splitting axis
     * */
    if (tree.getPoint()
            .getCoordinates()
            .get(currentAxis)
            .compareTo(minCorner.getCoordinates().get(currentAxis))
        >= 0) {
      /* Move left of the current node if the current node is greater than or equal to minCorner with respect to the current splitting axis */
      findInLimit(tree.getLeft(), minCorner, maxCorner, currentDepth + 1, outputSet);
    }
    if (tree.getPoint()
            .getCoordinates()
            .get(currentAxis)
            .compareTo(maxCorner.getCoordinates().get(currentAxis))
        <= 0) {
      /* Move right of the current node if the current node is less than or equal to maxCorner with respect to the current splitting axis */
      findInLimit(tree.getRight(), minCorner, maxCorner, currentDepth + 1, outputSet);
    }
    return outputSet;
  }
}
