import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VijayRekha {
  static ArrayList<String> head = new ArrayList<>();
  static ArrayList<String> data = new ArrayList<>();

  public static Boolean readData(String filename, int limit) throws FileNotFoundException {
    try {
      Scanner scan = new Scanner(new File(filename));
      head.add("Name");

      while (scan.hasNextLine()) {
        proccesLine(scan.nextLine(), limit);
      }
      System.out.println(data);
      System.out.println(head);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
    return true;
  }

  public static void proccesLine(String line, int limit) {
    if (line != "") {
      String[] word = line.split("\t");
      if (line.startsWith("Compound")) {
        head.add(line);
      }
      if (word.length > 1 && line.matches("^\\d.*")) {
        data.add(line);
      }
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    String filepath = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";

    int limit = 22;
    Boolean result = readData(filepath, limit);

    System.out.println(result);

  }


}
