import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VijayRekha {

  ArrayList<String> head = new ArrayList<>();
  ArrayList<String> data = new ArrayList<>();
  int limit;
  int headNumber;

  public VijayRekha(int limit,int headNumber){
    this.limit=limit;
    this.headNumber=headNumber;
  }

  public String[][] readData(String filename) throws FileNotFoundException {
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
      throw new FileNotFoundException("Invalid file path! try again");
    }
    String[][] empty = new String[0][0];
    return empty;
  }

  public static void main(String[] args) throws FileNotFoundException {

    int limit = 18;
    String filepath1 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";
    String filepath2 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AC.txt";
    String filepath3 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_ACEXT.txt";

    VijayRekha result = new VijayRekha( limit + 4,0);

    String[][] results1 = result.readData(filepath1);
    System.out.println(results1);
    String[][] results2 = result.readData(filepath2);
    System.out.println(results2);
    String[][] results3 = result.readData(filepath3);
    System.out.println(results3);


  }
}
