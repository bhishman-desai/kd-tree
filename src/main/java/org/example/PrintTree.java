package org.example;

/* This class handles the tree printing part */
public class PrintTree<T extends Comparable<T>> {

  /**
   * Print a tree in Breadth-First Fashion
   *
   * @param tree tree to be printed
   * @return String of the tree
   */
  protected String breadthFirstOrder(Node<T> tree) {

    if (tree == null) {
      return "";
    }

    StringBuilder treeString = new StringBuilder();
    int currentDepth = 0;

    while (true) {
      boolean isLevelNotEmpty = currentLevel(tree, currentDepth, treeString);

      /* If there are no nodes on the level, break from the loop */
      if (!isLevelNotEmpty) {
        break;
      }

      /* Move on to the next level */
      currentDepth++;
    }

    return treeString.toString();
  }

  /**
   * @param node The actual tree
   * @param currentDepth current depth
   * @param treeString string
   * @return true until there are nodes on each level
   */
  private boolean currentLevel(Node<T> node, int currentDepth, StringBuilder treeString) {
    if (node == null) {
      return false;
    }

    if (currentDepth == 0) {
      /* Once we reach the target depth, print the node */
      treeString
          .append(node.getPoint().getCoordinatesAsString())
          .append(" ")
          .append(node.getDepth() + 1)
          .append("\n");

      /* Iterate to the next level */
      return true;
    }

    /* Recurse and iterate through both left and right sub nodes */
    boolean isLeftNotEmpty = currentLevel(node.getLeft(), currentDepth - 1, treeString);
    boolean isRightNotEmpty = currentLevel(node.getRight(), currentDepth - 1, treeString);

    /* Repeat the process if any of the nodes is not empty */
    return isLeftNotEmpty || isRightNotEmpty;
  }

  /**
   * Print a tree in Depth-First Fashion
   *
   * @param tree The tree to be printed
   * @return String in depth-first order
   */
  protected String depthFirstOrder(Node<T> tree) {
    if (tree == null) {
      return "";
    }

    return tree.getPoint().getCoordinatesAsString()
        + " "
        + (tree.getDepth() + 1)
        + "\n"

        /* Recursively append the left and right subtrees */
        + depthFirstOrder(tree.getLeft())
        + depthFirstOrder(tree.getRight());
  }
}
