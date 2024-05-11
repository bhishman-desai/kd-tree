package org.example;

public interface TreeDebug<T extends Comparable<T>> extends Searchable<T> {
  public String printTree();
}
