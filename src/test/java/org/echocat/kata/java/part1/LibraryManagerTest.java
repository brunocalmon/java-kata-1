package org.echocat.kata.java.part1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
  public void test_getting_by_isbn() {
    String isbn = "5554-5545-4518";

    Publication pubByISBN = manager.getByISBN(isbn).get();

    assertEquals(isbn, pubByISBN.getIsbn());
  }

  @Test(expected = RuntimeException.class)
  public void test_getting_by_isbn_not_found() {
    String isbn = "1111-1111-1111";

    manager.getByISBN(isbn).orElseThrow(() -> new RuntimeException("Not Found"));

    fail();
  }

  @Test
  public void test_getting_by_author() {
    String author = "null-mueller@echocat.org";

    List<Publication> pubsByAuthor = manager.getByAuthor(author).get();

    assertTrue(pubsByAuthor.get(0).getAuthors().contains(author));
    assertTrue(pubsByAuthor.get(1).getAuthors().contains(author));
  }

  @Test(expected = RuntimeException.class)
  public void test_getting_by_author_not_found() {
    String author = "pink-unicorn@mail.com";

    manager.getByAuthor(author).orElseThrow(() -> new RuntimeException("Not Found"));

    fail();
  }

  @Test
  public void test_read_author_from_csv() {
    String authorLastName = "Müller";

    List<Author> authors = LibraryManager.readAuthor(Author::fromLine);
    String gotLastName = authors.stream()
        .filter(a -> a.getLastname().equals(authorLastName))
        .findFirst().get().getLastname();

    assertTrue(!authors.isEmpty());
    assertEquals(authorLastName, gotLastName);
  }

  @Test
  public void test_read_book_from_csv() {
    String title = "Ich helfe dir kochen. Das erfolgreiche Universalkochbuch mit großem Backteil";

    List<Book> books = LibraryManager.readBook(Book::fromLine);
    String gotTitle = books.stream()
        .filter(a -> a.getTitle().equals(title))
        .findFirst().get().getTitle();

    assertTrue(!books.isEmpty());
    assertEquals(title, gotTitle);
  }

  @Test
  public void test_read_magazines_from_csv() {
    String title = "Beautiful cooking";

    List<Magazine> magazines = LibraryManager.readMagazine(Magazine::fromLine);
    String gotTitle = magazines.stream()
        .filter(a -> a.getTitle().equals(title))
        .findFirst().get().getTitle();

    assertTrue(!magazines.isEmpty());
    assertEquals(title, gotTitle);
  }
}