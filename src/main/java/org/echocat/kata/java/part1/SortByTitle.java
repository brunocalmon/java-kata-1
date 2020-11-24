package org.echocat.kata.java.part1;

import java.util.Comparator;

public class SortByTitle implements Comparator<Publication> {

  public int compare(Publication a, Publication b) {
    return a.getTitle().compareTo(b.getTitle());
  }
}
