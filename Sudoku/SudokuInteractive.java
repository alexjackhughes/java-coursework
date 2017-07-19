/**
*
* The sub-class SudokuInteractive allows a user to play a game of Sudoku, inputing values which will be
* displayed on the command line terminal.
*
* The method play allows users to input commands, as well as reset and exit the game. Users can only input
* new characters on empty spaces in the sudoku matrix.
*
* @author Alexander Jack Hughes
*
**/

import java.util.*; // Imports Scanner & arrays
import java.util.Scanner; // Specifically import scanner
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

public class SudokuInteractive extends Sudoku {

	// Field Variables
	private int[][] playerArray;

	public SudokuInteractive(int[][] array) { // new field variables entered here
		super(array); // Includes field variables from Super
		//this.fuelConsumption = fuelConsumption; // New field variable
		playerArray = new int[9][9];
		array = playerArray;
	}

	/**
	 * The method play creates a while loop within which the user can play a game of Sudoku
	 * using the values provided in the file argument. The values in the provided file will be
	 * used to create a Sudoku matrix, upon which users can add new values.
	 *
	 * @param file is the filename of where the Sudoku is located
	 */
	public static void play (String file) {

		/**
		 * Creates a scanner object to take user input
		 */
		Scanner sc = new Scanner(System.in);

		SudokuRead x = new SudokuRead(); // Creates an instance of the class from exercise 2

		/**
		 * Creates a new sudoku matrix using the values from the argument file
		 *
		 * @throws IOException if filename is incorrect or file is missing
		 */
		Sudoku sudoku = null;  // Initialises a new Sudoku object from file
		Sudoku pSudoku = null; // Initialises a sudoku object for player to change
		try {
			sudoku = x.readSudoku(file); // Creates a new Sudoku object from file
			pSudoku = x.readSudoku(file); // Creates a sudoku object for player to change
		} catch(IOException ex){
	        System.out.println (ex.toString());
	        System.out.println("Could not find this file: " + file);
	    }

		/**
		 * While loop contains the user interaction with the sudoku matrix
		 *
		 * @exit the while loop by typing exit
		 */
		int exitToggle = 0;
		int wrongInput = 0;
		char[] rows = {'a','b','c','d','e','f','g','h','i'};

		System.out.println(toString(sudoku, pSudoku)); // Prints the matrix one time
		while (exitToggle == 0) {

			/**
			 * Program asks user for input; can command to exit program,
			 *
			 * @input of user stored in variable command
			 */
			System.out.println("Please enter a command:");
			String command = sc.next();

			command = command.toLowerCase(); // makes all input lower-case
			command = command.replaceAll("\\s+",""); // gets rid of all spaces in input
			//System.out.println("You put: " + command); To test that the right input is being output

			/**
			 * @input move: Allows users to input the position and value of a new element
			 * using the short code letterNumber:value i.e. a1:5 means in position a1 put
			 * the value 5.
			 */
			if (command.matches("\\w\\d.\\d")) {

				System.out.println("Adding value to board (if possible):");
				char[] commandArray = command.toCharArray();

				// Checks what row the letter corresponds to, and assigns it to variable w
				int w = 0;
				for (int v = 0; v < 9; v++) {
					if (commandArray[0] == rows[v]) {
						w = v;
					}
				}

				int y = Character.getNumericValue(commandArray[1]); // Holds column value
				int z = Character.getNumericValue(commandArray[3]); // Hold value value

				// Makes sure the values are within the specified range
				if (w >= 0 && w < 9 && y >= 0 && y < 9 && z >= 0 && z < 9) {
					int[][] array = pSudoku.getArray();

					array[w][y-1] = z; // Assigns new value
					pSudoku.setArray(array); // updates the player sudoku array with new values
				}
				wrongInput = 1;
			}

			/**
			 * @input view: Allows user to see the current matrix, including their input
			 */
			if (command.matches("view")) {
				System.out.println(toString(sudoku, pSudoku));
				wrongInput = 1;
			}

			/**
			 * @input reset: resets the sudoku matrix
			 */
			if (command.matches("reset")) {
				System.out.println("You have reset the sudoku matrix:");
				exitToggle = 1; // Exits the while loop
				play(file); // Replays the method
			}

			/**
			 * @input help:
			 * If users asks for help, will print out a list of commands
			 */
			if (command.matches("help")) {
				System.out.println("You can use these commmands:");
				System.out.println("view, reset, exit.");
				System.out.println();
				wrongInput = 1;
			}

			/**
			 * @input exit: exits the while loop
			 *
			 * @input all: if user inputs any other command, the game will tell user
			 * that it does not recognize input
			 */
			if (command.matches("exit")) { // exits the game
				System.out.println("Thanks for playing!");
				exitToggle++;
			}

			if (wrongInput <= 0) { // All other input not covered will allow user to try a new input
				wrongInput = 1;
				System.out.println("Sorry, I don't recognize that input (Type 'help' if you're stuck).");
			}
			wrongInput = 0; // Resets wrong input for next loop
		}

	}

