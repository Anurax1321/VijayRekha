import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VijayRekha {

  public static Boolean readData(String filename) throws FileNotFoundException {
    try {
//      ArrayList<String> eachLine = new ArrayList<>();
      Scanner scan = new Scanner(new File(filename));
//      for (int i = 0; i < eachLine.size(); i++) {
//        eachLine.add(Arrays.toString(scan.nextLine().split(" ")));
////        String presentLine = eachLine.get(i);
////        //        if(presentLine!=""){
////        proccesLine(presentLine);
////        //        }
//      }

      while(scan.hasNextLine()){
        proccesLine(scan.nextLine());
      }
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
    return true;
  }

  public static void proccesLine(String line) {
    if(line!=""){
      System.out.println(line);
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    String filepath = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";

    Boolean result = readData(filepath);

    System.out.println(result);

  }


}
