package org.echocat.kata.java.part1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

  public static String HEADERS = "email;firstname;lastname";
  public static String FILE_NAME = "authors";
  private String email, firstName, lastName;

  public static Author fromLine(String[] lineDetails) {
    return new Author(lineDetails[0], lineDetails[1], lineDetails[2]);
  }

  public static String fromObject(Author author) {
    return author.getEmail() + ";" + author.getFirstName() + ";" + author.getLastName();
  }

  @Override
  public String toString() {
    return "Author(" +
        "email=" + this.getEmail() + ", " +
        "firstName=" + this.getFirstName() + ", " +
        "lastName=" + this.getLastName() + ")";
  }
}
