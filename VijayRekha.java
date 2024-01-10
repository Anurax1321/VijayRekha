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
        ArrayList<String> eachLine = new ArrayList<>();
        ArrayList<String> split = new ArrayList<>();
//        while (scan.hasNext()) {
//          split.add(scan.next());
//        }
        eachLine.add(Arrays.toString(scan.nextLine().split(" ")));

//        eachLine.add(scan.nextLine());
        split.add(eachLine.get(0));

//        for(int i=0; i<eachLine.size();i++){
//          System.out.println(eachLine.get(i));
//        }
        System.out.println(eachLine.size());

        System.out.println(split);

//        split.add();
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
