import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VijayRekha {

  public static Boolean readData(String filename) throws FileNotFoundException {
    try {
      ArrayList<String> eachLine = new ArrayList<>();
      Scanner scan = new Scanner(new File(filename));
      while (scan.hasNextLine()) {
        eachLine.add(Arrays.toString(scan.nextLine().split(" ")));
      }
      System.out.println(eachLine.size());
      System.out.println(eachLine);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
    return true;
  }

  public static void proccesLine(String line) {
    System.out.println(line);
  }

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    String filepath = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";

    Boolean result = readData(filepath);

    System.out.println(result);

  }


}
