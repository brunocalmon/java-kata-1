package org.echocat.kata.java.part1;

import java.util.Collections;
import java.util.List;

public class MainApp {

  public static void main(String[] args) {
    LibraryManager manager = new LibraryManager(
        LibraryManager.readMagazine(Magazine::fromLine),
        LibraryManager.readBook(Book::fromLine),
        LibraryManager.readAuthor(Author::fromLine));

    manager.printAllAuthors();
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

    List<Publication> sortedByTitle = manager.getSortedByTitle();
    System.out.println("Books and Magazines sorted by title:");
    for (Publication p : sortedByTitle) {
      System.out.println(p.toString());
    }

  }
}
