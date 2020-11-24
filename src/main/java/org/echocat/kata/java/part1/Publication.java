package org.echocat.kata.java.part1;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Publication {

  private String title, isbn;
  private List<String> authors;

  public boolean isISBN(String isbn) {
    return this.isbn.equals(isbn);
  }

  public boolean isAuthor(String author) {
    return authors.contains(author);
  }
}
