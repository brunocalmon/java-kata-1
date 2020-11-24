package org.echocat.kata.java.part1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public interface WriteCSVFile<T> {

  default boolean createCsv(String name, String headers, List<T> data)
      throws IOException, URISyntaxException {
    String packageName = getClass().getPackageName().replace(".", "/");
    String filePath = packageName + "/data";
    URL resource = getClass().getClassLoader().getResource(filePath);
    String stringPath = resource.toString().replace("target/classes", "src/main/resources");

    File parentDir = new File(new URI(stringPath));
    File file = new File(parentDir, name + "-" + getDate() + ".csv");

    file.createNewFile();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
      bw.write(headers);
      bw.newLine();
      int i = 0;
      while (i < data.size()) {
        T item = data.get(i);
        bw.write(this.parseLine(item));
        bw.newLine();
        bw.flush();
        ++i;
      }
      return true;
    } catch (Exception ee) {
      ee.printStackTrace();
    }
    return false;
  }

  private String getDate() {
    DateTimeFormatter formatter =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.UK)
            .withZone(ZoneId.systemDefault());

    Instant instant = Instant.now();
    return formatter.format(instant).split(",")[0].replace("/", ".");
  }

  String parseLine(T lineDetails);
}
