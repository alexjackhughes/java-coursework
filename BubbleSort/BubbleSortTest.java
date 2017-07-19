import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Alexander Jack Hughes
 * 4 tests to check whether bubbleSort produces the correct result.
 */

public class BubbleSortTest {

	@Test
	public void myTest1 () {
		
		// Create an array to test
		int[] a = {3, 5, 2, 6, 7, 1, 8};
		
		int[] expected = {1,2,3,5,6,7,8}; // Expected Value
		int[] actual = BubbleSort.bubbleSort(a); // Returns the method bubbleSort on array a
		
		assertArrayEquals(expected, actual); // Are expected and actual equal?
	}
	
	@Test
	public void myTest2 () {
		
		// Create an array to test
		int[] a = {10, 4, 9, 6, 2, 1, 3, 33, 5};
		
		int[] expected = {1, 2, 3, 4, 5, 6, 9, 10, 33}; // Expected Value
		int[] actual = BubbleSort.bubbleSort(a); // Returns the method bubbleSort on array a
		
		assertArrayEquals(expected, actual); // Are expected and actual equal?
	}
	
	@Test
	public void myTest3 () {
		
		// Create an array to test
		int[] a = {4, 7, 2, 1, 3, 5, 8, 6, 9, 10, 11};
		
		int[] expected = {1,2,3,4,5,6,7,8,9,10,11}; // Expected Value
		int[] actual = BubbleSort.bubbleSort(a); // Returns the method bubbleSort on array a
		
		assertArrayEquals(expected, actual); // Are expected and actual equal?
	}
	
	@Test
	public void myTest4 () { // This test checks that if the array is already sorted, method does nothing
		
		// Create an array to test 
		int[] a = {1, 2, 3, 4, 5, 6, 7,8, 9, 10, 11, 12, 13};
		
 		int[] expected = {1, 2, 3, 4, 5, 6, 7,8, 9, 10, 11, 12, 13}; // Expected Value
		int[] actual = BubbleSort.bubbleSort(a); // Returns the method bubbleSort on array a
		
		assertArrayEquals(expected, actual); // Are expected and actual equal?
	}
}
