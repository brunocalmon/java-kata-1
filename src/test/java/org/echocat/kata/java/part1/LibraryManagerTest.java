package org.echocat.kata.java.part1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagerTest {

  LibraryManager manager;

  @Before
  public void setUp() {
    manager = new LibraryManager(
        LibraryManager.readMagazine(Magazine::fromLine),
        LibraryManager.readBook(Book::fromLine),
        LibraryManager.readAuthor(Author::fromLine));
  }

  @Test
  public void getByISBN() {
    String isbn = "5554-5545-4518";

    Publication pubByISBN = manager.getByISBN(isbn).get();

    assertEquals(isbn, pubByISBN.getIsbn());
  }

  @Test
  public void getByAuthor() {
    String author = "null-mueller@echocat.org";

    List<Publication> pubsByAuthor = manager.getByAuthor(author).get();

    assertTrue(pubsByAuthor.get(0).getAuthors().contains(author));
    assertTrue(pubsByAuthor.get(1).getAuthors().contains(author));
  }
}