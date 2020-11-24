package org.echocat.kata.java.part1;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Test;

public class MagazineTest {

  @Test
  public void test_magazine_creation_from_line() {
    String[] lines = new String[]{"A", "B", "C,D", "E"};

    Magazine magazine = Magazine.fromLine(lines);

    assertEquals("A", magazine.getTitle());
    assertEquals("B", magazine.getIsbn());
    assertEquals("C", magazine.getAuthors().get(0));
    assertEquals("D", magazine.getAuthors().get(1));
    assertEquals("E", magazine.getPublishedAt());
  }

  @Test
  public void test_magazine_converting_to_csv_format() {
    Magazine magazine = new Magazine("John Doe Scientific", "1234-1234-1234",
        Arrays.asList("john-doe@mail.com", "john-snow@mail.com"),
        "21.05.2011");

    String csvMagazineLine = Magazine.fromObject(magazine);

    assertEquals(
        "John Doe Scientific;1234-1234-1234;john-doe@mail.com,john-snow@mail.com;21.05.2011",
        csvMagazineLine);
  }
}