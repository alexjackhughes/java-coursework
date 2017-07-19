
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

/**
 * 
 * This class represents the data structure used to answer question 4.
 * 
 *  The tree has the following attributes:
 * - Support efficient search
 * - Support efficient insertion of new words
 * - Support finding words with just the prefix
 * - Each node has up to eight branches
 * - Each node contains all the words possible at that point
 * 
 * @author Alexander Jack Hughes
 */
public class TreeBuilder {

	/**
	 * List of RootNode's field variables:
	 */
	private TreeBuilder[] children = new TreeBuilder[8]; // Eight branches
	private int height;// Height of current tree
	protected boolean empty;
	
	// Collection of all words that signature could be
	// at this level
	Set<String> words = new TreeSet<String>();
	
	/**
	 * Constructor for a blank tree.
	 */
	public TreeBuilder() {
		this.height = 0;
		this.empty = true;
	}
	
	/**
	 * Constructor for a tree if height 
	 * of tree is given.
	 */
	 public TreeBuilder(int height) {
		this.height = height;
		this.empty = false;
	 }
	 
	 /**
	  * Getter method for words
	  * @return words
	  */
	 public Set<String> getWords() {
		 return this.words;
	 }
	 
	 /**
	  * Getter method for height
	  * @return words
	  */
	 public int getHeight() {
		 return this.height;
	 }
	 
	 /**
	  * Getter method for children
	  * @param i is index child to return (representing: 2-9)
	  * @return children
	  */
	 public TreeBuilder getChildren(int i) {
		return children[i];
	 }
	
	/**
	 * insertWords() takes an argument String, which
	 * it then inserts into the Collection of the tree.
	 * 
	 * @param word is a String corresponding to a line of the file words.txt
	 * @return No return. Edits the 'words' Collection
	 * 
	 */
		public void insertWords(String word) {

			// If we are not at the root node, then add a version of the 
			// current word that has been trimmed to same length as the
			// height of the current tree
			if (this.height != 0) {
				words.add(word.substring(0, height)); 
				// Only returns part of the word, i.e. word == length of signature
			}
			// Only continue if the word length is less than the length of the word
			if (word.length() > height) {
				
				// Find the tree index value for the word by finding the 
				// signature of the current character, and then returning
				// it as an integer
				String newString = wordsToSignature(Character.toString(word.charAt(height))); 
				int charValue = Integer.parseInt(newString) - 2;
				
				// If the indexed child hasn't been created yet, 
				// declare the child
				if (children[charValue] == null) {
					children[charValue] = new TreeBuilder(height + 1);
				}
				// Recursively adds the word to the indexed child
				children[charValue].insertWords(word);
			}
		}
	 
		  /**
		   * This method takes a word and returns a numeric signature. i.e. 
		   * the input "home" would return the signature "4663".
		   * 
		   * Any non-alphabetic character returns a space. 
		   * 
		   * @param word is a string to be interpreted
		   * @return a string version of the signature
		   */
		public static String wordsToSignature(String word) {

			  StringBuffer signature = new StringBuffer (""); // Variable to hold the signature
			  String[] wordChar = word.split("(?!^)"); // Splits word, then access array within loop
			  
			  // Iterates through each character assigning a value to each character
			  for (int i = 0; i < wordChar.length; i++) {
				  
				  // Access the element in the array
				  String a = wordChar[i].toLowerCase(); // Makes character Lowercase
				  
				  // Searches for the correct value for each character of the word
				  // and then appends it to a StringBuffer
				  if (a.equals("a") || a.equals("b") || a.equals("c")) { // a,b,c == 2
					  signature.append("2");
				  }
				  else if (a.equals("d") || a.equals("e") || a.equals("f")) { // d,e,f == 3
					  signature.append("3");
				  }
				  else if (a.equals("g") || a.equals("h") || a.equals("i")) { //g,h,i == 4
					  signature.append("4");
				  }
				  else if (a.equals("j") || a.equals("k") || a.equals("l")) { //j,k,l == 5
					  signature.append("5");
				  }
				  else if (a.equals("m") || a.equals("n") || a.equals("o")) { //m,n,o == 6
					  signature.append("6");
				  }
				  else if (a.equals("p") || a.equals("q") || a.equals("r") || a.equals("s")) { // p,q,r,s == 7
					  signature.append("7");
				  }
				  else if (a.equals("t") || a.equals("u") || a.equals("v")) { //t,u,v == 8
					  signature.append("8");
				  }
				  else if (a.equals("w") || a.equals("x") || a.equals("y") || a.equals("z")) { // w,x,y,z == 9
					  signature.append("9");
				  }
				// For any other character that's not a-z
				  else {
					  // If a space is already present, don't add an extra space
					  if (signature.charAt(signature.length() - 1) == ' ') {
						  // Do nothing so that extra spaces aren't added
					  }
					  else {
						  signature.append(" "); // Adds a space
					  }
				  }
			  }
			  return signature.toString(); // Returns Signature once for loop is complete
		}
	 
	 /**
	  * wordToSignature is used to convert an argument 'signature'
	  * into a collection of the possible words that match the signature.
	  * Unlike previous iterations, this method works with partial 'signatures'. 
	  * 
	  * @param signature is a String to convert
	  * @return the possible words of the argument returned as a set
	  */
	 public Set<String> signatureToWords(String signature) {
		
		 // If argument is empty, return the set of words
		 // If the argument was always empty, will return 
		 // an empty set.
			if (signature.isEmpty()) {
				return words;
			}
			//
			else {
				// Get character of signature and convert to an integer to use
				// as the index
				String newString = Character.toString(signature.charAt(0)); 
				int childIndex = Integer.parseInt(newString) - 2;
				
				// if the child tree isn't empty, then 
				// return the child tree recursively with the next character of
				// the signature
				if (children[childIndex] != null) {
					// Recall the method, deleting the first character
					return children[childIndex].signatureToWords(signature.substring(1));
				}
				// If child tree is empty, then
				// the word isn't in tree - 
				// return an illegal argument exception
				else {
					return words;
					//throw new IllegalArgumentException("Your word was not in the dictionary");
				}
			}
		}
	
	// Main Method:
	public static void main (String [] args) {
	}
}
