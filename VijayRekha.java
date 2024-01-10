import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VijayRekha {

  public static Boolean readData(String filename) throws FileNotFoundException {
    try {
      Scanner scan = new Scanner(new File(filename));
      if (scan.hasNextLine()) {
        ArrayList<String> split = new ArrayList<>();
        while (scan.hasNext()) {
          split.add(scan.next());
        }
        System.out.println(split);
      }

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    } return true;
  }


  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    String filepath = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";

    Boolean result = readData(filepath);

    System.out.println(result);

  }


}
