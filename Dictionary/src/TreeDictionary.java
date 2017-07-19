
import java.util.*;
import java.io.*;

/**
 *
 * This code answers question 4, Work Sheet 3.
 *
 * The tree is not stores in a new data structure specific to the predictive
 * text problem, with the following attributes:
 * - Support efficient search
 * - Support efficient insertion of new words
 * - Support finding words with just the prefix
 * - Each node has up to eight branches
 * - Each node contains all the words possible at that point
 *
 * @author Alexander Jack Hughes
 **/
public class TreeDictionary implements Dictionary {

	/**
	 * field variables of class:
	 */
	private String path; // path to the words.txt
	private TreeBuilder tree; // Initializes the tree
	
	/**
	 * Constructor of class:
	 * Builds a tree in memory using the 
	 * argument path to the dictionary file.
	 */
	public TreeDictionary(String path) {
		this.path = path;
		this.tree = constructTree(path); // Declares a new tree
		
	}
	
	/**
	 * Helper method to construct a tree from the TreeBuilder
	 * class, using the argument path
	 */
	public TreeBuilder constructTree(String path) {
		
		TreeBuilder treeBuild = new TreeBuilder(); // Declare a new tree
		
		// Try and read the file and insert words
		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			
			String word; // Variable to hold one word from the dictionary
			// Then, iterate through the dictionary file adding each word to the TreeMap
			while ((word = br.readLine()) != null) {
				    
				if (isValidWord(word)) { // if word is valid
					word = word.replaceAll("\\s",""); // Delete any white space in string
					word = word.toLowerCase(); // Make word Lowercase
					treeBuild.insertWords(word); // Inserts words into tree
				}
			}	
		}
		catch(IOException ex) { // If file can't be found throw exception
        	System.out.println (ex.toString());
        	System.out.println("File not found.");
		}
		return treeBuild; // Returns tree filled with words from words.txt
	}

	/**
	 * Calls the method in TreeBuilder of the same method.
	 * 
	 * @param takes a String signature
	 * @return a set of possible words that match argument string 
	 */
	@Override
	public Set<String> signatureToWords(String signature) {
		return tree.signatureToWords(signature);
	}
	
	/**
	 * 
	 * wordToSignature reuses the code from the prototype.
	 * 
	 * @param word is a word to be translated into a signature
	 * @return the signatures of all the 'words' as a string 
	 */
	public static String wordsToSignature(String word) {
		return TreeBuilder.wordsToSignature(word); // Re-uses prototype code
	}
	
	/**
	 * Validates that the method is working properly.
	 * 
	 * @param word
	 * @return
	 */
	private boolean isValidWord(String word) {
		char[] charArray = word.toCharArray();
		for (char c : charArray) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}
	
	// Main method:
	public static void main(String [] args) {
		
		String path = "src/predictive/words.txt"; // Path to the dictionary
    	TreeDictionary x = new TreeDictionary(path); // Creates the object

    	// Tests the methods:
    	System.out.println(x.wordsToSignature("indubitable")); // Works
    	System.out.println(x.signatureToWords("2")); // Works
	}
}
