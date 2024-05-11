package org.example;

public class Node<T extends Comparable<T>> {

  private Point<T> point;
  private Node<T> left;
  private Node<T> right;
  private int depth;

  /* Getters and Setters */
  protected Point<T> getPoint() {
    return point;
  }

  protected Node<T> getLeft() {
    return left;
  }

  protected Node<T> getRight() {
    return right;
  }

  protected int getDepth() {
    return depth;
  }

  protected void setLeft(Node<T> left) {
    this.left = left;
  }

  protected void setRight(Node<T> right) {
    this.right = right;
  }

  /** Create an empty node */
  protected Node() {
    this.point = null;
    this.left = null;
    this.right = null;
    this.depth = 0; /* Keeping depth 0 based */
  }

  /**
   * Create a Node with a given point / value
   *
   * @param point The value of the node
   */
  protected Node(Point<T> point, int depth) {
    this.point = point;
    this.left = null;
    this.right = null;
    this.depth = depth;
  }

  /**
   * Will add a Node to the left of the current node
   *
   * @param nodeToTheLeft The node which is to be added
   */
  protected void addToLeft(Node<T> nodeToTheLeft) {
    /* Checks if the node to be added have a value */
    if (!nodeToTheLeft.getPoint().getCoordinates().isEmpty()) {
      this.left = nodeToTheLeft;
    }
  }

  /**
   * Will add a Node to the right of the current node
   *
   * @param nodeToTheRight The node which is to be added
   */
  protected void addToRight(Node<T> nodeToTheRight) {
    /* Checks if the node to be added have a value */
    if (!nodeToTheRight.getPoint().getCoordinates().isEmpty()) {
      this.right = nodeToTheRight;
    }
  }

  /**
   * Checks if the current node is a leaf node
   *
   * @return true if it's a leaf node
   */
  protected boolean isLeaf() {
    return this.left == null && this.right == null;
  }
}
