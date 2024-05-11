package org.example;

import java.util.HashSet;
import java.util.Set;

public class kdTree<T extends Comparable<T>> implements Searchable<T>, TreeDebug<T> {

  private Node<T> tree;
  private int dimensions;

  /* Getters and Setters */
  public Node<T> getTree() {
    return tree;
  }

  protected int getDimensions() {
    return dimensions;
  }

  /* Creating an empty tree with provided dimensions */
  public kdTree(int dimensions) {
    this.tree = null;
    this.dimensions = dimensions;
  }

  /* Interface methods Implementation */

  /**
   * - Create the kdTree with the given points and parameter - tree should be balanced
   *
   * @param points The points from which the tree is to be built
   * @return true if the tree is successfully built
   */
  @Override
  public boolean build(Set<Point<T>> points) {
    try {
      if (points == null || points.isEmpty()) {
        return false;
      }

      TreeBuilding<T> treeBuilding = new TreeBuilding<>();
      /*  - Creating an array of generic type
       *  - Java's array do not work well with generic types
       *  - So, suppressing the below warning
       *  */
      @SuppressWarnings("unchecked")
      Point<T>[] pointsArray = new Point[points.size()];

      /* Building Array (of type Point) out of the given points */
      int i = 0;
      for (Point<T> point : points) {
        if (point.dimension() == this.dimensions) {
          pointsArray[i] = point;
          i += 1;
        } else {
          /* If the dimensions in input set are more than dimensions defined in kdTree's constructor */
          return false;
        }
      }

      /* - Used recursion for building the array
       *  - lowerIndex and upperIndex are used for recursion
       *  - considered the first level or the depth to be zero
       *  - starting lowerIndex from 0 and upperIndex is pointsArray.length - 1 because we are accessing the array elements through indices in the method
       * */
      if (tree != null) {
        Node<T> copyTree = treeBuilding.buildTree(pointsArray, 0, pointsArray.length - 1, 0);
        if (compareTree(tree, copyTree)) {
          return false;
        }
      }
      tree = treeBuilding.buildTree(pointsArray, 0, pointsArray.length - 1, 0);
      return true;

    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * Adds the given point to the kdTree. It can make the tree unbalanced.
   *
   * @param point The point which needs to be added in the kdTree
   * @return true if the point was added else false
   */
  @Override
  public boolean add(Point<T> point) {
    try {
      if (point.getCoordinates().isEmpty()) {
        return false;
      }

      /* Checking if provided dimensions the same as of kdTree's dimension */
      if(point.dimension() != dimensions){
        return false;
      }

      if (tree == null) {
        /* Add the point as root node if the tree is empty */
        this.tree = new Node<>(point, 0);
        return true;
      } else {
        FindAdd<T> findAdd = new FindAdd<>();

        /* Checks if the point to be added is already in the tree */
        int index = 0;
        for (T p : point.getCoordinates()) {
          if (findAdd.searchCoordinateInTree(p, this.tree, index)) {
            return false;
          }
          index += 1;
        }

        /* Double check if the same point in the tree already exists and if not then add the given point */
        return !findAdd.searchNodeInTree(point, this.tree, 0)
            && findAdd.addNodeInTree(point, this.tree, 0);
      }

    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * Used to find a point in the tree
   *
   * @param point Point which is to be searched
   * @return true if point was found, else false
   */
  @Override
  public boolean find(Point<T> point) {
    try {
      if (point.getCoordinates().isEmpty() || this.tree == null) {
        return false;
      }

      FindAdd<T> findAdd = new FindAdd<>();

      return findAdd.searchNodeInTree(point, this.tree, 0);

    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * @return The size of the kd tree
   */
  @Override
  public int size() {
    return length(this.tree);
  }

  /**
   * List of all points currently stored in the tree between the range
   *
   * @param minCorner min point of the diagonal
   * @param maxCorner max point of the diagonal
   * @return set of all points currently stored in the tree that appear inside the rectangle whose
   *     diagonal is defined by minCorner to maxCorner
   */
  @Override
  public Set<Point<T>> findInRange(Point<T> minCorner, Point<T> maxCorner) {
    try {
      if (minCorner == null
          || maxCorner == null
          || this.tree == null
          || minCorner.getCoordinates().size() != maxCorner.getCoordinates().size()) {
        return null;
      }

      FindInRange<T> findInRange = new FindInRange<>();
      Set<Point<T>> outputSet = new HashSet<>();

      return findInRange.findInLimit(this.tree, minCorner, maxCorner, 0, outputSet);

    } catch (Exception exception) {
      return null;
    }
  }

  /**
   * @return The string with the coordinates (current node) and respective depth/level
   */
  @Override
  public String printTree() {
    if (this.tree == null) {
      return null;
    }
    PrintTree<T> printTree = new PrintTree<T>();

    return printTree.breadthFirstOrder(this.tree);
  }

  /* Helper / Utility Functions */

  /**
   * Get the length of kdTree. Basically the number of nodes in the tree
   *
   * @param tree Tree, for which the length is to be calculated
   * @return size of the tree
   */
  private int length(Node<T> tree) {
    if (tree == null) {
      return 0;
    }

    /*  - Using recursion yet again to get the size of the kdTree
     *  - We keep on adding 1 on each recurring
     *  - And when the value on either side (right or left) is null, just add a 0
     *  */
    return length(tree.getLeft()) + length(tree.getRight()) + 1;
  }

  /**
   * Compares the two trees
   *
   * @param originalTree First tree or original Tree
   * @param copyTree The Second tree or the tree, which is to be compared with an original tree
   * @return true if both the trees are same
   */
  private boolean compareTree(Node<T> originalTree, Node<T> copyTree) {
    /* If both reaches the null, they are the same */
    if (originalTree == null && copyTree == null) {
      return true;
    }

    /* If one reaches null and the other doesn't, they are not the same */
    if (originalTree == null || copyTree == null) {
      return false;
    }

    /* If the values or coordinates in this case are not the same, they are not the same */
    if (!originalTree.getPoint().getCoordinates().equals(copyTree.getPoint().getCoordinates())) {
      return false;
    }

    boolean leftMatch = compareTree(originalTree.getLeft(), copyTree.getLeft());
    boolean rightMatch = compareTree(originalTree.getRight(), copyTree.getRight());

    return leftMatch && rightMatch;
  }
}
