package org.echocat.kata.java.part1;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class Book extends Publication {

  public static String HEADERS = "title;isbn;authors;description";
  public static String FILE_NAME = "books";
  private String description;

  public Book(String title, String isbn, List<String> authors, String description) {
    super(title, isbn, authors);
    this.description = description;
  }

  public static Book fromLine(String[] lineDetails) {
    List<String> authors = Arrays.asList(lineDetails[2].split(","));
    return new Book(lineDetails[0], lineDetails[1], authors, lineDetails[3]);
  }

  public static String fromObject(Book book) {
    String authors = book.getAuthors().stream().reduce("", (prev, next) -> {
      if (prev == "") {
        return next;
      } else {
        return prev + "," + next;
      }
    });
    return book.getTitle() + ";" + book.getIsbn() + ";" + authors + ";" + book.getDescription();
  }

  @Override
  public PUBLICATION_TYPE getType() {
    return PUBLICATION_TYPE.BOOK;
  }

  @Override
  public String toString() {
    return "Book(" +
        "title=" + super.getTitle() + ", " +
        "isbn=" + super.getIsbn() + ", " +
        "authors=" + super.getAuthors() + ", " +
        "description=" + description +
        ")";
  }
}
