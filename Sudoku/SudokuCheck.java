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

public class SudokuCheck {


	/**
	 * Constructor for class SudokuCheck
	 */
	public SudokuCheck() {
	}

	/**
	 * To check each square efficiently, the for loop has been split into its own
	 * method, which can then be called independently of method check.
	 *
	 * sudokuForLoop iterates over one of the smaller squares of the sudoku matrix
	 * and returns a boolean value depending on whether the values of the square are unique.
	 *
	 * @param array is the values of the sudoku matrix
	 * @param colValue is the current column value of the square we are checking
	 * @param rowValue is the current row value of the square we are checking
	 * @return
	 */
	public static boolean sudokuForLoop(int[][] array, int colValue, int rowValue) {

		int squareFalse = 0;
		List<Integer> squareList = new ArrayList<Integer>(); // Creates an ArrayList to hold values already seen

		/**
		 * Loop looks through smaller square of sudoku matrix,
		 * if an element in the square doesn't conform to the parameters of Sudoku,
		 * the method returns false.
		 */
		for (int i = 0 + rowValue; i < 3 + rowValue; i++) {
			for (int j = 0 + colValue; j < 3 + colValue; j++) {

				// Checks that element value is between 0-9
				if (array[i][j] < 0 || array[i][j] > 9) {
					return false;
				}

				// current element in array is checked against the values of elements already checked
				// to make sure each element in a square is unique.
				// Ignore 0, because there can be more than one empty space
				// in a square.
				if (array[i][j] >= 1 && array[i][j] <= 9 && squareList.contains(array[i][j])) {
					return false; // Returns false if an element is not unique
				} else {
					squareList.add(array[i][j]); // Adds new value to an array list
				}

			}
		}
		return true; // Otherwise returns false
	}

