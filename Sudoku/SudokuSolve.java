/**
*
* The class SudokuSolve takes a Sudoku object and solves any empty spaces by trying a value,
* then checking that the value doesn't conflict with any other values.
*
* Followed the coding methodology from: https://rafal.io/posts/solving-sudoku-with-dancing-links.html
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

public class SudokuSolve {

	/**
	 * Constructor of class SudokuRead
	 */
	public SudokuSolve() {
	}

	/**
	 * The method solve takes a sudoku matrix as an argument, and solves any empty spaces it has
	 * according to the rules of sudoku.
	 *
	 * If there are no empty spaces, will return the completed sudoku matrix.
	 *
	 * @param sudoku is the sudoku object that you want to solve
	 */
	public static void solve (Sudoku sudoku) {
		// Gets the values of the object instance
		int[][] array = sudoku.getArray();

		// Calls the search method to find the correct values
		search(array);
	}

	/**
	 * The method getFreeCellList checks to find all 0 elements, so the program knows which
	 * elements it needs to repalces, and returns them in an int[][] array
	 *
	 * @param grid is the array of values for the sudoku matrix
	 * @return an int[][] array of all empty spaces in the sudoku matrix
	 */
	public static int[][] FreeCellList(int[][] array) {

	// Determines the number of free cells
		int freeCells = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

					 if (array[i][j] == 0) {
						 freeCells++;
					 }
			}
		}

		// Stores the free cell positions into the freeCellList
		int[][] freeCellList = new int[freeCells][2];
		int counter = 0;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (array[i][j] == 0) {
					freeCellList[counter][0] = i;
					freeCellList[counter++][1] = j;
				}
			}
		}

	return freeCellList;

	}

		 /**
		  * The method search takes the values of the Sudoku matrix as an argument, and trials
		  * a new unique number in each of the "empty" positions. It then checks whether the guessed number
		  * in the empty positon is correct, and if so, moves on to the next empty space. If not, it will go back
		  * and try the empty slot with the next biggest number that's available.
		  *
		  * @param array of the values of the Sudoku object
		  * @return a boolean value so that the program knows when to move on to the next position.
		  * true means next element, false means go back.
		  */
		 public static boolean search(int[][] array) {

			 int[][] freeCellList = FreeCellList(array); // Gets the free cells of an array

			 if (freeCellList.length == 0) {
				 return true; // Checks there are free cells
			 }

			 int k = 0; // Start from the first free cell

			 while (true) {
				 int i = freeCellList[k][0];
				 int j = freeCellList[k][1];

				 if (array[i][j] == 0) {
					 array[i][j] = 1; // Fill the free cell with number 1
				 }

				 if (isValid(i, j, array)) {
					 if (k + 1 == freeCellList.length) { // There are no more free cells in array
						 return true; // A solution is found
					 } else { // Move to the next free cell
						 k++;
					 }

				 } else if (array[i][j] < 9) {
					 // Fill the free cell with the next possible value
					 array[i][j] = array[i][j] + 1;

				 } else { // free cell grid[i][j] is 9, backtrack
					 while (array[i][j] == 9) {
						 if (k == 0) {
							 return false; // No possible value
						 }
						 array[i][j] = 0; // Reset to free cell
						 k--; // Backtrack to the preceding free cell
						 i = freeCellList[k][0];
						 j = freeCellList[k][1];
					 }
					 // Fill the free cell with the next possible value,
					 // search continues from this free cell at k
					 array[i][j] = array[i][j] + 1;
				 }
			 }
		 }

		 /**
		  * The method isValid checks the current position of guessed value using 3 different tests.
		  * It tests whether it's a unique value in the row, in the column, and in the smaller square.
		  *
		  * @param i is the current column number of the sudoku matrix
		  * @param j is the current row number of the sudoku matrix
		  * @param array is the int[][] containing the values of the sudoku matrix
		  *
		  * @return returns a boolean value depending on whether the guessed answer is correct
		  * in its current position
		  */
		 public static boolean isValid(int i, int j, int[][] array) {

			 // Check whether grid[i][j] column is valid
			 for (int column = 0; column < 9; column++) {
				 if (column != j && array[i][column] == array[i][j]) {
					 return false;
				 }
			 }

			 // Check whether grid[i][j] row is valid
			 for (int row = 0; row < 9; row++) {
				 if (row != i && array[row][j] == array[i][j]) {
					 return false;
				 }
			 }

			 // Should also check whether subsquares are valid or not (return false)

		 // If nothing returns false, then the current value is currently correct
			 return true;
		 }

	/**
	 * Main method of class to test method - Creates a Sudoku object and tests
	 * method of SudokuSolve
	 */
	public static void main ( String[] args ) {

		  // Below code creates a sudoku matrix with empty spaces and solves it
		  int[][] i = {{1,2,3,4,5,6,7,8,9},
			  	       {4,5,6,7,8,9,1,0,3},
				       {7,0,9,1,2,3,4,5,6},
				       {2,3,4,5,6,7,8,9,1},
				       {5,6,7,8,9,1,2,3,4},
				       {8,9,1,2,3,4,5,6,7},
				       {3,4,5,6,7,8,9,1,2},
				       {6,7,8,9,1,2,3,4,5},
				       {9,1,2,3,4,5,6,7,0}};

		  // Prints two sudoku matrixes; one incomplete, and one completed with solver
		  System.out.println("Given Sudoku:");
		  Sudoku a = new Sudoku(i);
		  System.out.print(a.toString());
		  solve(a); // Solves the Sudoku matrix
		  System.out.println("");
		  System.out.println("Completed Sudoku:");
		  Sudoku b = new Sudoku(a.getArray());
		  System.out.print(b.toString());

		  System.out.println(Math.max(5, 5));
	}
}
