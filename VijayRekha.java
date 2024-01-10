import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class VijayRekha {

  public static Boolean readData(String filename) throws FileNotFoundException {
    try {
      Scanner scan = new Scanner(new File(filename));
      if (scan.hasNextLine()) {
        String[] split = new String[10];
//        System.out.println(Arrays.stream(split).count());

//        System.out.println(split);
        for (int i=0; i<3;i++){
          split[i]=scan.next();
        }

        for (int i=0; i<3;i++){

          System.out.println(split[i]);
          System.out.println("Hi");
        }


      }

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    } return true;
  }


  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    String filepath =
        "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";

    Boolean result = readData(filepath);

    System.out.println(result);

  }


}
