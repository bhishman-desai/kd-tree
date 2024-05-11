/* Based upon the feedback from assignment 1, using classes for much more than just as structures */
package org.example;

import java.util.Arrays;
import java.util.Comparator;

/* This class just handles the tree building part */
public class TreeBuilding<T extends Comparable<T>> {

  /**
   * Method will build a tree from the given points
   *
   * @param pointsArray Array of Type Point consisting all the input points
   * @param lowerIndex Lower index of the array
   * @param upperIndex upper index of the array
   * @param currentDepth defines the level at which the node is
   * @return A balanced kdTree
   */
  protected Node<T> buildTree(
      Point<T>[] pointsArray, int lowerIndex, int upperIndex, int currentDepth) {

    /* - Checks if the lower index exceeded upper index
     * - Can be used as a terminating condition, as once this condition is true, it signifies that no node can be added on either side of the current node.
     * */
    if (lowerIndex > upperIndex) {
      return null;
    }

    /* This is to find out the current axis.
    Since all points in pointsArray would have the same dimension, we can get dimension of a first element, and it would be the same for others.
    This would be safe to use as the size of the pointsArray stays consistent */
    int currentAxis = getCurrentSplittingAxis(currentDepth, pointsArray[0].dimension());

    /*  - A Comparator comparing the co-ordinates for each point in the array
     *  - Basically used for sorting purposes
     * */
    Comparator<Point<T>> sortComparator =
        Comparator.comparing(p -> p.getCoordinates().get(currentAxis));

    /*  - Creating a copy of the original array as during recursion, sorting seemed to work only the first time but not subsequent times
     *  - Partitioning the array here itself instead of passing the lower (from index) and higher (to index) in Arrays.sort
     * */
    Point<T>[] sortedPoints = Arrays.copyOfRange(pointsArray, lowerIndex, upperIndex + 1);

    Arrays.sort(sortedPoints, sortComparator);

    /* This condition would make sure that there are elements to copy and that the original array is not empty */
    if (upperIndex + 1 - lowerIndex >= 0) {
      /*  - Copying the sorted subarray back to the original array to get it sorted
       *  - This is because we need to calculate the median on the basis of sorted array
       *  */
      System.arraycopy(sortedPoints, 0, pointsArray, lowerIndex, upperIndex + 1 - lowerIndex);
    }

    /* Median Calculation */
    int medianIndex;
    /* This is when the number of elements in an array are even */
    if ((upperIndex - lowerIndex) % 2 == 1) {
      /* subtracting 1 because we are asked to consider the smaller of the two median values */
      medianIndex = lowerIndex + (upperIndex - lowerIndex - 1) / 2;
    } else {
      /* When the number of elements in an array is odd */
      medianIndex = lowerIndex + (upperIndex - lowerIndex) / 2;
    }

    /* Creating the new node with given point constructor */
    Node<T> newNode = new Node<>(pointsArray[medianIndex], currentDepth);

    /* Shifting the upper index to the left and calling the function again */
    newNode.setLeft(buildTree(pointsArray, lowerIndex, medianIndex - 1, currentDepth + 1));
    /* Shifting the lower index to the right and calling the function again */
    newNode.setRight(buildTree(pointsArray, medianIndex + 1, upperIndex, currentDepth + 1));

    return newNode;
  }

  /* Helper / Utility Functions */

  /**
   * Gets the current splitting axis
   *
   * @param depth the current depth or level
   * @param dimension the total dimension or number of elements in Point
   * @return the current splitting axis
   */
  protected int getCurrentSplittingAxis(int depth, int dimension) {
    return depth % dimension;
  }
}
