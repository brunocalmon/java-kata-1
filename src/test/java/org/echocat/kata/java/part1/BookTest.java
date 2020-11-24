package org.echocat.kata.java.part1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BookTest {

  @Test
  public void test_book_creation_from_line() {
    String[] lines = new String[]{"A", "B", "C,D", "E"};

    Book book = Book.fromLine(lines);
    Book.fromLine(lines);

    assertEquals("A", book.getTitle());
    assertEquals("B", book.getIsbn());
    assertEquals("C", book.getAuthors().get(0));
    assertEquals("D", book.getAuthors().get(1));
    assertEquals("E", book.getDescription());
  }
}