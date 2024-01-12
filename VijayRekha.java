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

  // 2D array to store the Excl represented values
  String[][] combine;

  // variable to store the number of patient given by the user
  int limit;

  // Variable to store the number of header elements based on the data set
  int headNumber;

  // Variable to store the chosen dataset
  int chosen;

  /**
   * This is a constructor which takes in two parameters and assignees (HardCoding) it ot the global
   * variable that is used further in the code. there is an if-else which decide how many number of
   * compounds that are in the header for the data set chosen.
   *
   * And to create the 2D array to store the Excel representation
   *
   * @param limit    this is the number of patient given by the user
   * @param headType this is the type of the data set that is being passed; used to set the no. of
   *                 header elements
   */
  public VijayRekha(int limit, int headType) throws IllegalArgumentException {

    // number of patients
    this.limit = limit;
    // chosen type of data set
    this.chosen = headType;

    // assigning (HardCoding) the headNumber based on the type of the data set given by the user
    if (this.chosen == 1) { // AA
      this.headNumber = 29; // no. of compounds in the data set
    } else if (this.chosen == 2) { // AC
      this.headNumber = 27; // no. of compounds in the data set
    } else if (this.chosen == 3) { // ACEXT
      this.headNumber = 35; // no. of compounds in the data set
    } else {
      throw new IllegalArgumentException("invalid input!! try again");
    }

    // created the 2D array to store the Excel representation
    combine = new String[limit + 1][headNumber];
  }

  /**
   * This is a method to read the data that uses a helper method to process, extract and initialize
   * the data into arraylists.
   *
   * @param filename filepath of the dataset that we want to extract data from
   * @return the 2D array that we get after the extraction of data; Excel data representation
   * @throws FileNotFoundException when the data path is not resulting in any usable data file
   */
  public String[][] readData(String filename) throws FileNotFoundException {
    try {
      // Scanner object to read the dataset
      Scanner scan = new Scanner(new File(filename)); // taking in the data from the file
      // ArrayList to store the whole data that I need the information from;
      ArrayList<String> data = new ArrayList<>();

      // calling the helper methods
      processData(scan, data);

      //      System.out.println(head);
      //      System.out.println(patientNames);
      //      System.out.println(responses);

      // extraction of the data completed
      // now we combine these ArrayLists into one 2D Array
      setCombine();
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Invalid file path! try again");
    }
    // returning the 2D array
    return combine;
  }

  /**
   * This is a helper method that takes in the scanner object and an ArrayList to trim the data and
   * return it and set the heading of the data set ino the head ArrayList. After achieving the data
   * it calls another helper method to extract information from the refined data extracted in this
   * method.
   *
   * @param scan scanner object that read the data file using the dat path
   * @param data An empty arraylist to store the usable data from the read dataset after data
   *             trimming
   * @return the Arraylist filled with usable data from the dataset
   */
  public void processData(Scanner scan, ArrayList<String> data) {

    //add "Name" column to the header; this comes before all compounds processed
    head.add("Name");
    // looping through all the lines of the data set
    while (scan.hasNextLine()) {
      // getting each line of the data set
      String line = scan.nextLine();
      if (line != "") {
        // skipping the lines with nothing in it
        // An Array to store each word of the current line separated by tab
        String[] word = line.split("\t");
        if (line.startsWith("Compound")) {
          // adding all lines that starts with 'compound' into head array
          head.add(line); // getting all the compound in the dataset
        }
        if (word.length > 1 && line.matches("^\\d.*")) {
          // only including the lines that have their length greater than one and start with a
          // number; gets added into the dat array that is returned back and used elsewhere.
          data.add(line);
        }
      }
    }
    // calling another helper method to finish the complete the extraction of data
    useData(data);
  }


  /**
   * This is a helper method to a helper method where we take in refined data and extract the
   * information that we want from it. we initialize the arraylist from the refined data array. We
   * are using a nested loop to go through each the lines (Rows) and values (Columns) in the refined
   * data. We want to match the pattern at the beginning of the line to a number to get all the
   * results related to one patient at a place. 'i' is also the number of patients in the data; we
   * start from 1 and go until the last patient and get all the results related to this patient at
   * one place to extract the information that we want and put it in the arraylists 'j' represents
   * the lines in the refined data; we go through each line of the refined data and match the number
   * 'i' and get the line if it matches and extract information form that line and place it in the
   * arraylist.
   *
   * @param data refined data from the data set
   */
  public void useData(ArrayList<String> data) {

    // the first loop is for the current patient number which is represented by 'i'
    for (int i = 1; i <= limit; i++) {
      // pattern to match the number at the beginning with the patient number.
      String pattern = "^" + i + "\\D";
      // The second loop goes through each line in the refined data using 'j' as index
      for (int j = 0; j < data.size(); j++) {
        if (data.get(j).matches(pattern + ".*")) { // matching

          // Array to store each line of the data ArrayList after the split as words
          String[] dataLine = data.get(j).split("\t");

          // checking for duplicates; only adding the unique names one after the other
          if (!(patientNames.contains(dataLine[1]))) {
            // adding the patients names to the arraylist
            patientNames.add(dataLine[1]);
          }
          // adding the responses to the arraylist; one compound after the other per one patient
          // to another until the last patient is reached in the refined data in chronological order
          responses.add(dataLine[dataLine.length - 1]);
        }
      }
    }
  }

  /**
   * This is a helper method to combine the Arraylists to one 2D array
   */
  public void setCombine() {

    //This loop is to set the header elements into the 2D Array at the top
    for (int i = 0; i < headNumber; i++) {
      combine[0][i] = head.get(i);
    }

    //The first loop is to go through each row at a time
    for (int i = 1, k = 0; i <= limit && k < responses.size(); i++) {
      // adding patient names at the beginning before adding in the values
      combine[i][0] = patientNames.get(i - 1);
      // the second loop goes through each column per row
      for (int j = 1; j < head.size(); j++, k++) {
        // setting in all the values for one patient and going to the next one
        combine[i][j] = responses.get(k);
      }
    }
  }

  /**
   *
   */
  public void finalResult() {
    if (this.chosen == 1) {

      
      //The first loop is to go through each row at a time
      for (int i = 1, k = 0; i <= limit && k < responses.size(); i++) {
        // adding patient names at the beginning before adding in the values
        combine[i][0] = patientNames.get(i - 1);
        // the second loop goes through each column per row
        for (int j = 1; j < head.size(); j++, k++) {
          // setting in all the values for one patient and going to the next one
          combine[i][j] = responses.get(k);
        }
      }


      System.out.println("AA");
    } else if (this.chosen == 2) {
      System.out.println("AC");
    } else if (this.chosen == 3) {
      System.out.println("ACEXT");
    }
  }

  /**
   * This is a helper method to print the elements in the 2D Array in the form of an Excel
   * representation. This method uses nested for loop and helps us to select each element at a time
   * in the 2d array starting from the left corner of the array
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
    String filepath2 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_AC.txt";
    String filepath3 = "C:\\Users\\jyoth\\IdeaProjects\\VijayRekha\\src\\08012024_ACEXT.txt";

    // creating the object of the class with the parameters
    VijayRekha result1 = new VijayRekha(limit + 4, 1);
    // the 2D Array for the AA dataset
    String[][] results1 = result1.readData(filepath1);
    // printing the 2D Array
    //    result1.print2DArray(results1);
    result1.finalResult();

    //    System.out.println("*************************************************************************");

    // creating the object of the class with the parameters
    VijayRekha result2 = new VijayRekha(limit + 4, 2);
    //     the 2D Array for the AC dataset
    String[][] results2 = result2.readData(filepath2);
    //     printing the 2D Array
    //    result2.print2DArray(results2);
    result2.finalResult();

    //    System.out.println("*************************************************************************");

    // creating the object of the class with the parameters
    VijayRekha result3 = new VijayRekha(limit + 4, 3);
    // the 2D Array for the ACEXT dataset
    String[][] results3 = result3.readData(filepath3);
    // printing the 2D Array
    //    result3.print2DArray(results3);
    result3.finalResult();

  }
}
