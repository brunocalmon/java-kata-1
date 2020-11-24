package org.echocat.kata.java.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ReadCSVFile<T> {

  String COMMA_DELIMITER = ";";

  default Optional<List<T>> readCsv(String name) {
    String packageName = getClass().getPackageName().replace(".", "/");
    String filePath = packageName + "/data/" + name + ".csv";
    URL resource = getClass().getClassLoader().getResource(filePath);

    try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
      List<T> list = new ArrayList<T>();

      String line = "";
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] lineDetails = line.split(COMMA_DELIMITER);

        if (lineDetails.length > 0) {
          T item = this.parseLine(lineDetails);
          list.add(item);
        }
      }
      return Optional.of(list);
    } catch (Exception ee) {
      ee.printStackTrace();
    }
    return Optional.empty();
  }

  T parseLine(String[] lineDetails);
}
