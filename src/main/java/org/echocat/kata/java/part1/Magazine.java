package org.echocat.kata.java.part1;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class Magazine extends Publication {

  public static String HEADERS = "title;isbn;authors;publishedAt";
  public static String FILE_NAME = "magazines";
  private String publishedAt;

  public Magazine(String title, String isbn, List<String> authors, String publishedAt) {
    super(title, isbn, authors);
    this.publishedAt = publishedAt;
  }

  public static Magazine fromLine(String[] lineDetails) {
    List<String> authors = Arrays.asList(lineDetails[2].split(","));
    return new Magazine(lineDetails[0], lineDetails[1], authors, lineDetails[3]);
  }

  public static String fromObject(Magazine magazine) {
    String authors = magazine.getAuthors().stream().reduce("", (prev, next) -> {
      if (prev == "") {
        return next;
      } else {
        return prev + "," + next;
      }
    });
    return magazine.getTitle() + ";" + magazine.getIsbn() + ";" + authors + ";" + magazine
        .getPublishedAt();
  }

  @Override
  public PUBLICATION_TYPE getType() {
    return PUBLICATION_TYPE.MAGAZINE;
  }

  @Override
  public String toString() {
    return "Magazine(" +
        "title=" + super.getTitle() + ", " +
        "isbn=" + super.getIsbn() + ", " +
        "authors=" + super.getAuthors() + ", " +
        "publishedAt=" + publishedAt +
        ")";
  }
}
