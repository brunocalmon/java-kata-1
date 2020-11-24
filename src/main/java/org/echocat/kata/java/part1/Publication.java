package org.echocat.kata.java.part1;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Publication {

  public enum PUBLICATION_TYPE {
    BOOK, MAGAZINE, UNKNOWN
  }

  private String title, isbn;
  private List<String> authors;

  public boolean isISBN(String isbn) {
    return this.isbn.equals(isbn);
  }

  public boolean isAuthor(String author) {
    return authors.contains(author);
  }

  public abstract PUBLICATION_TYPE getType();
}
