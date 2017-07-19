/**
*
* The toString method prints out the values of the Sudoku object in the format provided. If there
* is a 0 in the values (representing a don't know), is replaced by a space when printed.
*
* isFilled checks whether there is any empty spaces in the object, if there is returns false.
*
* @author Alexander Jack Hughes
*
**/

import java.util.*; // Imports Java library
public class Sudoku {

	  private int[][] array; // Contains all the values of Sudoku matrix

	  /**
	   * This constructor creates a
	  * @param array is all the values of the sudoku object
	  */
	  public Sudoku(int[][] array) {
	    this.array = array;
	  }


	// All Methods for class will be listed below:
	  /**
	   * Getter for field variable array
	   * @return the array of object
	   */
	  public int[][] getArray() {
		  return this.array;
	  }

	  /**
	   * Setter for field variable array
	   * @param changes the array to provided argument
	   */
	  public void setArray(int[][] array) {
		  this.array = array;
	  }

	  /**
	   * @return returns a Sudoku chart in the format provided, with the values of the array
	   * if there are any "0" elements in the array - these will be left empty instea.
	   */
	  public String toString() {

		  // Creates a String array to convert the array into strings.
		  String[][] a = new String[array[0].length][array.length];

		  // variable to temporarily hold int value within for loop
		  int k = 0;

		  /**
		   * Loop adds every element value of int[][] array, into new string[][] k
		   * for ease of printing.
		   *
		   * @return changes any element that contain 0, to a " " in the String array[][]
		   */
		  for (int i = 0; i < array.length; i++) {
			  for (int j = 0; j < array.length; j++) {

				  // Checks if element contains 0;
				  if (array[i][j] == 0) {
					  a[i][j] = " ";
				  } else {
					  k = array[i][j];
					  a[i][j] = String.valueOf(k); // Converts array element to a string
				  }

			  }
		  }

		  	/**
		  	 * @return Returns a 9 x 9 quadratic structure that includes the values of
		  	 * the elements in the field variable array.
		  	 */
			return
			"++===+===+===++===+===+===++===+===+===++\n" +
			"|| "+ a[0][0] +" | "+ a[0][1] +" | "+ a[0][2] +" || "+ a[0][3] +" | "+ a[0][4] +" | "+ a[0][5] +" || "+ a[0][6] +" | "+ a[0][7] +" | "+ a[0][8] +" ||\n" +
			"++---+---+---++---+---+---++---+---+---++\n" +
			"|| "+ a[1][0] +" | "+ a[1][1] +" | "+ a[1][2] +" || "+ a[1][3] +" | "+ a[1][4] +" | "+ a[1][5] +" || "+ a[1][6] +" | "+ a[1][7] +" | "+ a[1][8] +" ||\n" +
			"++---+---+---++---+---+---++---+---+---++\n" +
			"|| "+ a[2][0] +" | "+ a[2][1] +" | "+ a[2][2] +" || "+ a[2][3] +" | "+ a[2][4] +" | "+ a[2][5] +" || "+ a[2][6] +" | "+ a[2][7] +" | "+ a[2][8] +" ||\n" +
			"++===+===+===++===+===+===++===+===+===++\n" +
			"|| "+ a[3][0] +" | "+ a[3][1] +" | "+ a[3][2] +" || "+ a[3][3] +" | "+ a[3][4] +" | "+ a[3][5] +" || "+ a[3][6] +" | "+ a[3][7] +" | "+ a[3][8] +" ||\n" +
			"++---+---+---++---+---+---++---+---+---++\n" +
			"|| "+ a[4][0] +" | "+ a[4][1] +" | "+ a[4][2] +" || "+ a[4][3] +" | "+ a[4][4] +" | "+ a[4][5] +" || "+ a[4][6] +" | "+ a[4][7] +" | "+ a[4][8] +" ||\n" +
			"++---+---+---++---+---+---++---+---+---++\n" +
			"|| "+ a[5][0] +" | "+ a[5][1] +" | "+ a[5][2] +" || "+ a[5][3] +" | "+ a[5][4] +" | "+ a[5][5] +" || "+ a[5][6] +" | "+ a[5][7] +" | "+ a[5][8] +" ||\n" +
			"++===+===+===++===+===+===++===+===+===++\n" +
			"|| "+ a[6][0] +" | "+ a[6][1] +" | "+ a[6][2] +" || "+ a[6][3] +" | "+ a[6][4] +" | "+ a[6][5] +" || "+ a[6][6] +" | "+ a[6][7] +" | "+ a[6][8] +" ||\n" +
			"++---+---+---++---+---+---++---+---+---++\n" +
			"|| "+ a[7][0] +" | "+ a[7][1] +" | "+ a[7][2] +" || "+ a[7][3] +" | "+ a[7][4] +" | "+ a[7][5] +" || "+ a[7][6] +" | "+ a[7][7] +" | "+ a[7][8] +" ||\n" +
			"++---+---+---++---+---+---++---+---+---++\n" +
			"|| "+ a[8][0] +" | "+ a[8][1] +" | "+ a[8][2] +" || "+ a[8][3] +" | "+ a[8][4] +" | "+ a[8][5] +" || "+ a[8][6] +" | "+ a[8][7] +" | "+ a[8][8] +" ||\n" +
			"++===+===+===++===+===+===++===+===+===++\n";
		}

	  /**
	   *
	   * A method to check whether a Sudoku matrix has been completed or not.
	   *
	   * @return a boolean value -
	   * if the Sudoku is filled, returns true.
	   * if the Sudoku is incomplete, returns false.
	   */
	  public boolean isFilled() {

		  /**
		   * Loop checks every element in the Sudoku matrix
		   * to see whether any element are empty
		   */
		  for (int i = 0; i < array[0].length; i++) {
			  for (int j = 0; j < array.length; j++) {

				  if (array[i][j] == 0) {
					  return false; // method returns false if any element == 0;
				  }

			  }
		  }
		  return true; // method returns true if array does not equal 0;
	  }

	 /**
	  *
	  *  Main method creates an array of values, and passes them to create
	  *  a new Sudoku object.
	  *
	  *  Object then tests methods of class Sudoku.
	  *
	  */
	  public static void main ( String[] args ) {

		  // Creates an array containing an uncompleted Sudoku matrix;
		  int[][] i = {{2,3,4,5,6,7,0,9,0},
				  	   {4,5,6,7,8,9,1,2,0},
					   {7,8,9,1,2,3,4,5,0},
					   {2,3,4,0,6,7,8,9,1},
					   {5,6,7,8,9,1,0,3,4},
					   {8,9,1,2,0,4,5,6,7},
					   {3,4,0,6,7,8,9,1,2},
					   {6,0,8,9,1,2,3,4,5},
					   {0,1,2,3,4,5,6,7,8}};

		  // Creates a Sudoku object
		  Sudoku a = new Sudoku(i);

		  // Prints Sudoku box
		  System.out.print(a.toString());

		  // Checks whether Sudoku box is filled
		  System.out.print(a.isFilled());
	  }
}
