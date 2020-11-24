package org.echocat.kata.java.part1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuthorTest {

  @Test
  public void test_author_creation_from_csv_line() {
    String[] lines = new String[]{"A", "B", "C"};

    Author author = Author.fromLine(lines);
    Author.fromLine(lines);

    assertEquals("A", author.getEmail());
    assertEquals("B", author.getFirstname());
    assertEquals("C", author.getLastname());
  }
}