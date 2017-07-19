/**
*
* The class SudokuRead contains a single method used to read in a file, and translate it
* into a Sudoku matrix object.
*
* The method readSudoku takes as an argument a string that is the filename containing the Sudoku matrix.
* The method reads this file, translates it into an array if the numbers are within the acceptable parameters
* (1-9), or if there is a space (translated as a 0). Finally, the method creates a Sudoku object using the
* array as an argument.
*
* @author Alexander Jack Hughes
*
**/

import java.util.*; // Imports Scanner & arrays
import java.io.*; // Imports input/output library
import java.net.*; // Other imports
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.*; // Imports split
import java.util.regex.Matcher; // Imports regex matcher
import java.util.regex.Pattern; // Imports regex pattern
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuRead {

	/**
	 * Constructor of class SudokuRead
	 */
	public SudokuRead() {
	}

	/**
	 *
	 * Method reads the file corresponding to the argument filename, converts the file into a Sudoku
	 * matrix, and then returns the Sudoku matrix object.
	 *
	 * @param fileName is the name of the file containing the values for the Sudoku matrix
	 * @return returns a Sudoku matrix.
	 * @throws IllegalArgumentException if the file containing values not between (0-9)
	 * @throws IOException if the file is not accessible.
	 */
	public static Sudoku readSudoku(String fileName) throws IllegalArgumentException, IOException {

		/**
		 *
		 * @return a variable called file that is a string of the entire file document
		 *
		 *  @throws IOException if file isn't readable
		 */
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			String file = new String(encoded);

		/**
		 * @stringArray to hold the values of the string when we split it into single elements
		 * @fileArray to eventually hold the elements as integers
		 */
		int[][] fileArray = new int[9][9];
		String[][] stringArray = new String[9][9];

		/**
		 * @lines splits the file string by line
		 * @temp array to hold the string when split from line
		 */
		String[] lines = file.split("\\r?\\n");
		String[] temp = new String[8];

		/**
		 * Splits each line of lines[] into individual elements
		 * @return
		 */
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				temp = lines[i].split("(?!^)"); // Split the array
				stringArray[i] = temp; // Assigns array of strings to array

			}
		}

		/**
		 * Checks for empty space in elements and puts a "0" instead
		 * Also throws exception if characters other than space and 0-9 are used
		 *
		 * @throws IllegalArgumentException if input is not between 0-9
		 */
		  for (int i = 0; i < 9; i++) {
			  for (int j = 0; j < 9; j++) {

				  // Checks if element contains 0;
				  if (stringArray[i][j].matches(" ")) {
					  stringArray[i][j] = "0";
				  }

				  // Checks that characters are only numbers between 1-9
				  if (stringArray[i][j].matches("[^0-9]")) {
					  throw new IllegalArgumentException("Input must be between 0-9:");
				  }
			  }
		  }

		  /**
		   * @returns an int array of all elements, parsed from String to Integer
		   */
		  for (int i = 0; i < 9; i++) {
			  for (int j = 0; j < 9; j++) {

				  	fileArray[i][j] = Integer.parseInt(stringArray[i][j]);
			  }
		  }

		/**
		 * Creates a Sudoku object and passes the int array as it's field values
		 */
		Sudoku a = new Sudoku(fileArray);

		/**
		 * @returns the Sudoku matrix
		 */
		return a;

		// If filename or file path is incorrect will return an IOException
		} catch (IOException e) {
			throw new IOException("Threw an IOException: File is not accessible");
		}
	}

	/**
	 *
	 * Main method of class to test method - Creates a Sudoku object and tests
	 * method of SudokuRead
	 *
	 */
	public static void main ( String[] args ) {

		SudokuRead x = new SudokuRead();

		String file = "sudokufile.txt"; // Name of the Sudoku file being read in

		try {
			Sudoku a = x.readSudoku(file); // Creates a new Sudoku object from file
			System.out.print(a.toString());
			System.out.print(a.isFilled());
		} catch(IOException ex){
	        System.out.println (ex.toString());
	        System.out.println("Could not find this file " + file);
	    }
	}
}
