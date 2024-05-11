package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class kdTreeTest {
  private static final int TestDimension = 2;
  @Test
  void exampleFromAssignment() {
    TreeDebug tree = new kdTree(TestDimension );
    List<Point> treePoints = new ArrayList<>();
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(3, 4)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(7, 1)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(1, 15)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(6, 13)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(9, 7)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(15, 3)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 11)) ) );
    treePoints.add( new Point( new ArrayList<>(Arrays.asList(14, 12)) ) );

    for (int i = 0; i < treePoints.size(); i++) {
      assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
    }
    assertEquals( "8 8 1\n5 6 2\n12 9 2\n4 2 3\n2 10 3\n13 5 3\n11 14 3\n3 4 4\n7 1 4\n1 15 4\n6 13 4\n9 7 4\n15 3 4\n10 11 4\n14 12 4\n", tree.printTree( ), "print the default tree...is the error in the print or in the add?" );
  }
}
