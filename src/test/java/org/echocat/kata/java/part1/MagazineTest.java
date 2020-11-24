package org.echocat.kata.java.part1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MagazineTest {

  @Test
  public void test_magazine_creation_from_line() {
    String[] lines = new String[]{"A", "B", "C,D", "E"};

    Magazine magazine = Magazine.fromLine(lines);

    assertEquals("A", magazine.getTitle());
    assertEquals("B", magazine.getIsbn());
    assertEquals("C", magazine.getAuthors().get(0));
    assertEquals("D", magazine.getAuthors().get(1));
    assertEquals("E", magazine.getPublishedAt());
  }
}