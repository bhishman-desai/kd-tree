package org.example;

/* This class handles the finding a node and adding a node to the tree part */
public class FindAdd<T extends Comparable<T>> {

  /**
   * Add a node to tree
   *
   * @param point The point (value of node) which is to be added
   * @param tree Tree to which point is to be added
   * @param currentDepth The current depth
   * @return returns true if node was added successfully
   */
  protected boolean addNodeInTree(Point<T> point, Node<T> tree, int currentDepth) {

    /* Checks if the point is smaller than the current node with current splitting axis */
    if (point.compareTo(tree.getPoint(), currentDepth) < 0) {
      /* If the left node is null, create a node with depth + 1 and add it to the left of the current node */
      if (tree.getLeft() == null) {
        Node<T> newNode = new Node<>(point, currentDepth + 1);
        tree.setLeft(newNode);
      } else {
        /* If not, then continue the same process on the next level that is depth + 1 */
        addNodeInTree(point, tree.getLeft(), currentDepth + 1);
      }
    }
    /* Checks if the point is larger than the current node with current splitting axis */
    if (point.compareTo(tree.getPoint(), currentDepth) > 0) {
      if (tree.getRight() == null) {
        Node<T> newNode = new Node<>(point, currentDepth + 1);
        tree.setRight(newNode);
      } else {
        addNodeInTree(point, tree.getRight(), currentDepth + 1);
      }
    }

    return true;
  }

  /**
   * Search a node in tree
   *
   * @param point Point which is to be searched
   * @param tree Tree, in which the node is to be searched
   * @param currentDepth The current depth
   * @return true is node is found
   */
  protected boolean searchNodeInTree(Point<T> point, Node<T> tree, int currentDepth) {
    if (tree == null) {
      return false;
    }
    if (point.getCoordinates().equals(tree.getPoint().getCoordinates())) {
      return true;
    }
    if (point.compareTo(tree.getPoint(), currentDepth) < 0) {
      return searchNodeInTree(point, tree.getLeft(), currentDepth + 1);
    }
    if (point.compareTo(tree.getPoint(), currentDepth) > 0) {
      return searchNodeInTree(point, tree.getRight(), currentDepth + 1);
    }

    return false;
  }

  /**
   * Searches if a single coordinate exists in the tree
   *
   * @param coordinate coordinate which is to be searched
   * @param tree tree in which coordinate is to be searched
   * @param index the axis
   * @return true if coordinate is found on the provided axis (index)
   */
  protected boolean searchCoordinateInTree(T coordinate, Node<T> tree, int index) {
    if (tree == null) {
      return false;
    }

    if (tree.getPoint().getCoordinates().get(index).equals(coordinate)) {
      return true;
    }

    return searchCoordinateInTree(coordinate, tree.getLeft(), index)
        || searchCoordinateInTree(coordinate, tree.getRight(), index);
  }
}
