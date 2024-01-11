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
      ArrayList<String> responses = new ArrayList<>();
      for (int i = 1; i <= limit; i++) {
        String pattern = "^" + i + "\\D";
        for (int j = 0; j < data.size(); j++) {
          if (data.get(j).matches(pattern + ".*")) {
            String[] dataLine = data.get(j).split("\t");

            if (!(patientNames.contains(dataLine[1]))) {
              patientNames.add(dataLine[1]);
            }
            responses.add(dataLine[dataLine.length - 1]);
          }
        }
      }

      String[][] combine = new String[limit + 1][head.size()];

      for (int i = 0; i < head.size(); i++) {
        combine[0][i] = head.get(i);
      }

      for (int i = 1, k = 0; i <= limit && k < responses.size(); i++) {
        combine[i][0] = patientNames.get(i - 1);
        for (int j = 1; j < head.size(); j++, k++) {
          combine[i][j] = responses.get(k);
        }
      }

      for (int i = 0; i < combine.length; i++) {
        // Loop through all columns of current row
        for (int j = 0; j < combine[i].length; j++) {
          System.out.print(combine[i][j] + " , ");
        }
        System.out.println(); // New line after each row
      }
      //      System.out.println(head);
      //      System.out.println(patientNames);
      //      System.out.println(responses);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
    return true;
  }

  //  public static void proccesLine(String line, int limit) {
  //  }

  public static void main(String[] args) throws FileNotFoundException {
    String filepath = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";
    int limit = 18;
    readData(filepath, limit + 4);
  }
}
