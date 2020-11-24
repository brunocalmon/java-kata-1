package org.echocat.kata.java.part1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

  private String email, firstname, lastname;

  public static Author fromLine(String[] lineDetails) {
    return new Author(lineDetails[0], lineDetails[1], lineDetails[2]);
  }
}
