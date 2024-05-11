package org.example;

import java.util.*;

public class Main {
  public static void main(String[] args) {

    List<Point<Integer>> inputPointLists = new ArrayList<>();

    inputPointLists.add(new Point<>(Arrays.asList(-3, 2)));
    inputPointLists.add(new Point<>(Arrays.asList(-5, 4)));
    inputPointLists.add(new Point<>(Arrays.asList(0, 0)));
    inputPointLists.add(new Point<>(Arrays.asList(9, -2)));
    inputPointLists.add(new Point<>(Arrays.asList(7, -5)));

    /* This will be the given set. It could be of any dimensions */
    Set<Point<Integer>> inputSet = new TreeSet<>(new PointComparator<>());
    inputSet.addAll(inputPointLists);

    /* Creating a kdTree of two dimensions */
    kdTree<Integer> kdTreeObject = new kdTree<>(2);

    /* Build method implementation */
    System.out.println("Was the kdTree built successfully?");
    System.out.println(kdTreeObject.build(inputSet));

    /* Add method implementation */
    System.out.println("Was the node added successfully?");
    System.out.println(kdTreeObject.add(new Point<>(Arrays.asList(1, 22))));
    System.out.println("Was the node added successfully?");
    System.out.println(kdTreeObject.add(new Point<>(Arrays.asList(11, 6))));

    /* Find in range method implementation */
    System.out.println("Points between minCorner(-5,-5) and maxCorner(11, 22):");
    for (Point<Integer> point :
        kdTreeObject.findInRange(
            new Point<>(Arrays.asList(-5, -5)), new Point<>(Arrays.asList(11, 22)))) {
      System.out.println(point.getCoordinates());
    }

    /* Print method implementation */
    System.out.println("kdTree Print:");
    System.out.println(kdTreeObject.printTree());

    /* Find method implementation */
    System.out.println("Is (0,0) in the kdTree?");
    System.out.println(kdTreeObject.find(new Point<>(Arrays.asList(0, 0))));

    /* Size method implementation */
    System.out.println("Size of the kdTree:");
    System.out.println(kdTreeObject.size());

    /* 3-dimensional */

    /*List<Point<Integer>> inputPointLists1 = new ArrayList<>();
    inputPointLists1.add(new Point<>(Arrays.asList(3, 1, 5)));
    inputPointLists1.add(new Point<>(Arrays.asList(1, 7, 10)));
    inputPointLists1.add(new Point<>(Arrays.asList(11, 2, 9)));
    inputPointLists1.add(new Point<>(Arrays.asList(7, 3, 0)));
    inputPointLists1.add(new Point<>(Arrays.asList(0, 0, 0)));
    inputPointLists1.add(new Point<>(Arrays.asList(2, 11, 55)));
    inputPointLists1.add(new Point<>(Arrays.asList(9, 10, 99)));
    inputPointLists1.add(new Point<>(Arrays.asList(12, 13, 19)));

    Set<Point<Integer>> inputSet1 = new TreeSet<>(new PointComparator<>());
    inputSet1.addAll(inputPointLists1);

    kdTree<Integer> kdTreeObject1 = new kdTree<>(3);
    kdTreeObject1.build(inputSet1);

    System.out.println(kdTreeObject1.printTree());*/

    /* String type */
    /*List<Point<String>> inputPointLists2 = new ArrayList<>();
    inputPointLists2.add(new Point<>(Arrays.asList("a", "b")));
    inputPointLists2.add(new Point<>(Arrays.asList("z", "x")));
    inputPointLists2.add(new Point<>(Arrays.asList("d", "f")));
    inputPointLists2.add(new Point<>(Arrays.asList("s", "z")));
    inputPointLists2.add(new Point<>(Arrays.asList("x", "f")));
    inputPointLists2.add(new Point<>(Arrays.asList("g", "c")));
    inputPointLists2.add(new Point<>(Arrays.asList("v", "p")));

    Set<Point<String>> inputSet2 = new TreeSet<>(new PointComparator<>());
    inputSet2.addAll(inputPointLists2);

    kdTree<String> kdTreeObject2 = new kdTree<>(2);
    kdTreeObject2.build(inputSet2);

    System.out.println(kdTreeObject2.printTree());*/
  }
}
