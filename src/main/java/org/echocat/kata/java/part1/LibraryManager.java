package org.echocat.kata.java.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryManager {

  final List<Publication> publications = new ArrayList<>();
  final List<Author> authors = new ArrayList<>();

  public LibraryManager(List<Magazine> magazines, List<Book> books, List<Author> authors) {
    this.publications.addAll(magazines);
    this.publications.addAll(books);
    this.authors.addAll(authors);
  }

  public void printAllBooksAndMagazines() {
    publications.stream().forEach(pubs -> {
      System.out.println(pubs.toString());
    });
  }

  public void printAllAuthors() {
    authors.stream().forEach(author -> {
      System.out.println(author.toString());
    });
  }

  public void printAllSortedPublications(List<Publication> sortedByTitlePublications) {
    sortedByTitlePublications.stream().forEach(publication -> {
      System.out.println(publication.toString());
    });
  }

  public List<Publication> getSortedByTitle() {
    List<Publication> cloned = new ArrayList<>(publications);
    cloned.sort(new SortByTitle());
    return cloned;
  }

  public Optional<Publication> getByISBN(String isbn) {
    return publications.stream().filter(pub -> pub.isISBN(isbn)).findFirst();
  }

  public Optional<List<Publication>> getByAuthor(String author) {
    return Optional
        .of(publications.stream().filter(pub -> pub.isAuthor(author)).collect(Collectors.toList()));
  }

  public static List<Magazine> readMagazine(ReadCSVFile<Magazine> readMagazine) {
    Optional<List<Magazine>> magazines = readMagazine.readCsv("magazines");
    return magazines.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public static List<Book> readBook(ReadCSVFile<Book> readBook) {
    Optional<List<Book>> books = readBook.readCsv("books");
    return books.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public static List<Author> readAuthor(ReadCSVFile<Author> readAuthor) {
    Optional<List<Author>> authors = readAuthor.readCsv("authors");
    return authors.orElseThrow(() -> new RuntimeException("Not found"));
  }
}
