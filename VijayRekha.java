import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VijayRekha {
  static ArrayList<String> head = new ArrayList<>();

  public static Boolean readData(String filename) throws FileNotFoundException {
    try {
      Scanner scan = new Scanner(new File(filename));
      head.add("Name");
      while (scan.hasNextLine()) {
        proccesLine(scan.nextLine());
      }
      System.out.println(head);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
    return true;
  }

  public static void proccesLine(String line) {
    if (line != "") {
      String[] word = line.split("\t");
      if (line.startsWith("Compound")) {
        head.add(line);
      }
      System.out.println(line);
      System.out.println(word.length);
      //      if(word[0] == "Compound"){
      //        System.out.println(line);
      //      }
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    String filepath = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";

    Boolean result = readData(filepath);

    System.out.println(result);

  }


}
