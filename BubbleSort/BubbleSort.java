/**
*
* @author Alexander Jack Hughes
*
* This code answers the question for ex3.
*
* The class BubbleSort takes an array and compares each number to the element to its left, 
* if the number is smaller, they swap positions.
* 
* After looping, the array is sorted from the smallest to biggest number.
*
**/

public class BubbleSort {
	
	// Constructor called automatically on new instance of class.
	BubbleSort() {
	}
	
	public static int[] bubbleSort(int[] numbers) {
		
		// Declares a placeholder variable to store one of the numbers in the array whilst we swap its value
		int temp = 0;
		
            //  i == counter - loops whilst i is smaller than length of the argument array
        	for(int i = 0; i < numbers.length; i++) {

        		// j == counter - loops whilst j is smaller than the length of the argument array
        		for(int j = 1; j < (numbers.length - i); j++) {
                    
        			// Only swaps elements if left element is bigger than right element
        			// i.e. if numbers[x-1] > numbers[x], these two elements swap places
        			if(numbers[j - 1] > numbers[ j ]) {
                        temp = numbers[j - 1];
                        numbers[j - 1] = numbers[j];
                        numbers[j] = temp;
                        }   
                    }
            }
        // Returns the sorted array
        return numbers;
		}
        
        // Main method
        public static void main(String[] args) {
    
        	// Creates an integer array that we will sort:
        	int numbers[] = new int[]{4, 3, 6, 1, 9, 2};
   
        	// Prints the unsorted array - n == a number in the array
        	System.out.println("Unsorted Array:");
        	for (int n : numbers) {
        	System.out.print(n + ", ");
        	}
    
        	// Sorts the array using bubble sort method
        	bubbleSort(numbers);
                  
        	// Prints the sorted Array using Enhanced for statement
        	System.out.println("");
        	System.out.println("Sorted Array:");
        	for (int n : numbers) {
        	System.out.print(n + ", ");
        	}
        }
}