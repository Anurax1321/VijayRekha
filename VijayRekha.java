//Import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a class with a mina method and rest non-static methods to read an interprit the data
 * to get a usable result.
 *
 * result is also expected to be printed to an Excel file
 */
public class VijayRekha {

  // global variables

  //
  ArrayList<String> head = new ArrayList<>();

  //
  ArrayList<String> data = new ArrayList<>();

  //

  //
  int limit;

  //
  int headNumber;


  /**
   * This is a constructor which takes in two parameters
   *
   * @param limit
   * @param headNumber
   */
  /**
   * This is a constructor which takes in two parameters
   *
   * @param limit
   * @param headType
   */
  public VijayRekha(int limit, int headType ) {
    this.limit = limit;
    if (headType==1){
      this.headNumber = 29;
    } else if (headType==2) {
      this.headNumber = 27;
    } else if (headType==3) {
      this.headNumber= 35;
    }

  }

  /**
   * @param filename
   * @return
   * @throws FileNotFoundException
   */
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
      //      System.out.println(head);
      //      System.out.println(patientNames);
      //      System.out.println(responses);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Invalid file path! try again");
    }
    String[][] empty = new String[0][0];
    return empty;
  }

  /**
   * This is a helper method to print the elements in the 2D Array
   *
   * This method uses nested for loop and helps us to select each element at a time in the 2d array
   * starting from the left corner of the array
   *
   * @param array the 2D array that we want to be printed
   */
  public void print2DArray(String[][] array) {
    // using a nested for loop
    for (int i = 0; i < array.length; i++) {
      // the first for-loop goes through all columns of current row
      // 'i' represents the current row
      for (int j = 0; j < array[i].length; j++) {
        // the second for-loop goes through all rows of the current column
        // 'j' represents the current column
        System.out.print(array[i][j] + " , ");
        // printing each element ending with a comma for .excel representation
      }
      System.out.println();
      // Adding a new line after each row; for tabular print; excel representation
    }
  }

  /**
   * This is the main method where the inputs are taken from the user and passed to the non-static
   * method in the class using an object of the class itself.
   *
   * @param args
   * @throws FileNotFoundException if the file path doesn't result in any usable file; in
   * readData method
   */
  public static void main(String[] args) throws FileNotFoundException {

    int limit = 18;
    String filepath1 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";
    //    String filepath2 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AC.txt";
    //    String filepath3 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_ACEXT.txt";

    VijayRekha result = new VijayRekha(limit + 4, 0);

    String[][] results1 = result.readData(filepath1);
    result.print2DArray(results1);
    //    String[][] results2 = result.readData(filepath2);
    //    System.out.println(results2);
    //    String[][] results3 = result.readData(filepath3);
    //    System.out.println(results3);

  }
}
