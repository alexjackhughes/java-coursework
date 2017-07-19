import java.util.*;
import java.io.*;

/**
 * 
 * @author Alexander Jack Hughes
 * 
 * This DictionaryModel is the answer for WorkSheet 4.
 * 
 * DictionaryModel is used in the predictive text GUI to 
 * replicate the predictive text feature utilized on mobile
 * phones in the early 21st century.
 */
public class DictionaryModel extends Observable implements DictionaryModelInterface {

	/**
	 * Field Variables for DictionaryModel located below:
	 */
	private String path; // path to the words.txt file
	private TreeDictionary tree; // Tree Dictionary data structure
	
	private String message; // Full saved message
	private String signature; // The current signature being written
	private String word; // Current word being translated

	private Set<String> potentialWords; // A list of the potential
	private ArrayList<String> wordArray; // list of potential words as an array
	private List<String> currentMessageList; // Full message including word
	private int index; // counter used to select next potential word
	
	/**
	 * Constructors for class:
	 * The first constructor takes the path name of a dictionary file 
	 * and initializes the internal data structures
	 */
	public DictionaryModel(String dictionaryFile) throws IOException {
		this.path = dictionaryFile; // Path to dictionary file
		this.tree = new TreeDictionary(path); // Creates the tree
		
		signature = ""; // Signature to translate to word
		word = ""; // Chosen word
		message = "";
		
		currentMessageList = new ArrayList<String>();
		potentialWords = new TreeSet<String>();
		wordArray = new ArrayList<String>();
		
		index = 0;
	}
	
	/**
	 * Constructor for class:
	 * Uses a default dictionary file of my choice.
	 */
	public DictionaryModel() throws IOException {
    	this.path = "src/words.txt"; // Path to the dictionary
    	this.tree = new TreeDictionary(path); // Creates the tree
    	
		signature = ""; // Signature to translate to word
		word = ""; // Chosen word
		message = "";
		
		currentMessageList = new ArrayList<String>();
		potentialWords = new TreeSet<String>();
		wordArray = new ArrayList<String>();
		
		index = 0;
	}
	
	/**
	 * Helper method to find whether a string is empty or not.
	 * @return true if String is empty, false otherwise.
	 */
	public boolean isEmpty(String str) {
		boolean isEmpty = (str == null || str.trim().length() == 0);
		return isEmpty;
	}
	
	/**
	 * Helper method that empties all the field variables
	 * for re-assignment.
	 */
	public void cleanVar() {
		message = "";
		word = "";
		signature = "";
		
		potentialWords = new TreeSet<String>();
		wordArray = new ArrayList<String>();
		
		index = 0;
	}
	
	/**
	 * Returns a list of the words typed in so far, including
	 * the last word (or prefix) which has not yet been accepted.
	 * @return List of words typed so far
	 */
	@Override
	public List<String> getMessage() {
		// Splits last message and adds it to list
		currentMessageList = new ArrayList<String>(Arrays.asList(message.split("\\s+")));
		currentMessageList.add(word); // Adds current word
		return currentMessageList;
	}
	
	/**
	 *  Adds a numeric key that has been typed in by the
	 *  user. Extends the current word (or prefix) with possible 
	 *  matches for the new key.
	 */
	@Override
	public void addCharacter(char key) {
		
		signature += key; // Adds character to the sig;
		potentialWords = tree.signatureToWords(signature);
		addWords(); // Adds new word
		
		word = wordArray.get(index); 

		setChanged();
		notifyObservers(); // notify view
	}
	
	
	/**
	 * Adds potential words to an array list.
	 */
	public void addWords() {
		
		 wordArray = new ArrayList<String>(); // Resets list
		 
	     Iterator<String> it = potentialWords.iterator();
	     
	     while(it.hasNext()){
	    	 wordArray.add(it.next());
	     }
	}
	
	/**
	 * Removes the last character typed in. This should
	 * remove the last character from the current match, 
	 * but it would in general enlarge the
	 * possible matches for the current word.
	 */
	@Override
	public void removeLastCharacter() {
		// if word isn't empty, delete character from word
		if (!isEmpty(word)) { // message isn't empty
			word = word.substring(0, word.length() - 1);
			signature = signature.substring(0, signature.length() - 1);
		}
		// if word is empty and message isn't, remove last character
		// from message
		else if (isEmpty(word) && !isEmpty(message)) {
			message = message.substring(0, message.length() - 1);
		}
		// if message is empty
		else {
			cleanVar(); // Empties all variables
		}

		setChanged();
		notifyObservers(); // Notify view
	}

	
	/**
	 * Cycles through the possible matches for the current word,
	 *  i.e., changes the current word to the next match if 
	 *  there is one, or goes back to the first
	 *  match otherwise.
	 */
	@Override
	public void nextMatch() {
		// Takes away the current word from the currentMessage
		
		// If index is going to exceed size of array, circle to the beginning
		if (index <= wordArray.size() - 1) {
			word = wordArray.get(index++); // Adds the next word
		} else {
			index = 0;
			word = wordArray.get(index); // Adds the next word	
		}
		
		setChanged();
		notifyObservers(); // Notify view
	}
	
	/**
	 * Accepts the current matched word 
	 * and adds it to the composed message.
	 */
	@Override
	public void acceptWord() {
		
		message += " " + word; // Adds a space and word
		
		// Resets Variables:
		word = "";
		signature = "";
		potentialWords = new TreeSet<String>();
		wordArray = new ArrayList<String>();
		index = 0;

		addWords();
		setChanged();
		notifyObservers(); // Notify view
	}
}