	/**
	 *
	 * The method check takes a Sudoku object as an argument, and checks whether the elements
	 * of this object conform to the rules of Sudoku. Mainly, that each element is unique both
	 * in its row, column, and square.
	 *
	 * @param sudoku is the sudoku matrix object that is to be tested
	 * @return a boolean double array that tests that the sudoku object conforms to the three
	 * different ways a sudoku matrix can be tested.
	 */
	public static boolean[][] check(Sudoku sudoku) {

		// Creates a boolean array that will be returned
		boolean[][] results = new boolean[3][9];

		// Sets all values of boolean results to true
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				results[i][j] = true;
			}
		}

		/**
		 * Creates a new int array of the values of the object Sudoku
		 */
		int[][] array = new int[9][9];
		array = sudoku.getArray();

		/**
		 * @rowFalse a binary counter to check whether the rows of the sudoku matrix conform to standards
		 * (1 == false, 0 == true)
		 */
		int rowFalse = 0;
		List<Integer> rowList = new ArrayList<Integer>();

		/**
		 * @colFalse a binary counter to check whether the columns of the sudoku matrix conform to standards
		 * (1 == false, 0 == true)
		 */
		int colFalse = 0;
		List<Integer> colList = new ArrayList<Integer>();

		/**
		 * @squareFalse a binary counter to check whether the smaller
		 * square of the sudoku matrix conform to standards
		 * (1 == false, 0 == true)
		 */
		int squareFalse = 0;
		List<Integer> squareList = new ArrayList<Integer>();

		/**
		 * Loops through every element in the array
		 * if a row has a non-unique element, then the row will be returned as false,
		 * otherwise will be returned as true.
		 */
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				// Checks that element value is between 0-9
				if (array[i][j] < 0 || array[i][j] > 9) {
					rowFalse = 1;
				}

				/**
				 * Conditional statement checks whether the element is unique by
				 * creating an ArrayList that saves all the values of the row that have already been seen
				 * at the end of the for loop, this ArrayList is reset for the next row.
				 */
				if (array[i][j] >= 1 && array[i][j] <= 9 && rowList.contains(array[i][j])) {
					rowFalse = 1;
				} else {
					rowList.add(array[i][j]); // Adds new value to the ArrayList only if it's unique
				}
			}

			// Checks the row array and assigns false to rows that contain non-unique elements
			if (rowFalse >= 1) {
					results[0][i] = false;
					rowFalse = 0; // Resets value for next row
			}
			rowList.clear(); // Clears ArrayList for the next row
		}

		/**
		 * Loops through every element in the array
		 * if a column has a non-unique element, then the column will be returned as false,
		 * otherwise will be returned as true.
		 */
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {

				// Makes sure value is between 0-9
				if (array[i][j] < 0 || array[i][j] > 9) {
					colFalse = 1;
				}

				/**
				 * Conditional statement checks whether the element is unique by
				 * creating an ArrayList that saves all the values of the column that have already been seen
				 * at the end of the for loop, this ArrayList is reset for the next column.
				 */
				if (array[i][j] >= 1 && array[i][j] <= 9 && colList.contains(array[i][j])) {
					colFalse = 1;
				} else {
					colList.add(array[i][j]); // Only adds new value to ArrayList if it's unique
				}

			}
			// Checks the column array and assigns false to columns that contain non-unique elements
			if (colFalse == 1) {
				if (j == 0)  {
					results[1][0] = false;
					colFalse = 0; // Resets value for next column
				}
				results[1][j] = false;
				colFalse = 0; // Resets value for next column
			}

			colList.clear(); // Resets ArrayList for next column
		}

		/**
		 * Loops through every element in the array
		 * if a small square has a non-unique element, then the small square will be returned as false,
		 * otherwise will be returned as true.
		 */
		int rowExtra = 0;
		int colExtra = 0;

		/**
		 *  for loops through the 9 squares in groups of 3 using method
		 *  sudokuForLoop.
		 */
		for (int k = 0; k < 9; k++) {

			// loops through first row of smaller squares
			for (int l = 0; l < 3; l++) {
				rowExtra = 0;
				boolean value = sudokuForLoop(array, colExtra, rowExtra);
				results[2][l] = value;
				colExtra = colExtra + 3;
			}
			colExtra = 0;
			rowExtra = 0;

			// Loops through 2nd row of smaller squares
			for (int m = 0; m < 3; m++) {
				rowExtra = 3;
				boolean value = sudokuForLoop(array, colExtra, rowExtra);
				results[2][m + rowExtra] = value;
				colExtra = colExtra + 3;
			}
			colExtra = 0;
			rowExtra = 0;

			// Loops through 3rd row of smaller squares
			for (int n = 0; n < 3; n++) {
				rowExtra = 6;
				boolean value = sudokuForLoop(array, colExtra, rowExtra);
				results[2][n + rowExtra] = value;
				colExtra = colExtra + 3;
			}
			colExtra = 0;
			rowExtra = 0;
		}

		/**
		 * @returns a boolean array containing the results of method
		 */
		return results;
	}

	/**
	 * Main method to check that SudokuClass methods are working correctly.
	 */
	public static void main (String[] args) {

		  // Creates an array containing an uncompleted Sudoku matrix;
		  int[][] i = {{1,1,3,4,5,6,7,8,9},
				  	   {4,5,6,7,8,9,1,2,3},
					   {7,8,9,1,2,3,4,5,6},
					   {2,3,4,5,6,7,8,9,1},
					   {5,6,7,8,9,1,2,3,4},
					   {8,9,1,2,3,4,5,6,7},
					   {3,4,5,6,7,8,9,1,2},
					   {6,7,8,9,1,2,3,4,5},
					   {9,1,2,3,4,5,6,7,8}};

		  // Creates a Sudoku object
		  Sudoku a = new Sudoku(i);

		  // Prints Sudoku box
		  System.out.print(a.toString());

		  // Checks results
		  boolean[][] results = check(a);
		  System.out.println(Arrays.deepToString(results));

	}
}
