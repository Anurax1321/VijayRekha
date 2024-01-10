import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class VijayRekha {

  public static Boolean readData(String filename) throws FileNotFoundException {
    try {
      Scanner scan = new Scanner(new File(filename));
      if (scan.hasNextLine()) {
        String[] split= scan.delimiter().split(" ");
        System.out.println(Arrays.stream(split).count());

        System.out.println(split);
        System.out.println(scan.next());

      }

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    } return true;
  }


  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    String filepath =
        "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\Vijayrekha\\src\\08012024_AA.txt";

    Boolean result = readData(filepath);

    System.out.println(result);

  }


}
