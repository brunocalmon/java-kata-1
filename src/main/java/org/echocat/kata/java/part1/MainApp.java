package org.echocat.kata.java.part1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.echocat.kata.java.part1.Publication.PUBLICATION_TYPE;

public class MainApp {

  public static void main(String[] args) {
    LibraryManager manager = new LibraryManager(
        LibraryManager.readMagazine(Magazine::fromLine, Magazine.FILE_NAME),
        LibraryManager.readBook(Book::fromLine, Book.FILE_NAME),
        LibraryManager.readAuthor(Author::fromLine, Author.FILE_NAME));

    readCsv(manager);
    writeCsv(manager);
  }

  public static void readCsv(LibraryManager manager) {
    System.out.println("Authors:");
    manager.printAllAuthors();

    System.out.println("Books and Magazines:");
    manager.printAllBooksAndMagazines();

    Publication pubByISBN = manager.getByISBN("5554-5545-4518")
        .orElseThrow(() -> new RuntimeException("ISBN NOT FOUND"));
    System.out.println("Find book by isbn: " + pubByISBN);
    System.out.println(pubByISBN);

    List<Publication> pubByAuthor = manager.getByAuthor("null-mueller@echocat.org")
        .orElseThrow(() -> new RuntimeException("AUTHOR NOT FOUND"));
    System.out.println("Find all by author: " + "null-mueller@echocat.org");
    for (Publication p : pubByAuthor) {
      System.out.println(p.toString());
    }

    System.out.println("Books and Magazines sorted by title:");
    manager.printAllSortedPublications(manager.getSortedByTitle());
  }

  public static void writeCsv(LibraryManager manager) {
    List<Magazine> magazines = getMagazines(manager);
    List<Book> books = getBooks(manager);
    List<Author> authors = getAuthors(manager);

    magazines.add(new Magazine("John Doe Scientific", "1234-1234-1234",
        Arrays.asList("john-doe@mail.com", "john-snow@mail.com"),
        "21.05.2011"));
    books.add(new Book("John Doe Scientific, The Book", "9231-7334-2234",
        Arrays.asList("john-doe@mail.com", "john-snow@mail.com"),
        "01.02.2019"));
    authors.add(new Author("john-doe@mail.com", "John", "Doe"));
    authors.add(new Author("john-snow@mail.com", "John", "Snow"));

    try {
      LibraryManager.writeMagazine(Magazine::fromObject, Magazine.FILE_NAME, magazines);
      LibraryManager.writeAuthor(Author::fromObject, Author.FILE_NAME, authors);
      LibraryManager.writeBook(Book::fromObject, Book.FILE_NAME, books);
    } catch (IOException ex) {
      System.out.println(ex);
    } catch (URISyntaxException ex) {
      System.out.println(ex);
    }
  }

  public static List<Magazine> getMagazines(LibraryManager manager) {
    return manager.getAllPublications().stream()
        .filter(pub -> pub.getType().equals(PUBLICATION_TYPE.MAGAZINE))
        .map(pub -> (Magazine) pub)
        .collect(Collectors.toList());
  }

  public static List<Book> getBooks(LibraryManager manager) {
    return manager.getAllPublications().stream()
        .filter(pub -> pub.getType().equals(PUBLICATION_TYPE.BOOK))
        .map(pub -> (Book) pub)
        .collect(Collectors.toList());
  }

  public static List<Author> getAuthors(LibraryManager manager) {
    return manager.getAllAuthors();
  }
}