	/**
	 * @Overrides the super method toString
	 *
	 * This method includes emphasis on which elements of Sudoku can be changed, as well
	 * as an index like a chessboard for ease of playing
	 */
	public static String toString(Sudoku sudoku, Sudoku pSudoku) {

		int[][] array = sudoku.getArray(); // Gets the array values of argument object
		int[][] pArray = pSudoku.getArray(); // The Players Sudoku

		// Creates a String array to convert the array into strings.
		  String[][] a = new String[array[0].length][array.length];

		  // variable to temporarily hold int value within for loop
		  int k = 0;
		  String l = "";

		  /**
		   * Loop adds every element value of int[][] array, into new string[][] k
		   * for ease of printing.
		   *
		   * @return changes any element that contain 0, to a " " in the String array[][]
		   * Also, all values that aren't 0 will now have emphasis i.e. 2 will now be *2*
		   */
		  for (int i = 0; i < array.length; i++) {
			  for (int j = 0; j < array.length; j++) {

				  // Checks if element contains 0;
				  if (array[i][j] == 0) {
					  a[i][j] = "   ";
					  /**
					   * Checks that the player array is the same as normal array
					   * if not, adds the correct value in
					   */
					  if (pArray[i][j] != 0) {
						  l = String.valueOf(pArray[i][j]);
						  a[i][j] = " " + l + " ";
					  }
				  } else if (array[i][j] != 0) {
					  k = array[i][j];
					  a[i][j] = "*" + String.valueOf(k) + "*"; // Converts array element to a string
				  }

			  }
		  }
				/**
			  	 * @return Returns a 9 x 9 quadratic structure that includes the values of
			  	 * the elements in the field variable array.
			  	 */
				return
				"      1   2   3    4   5   6    7   8   9   \n" +
				"   ++===+===+===++===+===+===++===+===+===++\n" +
				"a  ||"+ a[0][0] +"|"+ a[0][1] +"|"+ a[0][2] +"||"+ a[0][3] +"|"+ a[0][4] +"|"+ a[0][5] +"||"+ a[0][6] +"|"+ a[0][7] +"|"+ a[0][8] +"||\n" +
				"   ++---+---+---++---+---+---++---+---+---++\n" +
				"b  ||"+ a[1][0] +"|"+ a[1][1] +"|"+ a[1][2] +"||"+ a[1][3] +"|"+ a[1][4] +"|"+ a[1][5] +"||"+ a[1][6] +"|"+ a[1][7] +"|"+ a[1][8] +"||\n" +
				"   ++---+---+---++---+---+---++---+---+---++\n" +
				"c  ||"+ a[2][0] +"|"+ a[2][1] +"|"+ a[2][2] +"||"+ a[2][3] +"|"+ a[2][4] +"|"+ a[2][5] +"||"+ a[2][6] +"|"+ a[2][7] +"|"+ a[2][8] +"||\n" +
				"   ++===+===+===++===+===+===++===+===+===++\n" +
				"d  ||"+ a[3][0] +"|"+ a[3][1] +"|"+ a[3][2] +"||"+ a[3][3] +"|"+ a[3][4] +"|"+ a[3][5] +"||"+ a[3][6] +"|"+ a[3][7] +"|"+ a[3][8] +"||\n" +
				"   ++---+---+---++---+---+---++---+---+---++\n" +
				"e  ||"+ a[4][0] +"|"+ a[4][1] +"|"+ a[4][2] +"||"+ a[4][3] +"|"+ a[4][4] +"|"+ a[4][5] +"||"+ a[4][6] +"|"+ a[4][7] +"|"+ a[4][8] +"||\n" +
				"   ++---+---+---++---+---+---++---+---+---++\n" +
				"f  ||"+ a[5][0] +"|"+ a[5][1] +"|"+ a[5][2] +"||"+ a[5][3] +"|"+ a[5][4] +"|"+ a[5][5] +"||"+ a[5][6] +"|"+ a[5][7] +"|"+ a[5][8] +"||\n" +
				"   ++===+===+===++===+===+===++===+===+===++\n" +
				"g  ||"+ a[6][0] +"|"+ a[6][1] +"|"+ a[6][2] +"||"+ a[6][3] +"|"+ a[6][4] +"|"+ a[6][5] +"||"+ a[6][6] +"|"+ a[6][7] +"|"+ a[6][8] +"||\n" +
				"   ++---+---+---++---+---+---++---+---+---++\n" +
				"h  ||"+ a[7][0] +"|"+ a[7][1] +"|"+ a[7][2] +"||"+ a[7][3] +"|"+ a[7][4] +"|"+ a[7][5] +"||"+ a[7][6] +"|"+ a[7][7] +"|"+ a[7][8] +"||\n" +
				"   ++---+---+---++---+---+---++---+---+---++\n" +
				"i  ||"+ a[8][0] +"|"+ a[8][1] +"|"+ a[8][2] +"||"+ a[8][3] +"|"+ a[8][4] +"|"+ a[8][5] +"||"+ a[8][6] +"|"+ a[8][7] +"|"+ a[8][8] +"||\n" +
				"   ++===+===+===++===+===+===++===+===+===++\n";
	}

	/**
	 * Main method used to test code of SudokuInteractive and begin the play loop
	 */
	public static void main ( String[] args ) {

		  // Play Sudoku
		  String file = "sudokufile.txt"; // Name of the Sudoku file being read in
		  play(file);
	}
}
