package org.echocat.kata.java.part1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.echocat.kata.java.part1.Publication.PUBLICATION_TYPE;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagerTest {

  LibraryManager manager;

  @Before
  public void setUp() {
    manager = new LibraryManager(
        LibraryManager.readMagazine(Magazine::fromLine, Magazine.FILE_NAME),
        LibraryManager.readBook(Book::fromLine, Book.FILE_NAME),
        LibraryManager.readAuthor(Author::fromLine, Author.FILE_NAME));
  }

  @Test
  public void test_print_all_books_and_magazines() throws Exception {
    String expected =
        "Magazine(title=Beautiful cooking, isbn=5454-5587-3210, authors=[null-walter@echocat.org], publishedAt=21.05.2011)\n"
            + "Magazine(title=My familie and me, isbn=4545-8541-2012, authors=[null-mueller@echocat.org], publishedAt=10.07.2011)\n"
            + "Magazine(title=Cooking for gourmets, isbn=2365-5632-7854, authors=[null-lieblich@echocat.org, null-walter@echocat.org], publishedAt=01.05.2012)\n"
            + "Magazine(title=Gourmet, isbn=2365-8745-7854, authors=[null-ferdinand@echocat.org], publishedAt=14.06.2010)\n"
            + "Magazine(title=The Wine Connoisseurs, isbn=2547-8548-2541, authors=[null-walter@echocat.org], publishedAt=12.12.2011)\n"
            + "Magazine(title=Vinum, isbn=1313-4545-8875, authors=[null-gustafsson@echocat.org], publishedAt=23.02.2012)\n"
            + "Book(title=Ich helfe dir kochen. Das erfolgreiche Universalkochbuch mit großem Backteil, isbn=5554-5545-4518, authors=[null-walter@echocat.org], description=Auf der Suche nach einem Basiskochbuch steht man heutzutage vor einer Fülle von Alternativen. Es fällt schwer, daraus die für sich passende Mixtur aus Grundlagenwerk und Rezeptesammlung zu finden. Man sollte sich darüber im Klaren sein, welchen Schwerpunkt man setzen möchte oder von welchen Koch- und Backkenntnissen man bereits ausgehen kann.)\n"
            + "Book(title=Das große GU-Kochbuch Kochen für Kinder, isbn=2145-8548-3325, authors=[null-ferdinand@echocat.org, null-lieblich@echocat.org], description=Es beginnt mit... den ersten Löffelchen für Mami, Papi und den Rest der Welt. Ja, und dann? Was Hersteller von Babynahrung können, das ist oft im Handumdrehen auch selbst gemacht, vielleicht sogar gesünder, oftmals frischer. Ältere Babys und Schulkinder will das Kochbuch ansprechen und das tut es auf eine verspielte Art angenehm altersgemäß.)\n"
            + "Book(title=Schlank im Schlaf , isbn=4545-8558-3232, authors=[null-gustafsson@echocat.org], description=Schlank im Schlaf klingt wie ein schöner Traum, aber es ist wirklich möglich. Allerdings nicht nach einer Salamipizza zum Abendbrot. Die Grundlagen dieses neuartigen Konzepts sind eine typgerechte Insulin-Trennkost sowie Essen und Sport im Takt der biologischen Uhr. Wie die Bio-Uhr tickt und was auf dem Speiseplan stehen sollte, hängt vom persönlichen Urtyp ab: Nomade oder Ackerbauer?)\n"
            + "Book(title=Das Perfekte Dinner. Die besten Rezepte, isbn=2221-5548-8585, authors=[null-lieblich@echocat.org], description=Sie wollen auch ein perfektes Dinner kreieren? Mit diesem Buch gelingt es Ihnen!)\n"
            + "Book(title=Das Piratenkochbuch. Ein Spezialitätenkochbuch mit den 150 leckersten Rezepten , isbn=3214-5698-7412, authors=[null-rabe@echocat.org], description=Das Piraten-Kochbuch beweist, dass die Seeräuberkapitäne zwar gefürchtete Haudegen waren, andererseits aber manches Mal mit gehobenenem Geschmacksempfinden ausgestattet. ... Kurzum, ein ideales Buch, um maritime Events kulinarisch zu umrahmen.)\n"
            + "Book(title=Genial italienisch, isbn=1024-5245-8584, authors=[null-lieblich@echocat.org, null-walter@echocat.org, null-rabe@echocat.org], description=Starkoch Jamie Oliver war mit seinem VW-Bus in Italien unterwegs -- und hat allerlei kulinarische Souvenirs mitgebracht. Es lohnt sich, einen Blick in sein Gepäck zu werfen...)\n"
            + "Book(title=O'Reillys Kochbuch für Geeks, isbn=2215-0012-5487, authors=[null-mueller@echocat.org], description=Nach landläufiger Meinung leben Geeks von Cola und TK-Pizza, die sie nachts am Rechner geistesabwesend in sich reinschlingen. Soweit das Klischee! Dass aber Kochen viel mit Programmieren zu tun hat, dass es nämlich ähnlich kreativ ist, dass viele Wege zum individuellen Ziel führen und dass manche Rezepte einfach nur abgefahren und -- ja, geekig sind: Das zeigen zwei Köchinnen in diesem Buch.)\n"
            + "Book(title=Schuhbecks Kochschule. Kochen lernen mit Alfons Schuhbeck , isbn=1215-4545-5895, authors=[null-walter@echocat.org], description=Aller Anfang ist leicht! Zumindest, wenn man beim Kochenlernen einen Lehrer wie Alfons Schuhbeck zur Seite hat. Mit seiner Hilfe kann auch der ungeschickteste Anfänger beste Noten für seine Gerichte bekommen. Der Trick, den der vielfach ausgezeichnete Meisterkoch dabei anwendet, lautet visualisieren. Die einzelnen Arbeitsschritte werden auf Farbfotos im Format von ca. 3x4 cm abgebildet. Unter diesen stehen kurz und knapp die Angaben zur Zubereitung. So präsentiert Schuhbecks Kochschule alles bequem auf einen Blick. Und der interessierte Kochneuling kann sich auf die Hauptsache konzentrieren – aufs Kochen. Wie die Speise aussehen soll, zeigt jeweils das Farbfoto auf der linken Seite, auf der auch die Zutaten – dank des geschickten Layouts – ebenfalls sehr übersichtlich aufgelistet sind. Außerdem gibt Schuhbeck prägnante Empfehlungen zu Zutaten und Zubereitung.)\n";

    //Redirect System.out to buffer
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    System.setOut(new PrintStream(bo));

    manager.printAllBooksAndMagazines();

    bo.flush();
    String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains(expected));
  }

  @Test
  public void test_print_all_authors() throws Exception {
    String expected = "Author(email=null-walter@echocat.org, firstName=Paul, lastName=Walter)\n"
        + "Author(email=null-mueller@echocat.org, firstName=Max, lastName=Müller)\n"
        + "Author(email=null-ferdinand@echocat.org, firstName=Franz, lastName=Ferdinand)\n"
        + "Author(email=null-gustafsson@echocat.org, firstName=Karl, lastName=Gustafsson)\n"
        + "Author(email=null-lieblich@echocat.org, firstName=Werner, lastName=Lieblich)\n"
        + "Author(email=null-rabe@echocat.org, firstName=Harald, lastName=Rabe)\n";

    //Redirect System.out to buffer
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    System.setOut(new PrintStream(bo));

    manager.printAllAuthors();

    bo.flush();
    String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains(expected));
  }

  @Test
  public void test_print_publications_sorted_by_title() throws Exception {
    String expected =
        "Magazine(title=Beautiful cooking, isbn=5454-5587-3210, authors=[null-walter@echocat.org], publishedAt=21.05.2011)\n"
            + "Magazine(title=Cooking for gourmets, isbn=2365-5632-7854, authors=[null-lieblich@echocat.org, null-walter@echocat.org], publishedAt=01.05.2012)\n"
            + "Book(title=Das Perfekte Dinner. Die besten Rezepte, isbn=2221-5548-8585, authors=[null-lieblich@echocat.org], description=Sie wollen auch ein perfektes Dinner kreieren? Mit diesem Buch gelingt es Ihnen!)\n"
            + "Book(title=Das Piratenkochbuch. Ein Spezialitätenkochbuch mit den 150 leckersten Rezepten , isbn=3214-5698-7412, authors=[null-rabe@echocat.org], description=Das Piraten-Kochbuch beweist, dass die Seeräuberkapitäne zwar gefürchtete Haudegen waren, andererseits aber manches Mal mit gehobenenem Geschmacksempfinden ausgestattet. ... Kurzum, ein ideales Buch, um maritime Events kulinarisch zu umrahmen.)\n"
            + "Book(title=Das große GU-Kochbuch Kochen für Kinder, isbn=2145-8548-3325, authors=[null-ferdinand@echocat.org, null-lieblich@echocat.org], description=Es beginnt mit... den ersten Löffelchen für Mami, Papi und den Rest der Welt. Ja, und dann? Was Hersteller von Babynahrung können, das ist oft im Handumdrehen auch selbst gemacht, vielleicht sogar gesünder, oftmals frischer. Ältere Babys und Schulkinder will das Kochbuch ansprechen und das tut es auf eine verspielte Art angenehm altersgemäß.)\n"
            + "Book(title=Genial italienisch, isbn=1024-5245-8584, authors=[null-lieblich@echocat.org, null-walter@echocat.org, null-rabe@echocat.org], description=Starkoch Jamie Oliver war mit seinem VW-Bus in Italien unterwegs -- und hat allerlei kulinarische Souvenirs mitgebracht. Es lohnt sich, einen Blick in sein Gepäck zu werfen...)\n"
            + "Magazine(title=Gourmet, isbn=2365-8745-7854, authors=[null-ferdinand@echocat.org], publishedAt=14.06.2010)\n"
            + "Book(title=Ich helfe dir kochen. Das erfolgreiche Universalkochbuch mit großem Backteil, isbn=5554-5545-4518, authors=[null-walter@echocat.org], description=Auf der Suche nach einem Basiskochbuch steht man heutzutage vor einer Fülle von Alternativen. Es fällt schwer, daraus die für sich passende Mixtur aus Grundlagenwerk und Rezeptesammlung zu finden. Man sollte sich darüber im Klaren sein, welchen Schwerpunkt man setzen möchte oder von welchen Koch- und Backkenntnissen man bereits ausgehen kann.)\n"
            + "Magazine(title=My familie and me, isbn=4545-8541-2012, authors=[null-mueller@echocat.org], publishedAt=10.07.2011)\n"
            + "Book(title=O'Reillys Kochbuch für Geeks, isbn=2215-0012-5487, authors=[null-mueller@echocat.org], description=Nach landläufiger Meinung leben Geeks von Cola und TK-Pizza, die sie nachts am Rechner geistesabwesend in sich reinschlingen. Soweit das Klischee! Dass aber Kochen viel mit Programmieren zu tun hat, dass es nämlich ähnlich kreativ ist, dass viele Wege zum individuellen Ziel führen und dass manche Rezepte einfach nur abgefahren und -- ja, geekig sind: Das zeigen zwei Köchinnen in diesem Buch.)\n"
            + "Book(title=Schlank im Schlaf , isbn=4545-8558-3232, authors=[null-gustafsson@echocat.org], description=Schlank im Schlaf klingt wie ein schöner Traum, aber es ist wirklich möglich. Allerdings nicht nach einer Salamipizza zum Abendbrot. Die Grundlagen dieses neuartigen Konzepts sind eine typgerechte Insulin-Trennkost sowie Essen und Sport im Takt der biologischen Uhr. Wie die Bio-Uhr tickt und was auf dem Speiseplan stehen sollte, hängt vom persönlichen Urtyp ab: Nomade oder Ackerbauer?)\n"
            + "Book(title=Schuhbecks Kochschule. Kochen lernen mit Alfons Schuhbeck , isbn=1215-4545-5895, authors=[null-walter@echocat.org], description=Aller Anfang ist leicht! Zumindest, wenn man beim Kochenlernen einen Lehrer wie Alfons Schuhbeck zur Seite hat. Mit seiner Hilfe kann auch der ungeschickteste Anfänger beste Noten für seine Gerichte bekommen. Der Trick, den der vielfach ausgezeichnete Meisterkoch dabei anwendet, lautet visualisieren. Die einzelnen Arbeitsschritte werden auf Farbfotos im Format von ca. 3x4 cm abgebildet. Unter diesen stehen kurz und knapp die Angaben zur Zubereitung. So präsentiert Schuhbecks Kochschule alles bequem auf einen Blick. Und der interessierte Kochneuling kann sich auf die Hauptsache konzentrieren – aufs Kochen. Wie die Speise aussehen soll, zeigt jeweils das Farbfoto auf der linken Seite, auf der auch die Zutaten – dank des geschickten Layouts – ebenfalls sehr übersichtlich aufgelistet sind. Außerdem gibt Schuhbeck prägnante Empfehlungen zu Zutaten und Zubereitung.)\n"
            + "Magazine(title=The Wine Connoisseurs, isbn=2547-8548-2541, authors=[null-walter@echocat.org], publishedAt=12.12.2011)\n"
            + "Magazine(title=Vinum, isbn=1313-4545-8875, authors=[null-gustafsson@echocat.org], publishedAt=23.02.2012)\n";

    //Redirect System.out to buffer
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    System.setOut(new PrintStream(bo));

    manager.printAllSortedPublications(manager.getSortedByTitle());

    bo.flush();
    String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains(expected));
  }

  @Test
  public void test_getting_by_isbn() {
    String isbn = "5554-5545-4518";

    Publication pubByISBN = manager.getByISBN(isbn).get();

    assertEquals(isbn, pubByISBN.getIsbn());
  }

  @Test(expected = RuntimeException.class)
  public void test_getting_by_isbn_not_found() {
    String isbn = "1111-1111-1111";

    manager.getByISBN(isbn).orElseThrow(() -> new RuntimeException("Not Found"));

    fail();
  }

  @Test
  public void test_getting_by_author() {
    String author = "null-mueller@echocat.org";

    List<Publication> pubsByAuthor = manager.getByAuthor(author).get();

    assertTrue(pubsByAuthor.get(0).getAuthors().contains(author));
    assertTrue(pubsByAuthor.get(1).getAuthors().contains(author));
  }

  @Test(expected = RuntimeException.class)
  public void test_getting_by_author_not_found() {
    String author = "pink-unicorn@mail.com";

    manager.getByAuthor(author).orElseThrow(() -> new RuntimeException("Not Found"));

    fail();
  }

  @Test
  public void test_read_author_from_csv() {
    String authorLastName = "Müller";

    List<Author> authors = LibraryManager.readAuthor(Author::fromLine, Author.FILE_NAME);
    String gotLastName = authors.stream()
        .filter(a -> a.getLastName().equals(authorLastName))
        .findFirst().get().getLastName();

    assertTrue(!authors.isEmpty());
    assertEquals(authorLastName, gotLastName);
  }

  @Test
  public void test_read_book_from_csv() {
    String title = "Ich helfe dir kochen. Das erfolgreiche Universalkochbuch mit großem Backteil";

    List<Book> books = LibraryManager.readBook(Book::fromLine, Book.FILE_NAME);
    String gotTitle = books.stream()
        .filter(a -> a.getTitle().equals(title))
        .findFirst().get().getTitle();

    assertTrue(!books.isEmpty());
    assertEquals(title, gotTitle);
  }

  @Test
  public void test_read_magazines_from_csv() {
    String title = "Beautiful cooking";

    List<Magazine> magazines = LibraryManager.readMagazine(Magazine::fromLine, Magazine.FILE_NAME);
    String gotTitle = magazines.stream()
        .filter(a -> a.getTitle().equals(title))
        .findFirst().get().getTitle();

    assertTrue(!magazines.isEmpty());
    assertEquals(title, gotTitle);
  }

  @Test
  public void test_write_author_from_csv() {
    List<Author> authors = getAuthors(manager);

    authors.add(new Author("john-doe@mail.com", "John", "Doe"));
    authors.add(new Author("john-snow@mail.com", "John", "Snow"));

    boolean result = false;
    try {
      result = LibraryManager.writeAuthor(Author::fromObject, Author.FILE_NAME, authors);
    } catch (IOException ex) {
      System.out.println(ex);
    } catch (URISyntaxException ex) {
      System.out.println(ex);
    }

    assertTrue(result);
  }

  public static List<Author> getAuthors(LibraryManager manager) {
    return manager.getAllAuthors();
  }

  @Test
  public void test_write_book_from_csv() {
    List<Book> books = getBooks(manager);

    books.add(new Book("John Doe Scientific, The Book", "9231-7334-2234",
        Arrays.asList("john-doe@mail.com", "john-snow@mail.com"),
        "01.02.2019"));

    boolean result = false;
    try {
      result = LibraryManager.writeBook(Book::fromObject, Book.FILE_NAME, books);
    } catch (IOException ex) {
      System.out.println(ex);
    } catch (URISyntaxException ex) {
      System.out.println(ex);
    }

    assertTrue(result);
  }

  public static List<Book> getBooks(LibraryManager manager) {
    return manager.getAllPublications().stream()
        .filter(pub -> pub.getType().equals(PUBLICATION_TYPE.BOOK))
        .map(pub -> (Book) pub)
        .collect(Collectors.toList());
  }

  @Test
  public void test_write_magazines_from_csv() {
    List<Magazine> magazines = getMagazines(manager);

    magazines.add(new Magazine("John Doe Scientific", "1234-1234-1234",
        Arrays.asList("john-doe@mail.com", "john-snow@mail.com"),
        "21.05.2011"));

    boolean result = false;
    try {
      result = LibraryManager.writeMagazine(Magazine::fromObject, Magazine.FILE_NAME, magazines);
    } catch (IOException ex) {
      System.out.println(ex);
    } catch (URISyntaxException ex) {
      System.out.println(ex);
    }

    assertTrue(result);
  }

  public static List<Magazine> getMagazines(LibraryManager manager) {
    return manager.getAllPublications().stream()
        .filter(pub -> pub.getType().equals(PUBLICATION_TYPE.MAGAZINE))
        .map(pub -> (Magazine) pub)
        .collect(Collectors.toList());
  }
}