package org.echocat.kata.java.part1;

import java.io.IOException;
import java.net.URISyntaxException;
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

  public List<Publication> getAllPublications() {
    return publications;
  }

  public List<Author> getAllAuthors() {
    return authors;
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
    List<Publication> publications = this.publications.stream().filter(pub -> pub.isAuthor(author))
        .collect(Collectors.toList());

    if (publications.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(publications);
    }
  }

  public static List<Magazine> readMagazine(ReadCSVFile<Magazine> readMagazine, String fileName) {
    Optional<List<Magazine>> magazines = readMagazine.readCsv(fileName);
    return magazines.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public static List<Book> readBook(ReadCSVFile<Book> readBook, String fileName) {
    Optional<List<Book>> books = readBook.readCsv("books");
    return books.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public static List<Author> readAuthor(ReadCSVFile<Author> readAuthor, String fileName) {
    Optional<List<Author>> authors = readAuthor.readCsv("authors");
    return authors.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public static boolean writeMagazine(WriteCSVFile<Magazine> writeCSVFile, String fileName,
      List<Magazine> magazines) throws IOException, URISyntaxException {
    boolean result = writeCSVFile.createCsv(fileName, Magazine.HEADERS, magazines);
    return result;
  }

  public static boolean writeBook(WriteCSVFile<Book> writeCSVFile, String fileName,
      List<Book> books) throws IOException, URISyntaxException {
    boolean result = writeCSVFile.createCsv(fileName, Book.HEADERS, books);
    return result;
  }

  public static boolean writeAuthor(WriteCSVFile<Author> writeCSVFile, String fileName,
      List<Author> authors) throws IOException, URISyntaxException {
    boolean result = writeCSVFile.createCsv(fileName, Author.HEADERS, authors);
    return result;
  }
}
