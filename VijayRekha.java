//Import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a class with a mina method and rest non-static methods to read an interprit the data to
 * get a usable result.
 *
 * result is also expected to be printed to an Excel file
 */
public class VijayRekha {

  // global variables

  // ArrayList to store the Header Values
  ArrayList<String> head = new ArrayList<>();

  // ArrayList to store the patient names
  ArrayList<String> patientNames = new ArrayList<>();

  // ArrayList to store the responses for all the compounds per patient
  ArrayList<String> responses = new ArrayList<>();

  // variable to store the number of patient given by the user
  int limit;

  // Variable to store the number of header elements based on the data set
  int headNumber;

  /**
   * This is a constructor which takes in two parameters and assignees (HardCoding) it ot the global
   * variable that is used further in the code. there is an if-else which decide how many number of
   * compounds that are in the header for the data set chosen.
   *
   * @param limit    this is the number of patient given by the user
   * @param headType this is the type of the data set that is being passed; used to set the no. of
   *                 header elements
   */
  public VijayRekha(int limit, int headType) {
    // number of patients
    this.limit = limit;
    // assigning (HardCoding) the headNumber based on headType
    if (headType == 1) { // AA
      this.headNumber = 29; // no. of compounds in the data set
    } else if (headType == 2) { // AC
      this.headNumber = 27; // no. of compounds in the data set
    } else if (headType == 3) { // ACEXT
      this.headNumber = 35; // no. of compounds in the data set
    }
  }

  // 2D array to store the Excl represented values
  String[][] combine = new String[this.limit + 1][headNumber];

  /**
   * This is a method to read the data
   *
   * @param filename
   * @return
   * @throws FileNotFoundException
   */
  public String[][] readData(String filename) throws FileNotFoundException {
    try {
      Scanner scan = new Scanner(new File(filename));
      head.add("Name");
      // ArrayList to store the whole data that I need the information from;
      ArrayList<String> data = new ArrayList<>();
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

      for (int i = 1; i <= limit; i++) {
        String pattern = "^" + i + "\\D";
        for (int j = 0; j < data.size(); j++) {
          if (data.get(j).matches(pattern + ".*")) {
            // Array to store each line of the data ArrayList after the split
            String[] dataLine = data.get(j).split("\t");

            if (!(patientNames.contains(dataLine[1]))) {
              // adding the patients names to the arraylist
              patientNames.add(dataLine[1]);
            }
            // adding the responses to the arraylist; one coumpound after the other for a
            // particular patient
            responses.add(dataLine[dataLine.length - 1]);
          }
        }
      }

      //      System.out.println(head.size());


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
    // returning the 2D array
    return combine;
  }

  /**
   * This is a helper method to print the elements in the 2D Array in the form of an Excel
   * representation
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
   * @throws FileNotFoundException if the file path doesn't result in any usable file; in readData
   *                               method
   */
  public static void main(String[] args) throws FileNotFoundException {

    // number of patients given by the user
    int limit = 18;

    // Data Paths for AA, AC, ACEXT; given by the user
    String filepath1 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AA.txt";
    //    String filepath2 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AC.txt";
    //    String filepath3 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_ACEXT.txt";

    // creating the object of the class with the parameters
    VijayRekha result = new VijayRekha(limit + 4, 1);

    // the 2D Array for the AA dataset
    String[][] results1 = result.readData(filepath1);
    // printing the 2D Array
    result.print2DArray(results1);

    // the 2D Array for the AC dataset
    //    String[][] results2 = result.readData(filepath2);
    // printing the 2D Array
    //    System.out.println(results2);

    // the 2D Array for the ACEXT dataset
    //    String[][] results3 = result.readData(filepath3);
    // printing the 2D Array
    //    System.out.println(results3);

  }
}
