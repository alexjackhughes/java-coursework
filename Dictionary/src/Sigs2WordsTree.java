

/**
 * 
 * Main method for question 4.
 * 
 * Sigs2WordsTree is used for calling the signatureToWords method. Within this method, we
* ignore any words with non-alphabetic characters given in the input.
 * 
 * 
 * For the average-length article, this method takes: 677 Milliseconds.
 * This is considerably faster than the Prototype class, but less fast that the List
 * class and the tree class. However, if you consider it's utility (i.e. it returns partial
 * words as well as full matches), this is obviously the superior version.
 * 
 * @author Alexander Jack Hughes
 */
public class Sigs2WordsTree {

    // Main method called from the command line - i.e. "Sigs2Words 2222"
    public static void main(String[] args) {

    	int b = (int) System.currentTimeMillis();
    	
    	String path = "src/words.txt"; // Path to the dictionary
    	TreeDictionary x = new TreeDictionary(path); // Creates the object
    	
    	// For loop iterates through the method:
        for (String s: args) {
            System.out.println(s + ": " + x.signatureToWords(s)); // Calls Method
        }
        
        
        int c = (int) System.currentTimeMillis();
        
        System.out.println("Time taken in Milliseconds:");
        System.out.println(c-b);
    }
}
