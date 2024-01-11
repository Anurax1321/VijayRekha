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
        String line = scan.nextLine();
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
      ArrayList<String> patientNames = new ArrayList<>();
//      String[][] Response = new String[limit + 1][head.size()];
      ArrayList<String> responses = new ArrayList<>();
      for (int i = 1; i <= limit; i++) {
        String pattern = "^" + i + "\\D";
        for (int j = 0; j < data.size(); j++) {
          if (data.get(j).matches(pattern + ".*")) {
            //            System.out.println(data.get(j));
            String[] dataLine = data.get(j).split("\t");

            if (!(patientNames.contains(dataLine[1]))) {
              patientNames.add(dataLine[1]);
            }
            //            try {



            responses.add(dataLine[dataLine.length - 1]);





            //            }catch (IndexOutOfBoundsException e){
            //              System.out.println("N/A");
            //            }
            //            for(String item : dataLine){
            //              System.out.println(item);
            //            }
          }
        }
      }
      //      System.out.println(patientNames);
      //      System.out.println(patientNames.size());
      System.out.println(responses);
      System.out.println(responses.size());

      //      System.out.println(data);
      System.out.println(head);
      System.out.println(head.size());

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
    return true;
  }

  public static void proccesLine(String line, int limit) {
  }

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello world!");
    String filepath = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";

    int limit = 22;
    Boolean result = readData(filepath, limit);

    System.out.println(result);

  }


}
