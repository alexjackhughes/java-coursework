import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/** Private Tests for all Sudoku Exercises (1-5).
 * 
 * @author Alexander Jack Hughes.
 *
 * Note: No tests for Ex4 as it's a question about user input.
 */

public class SudokuTest {

	private Sudoku a; // Initializes Student Variables

	@Before
	public void setUpBeforeClass() {
		
		  int[][] i = {{2,3,4,5,6,7,0,9,0},
			  	   {4,5,6,7,8,9,1,2,0},
				   {7,8,9,1,2,3,4,5,0},
				   {2,3,4,0,6,7,8,9,1},
				   {5,6,7,8,9,1,0,3,4},
				   {8,9,1,2,0,4,5,6,7},
				   {3,4,0,6,7,8,9,1,2},
				   {6,0,8,9,1,2,3,4,5},
				   {0,1,2,3,4,5,6,7,8}};
	  
		// Sets a new Sudoku object - inherently tests that the Sudoku constructor works
		a = new Sudoku(i);
	}
	
	@Test
	// Checks that the set and get methods work for class Employee
	public void testEx1() { 
	
		  int[][] i = {{2,3,4,5,6,7,0,9,0},
			  	   {4,5,6,7,8,9,1,2,0},
				   {7,8,9,1,2,3,4,5,0},
				   {2,3,4,0,6,7,8,9,1},
				   {5,6,7,8,9,1,0,3,4},
				   {8,9,1,2,0,4,5,6,7},
				   {3,4,0,6,7,8,9,1,2},
				   {6,0,8,9,1,2,3,4,5},
				   {0,1,2,3,4,5,6,7,8}};
		  
		assertArrayEquals(i, a.getArray()); // is depreciated - checks getter works
		assertFalse(a.isFilled()); // Tests method isFilled
	}
	
	@Test
	public void testEx2() throws IOException {
		
		// Matches the array in file sudokufile.txt
		  int[][] i = {{2,3,4,5,6,7,0,9,0},  
		   {4,5,6,7,8,9,1,2,0},
		   {7,8,9,1,2,3,4,5,0},
		   {2,3,4,0,6,7,8,9,1},
		   {5,6,7,8,9,1,0,3,4},
		   {8,9,1,2,0,4,5,6,7},
		   {3,4,0,6,7,8,9,1,2},
		   {6,0,8,9,1,2,3,4,5},
		   {1,2,3,4,5,6,7,8,0}};

		
		SudokuRead x = new SudokuRead();
		String fileName = "sudokufile.txt";
		
		try {
			Sudoku b = x.readSudoku(fileName);
			assertArrayEquals(i, b.getArray());
		} catch (IOException e) {
			throw new IOException("Threw an IOException: File is not accessible");
		}
	}
	
	@Test
	public void testEx3() {
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
		  
		  // Checks results
		  boolean[][] results = SudokuCheck.check(a);
		  
		  // True and False to test that boolean array doesn't just provide one result
		  assertTrue(results[0][2]); // Tests row
		  assertTrue(results[1][2]); // Tests column
		  assertTrue(results[2][1]); // Test Square
		  assertFalse(results[0][0]); // Tests row
		  assertFalse(results[1][1]); // Tests column
		  assertFalse(results[2][0]); // Test Square

	}
	
	@Test
	public void testEx5() {
		
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
		  
		  int[][] j = {{1,2,3,4,5,6,7,8,9},
		  	       {4,5,6,7,8,9,1,2,3},
			       {7,8,9,1,2,3,4,5,6},
			       {2,3,4,5,6,7,8,9,1},
			       {5,6,7,8,9,1,2,3,4},
			       {8,9,1,2,3,4,5,6,7},
			       {3,4,5,6,7,8,9,1,2},
			       {6,7,8,9,1,2,3,4,5},
			       {9,1,2,3,4,5,6,7,8}};
		  
		  Sudoku a = new Sudoku(i);
		  SudokuSolve.solve(a);
		  Sudoku b = new Sudoku(a.getArray());
		  
		  // Tests that the corrected array is equal to the correct array
		  assertArrayEquals(j, b.getArray());
	}
}