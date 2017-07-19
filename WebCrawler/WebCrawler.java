/**
*
* @author Alexander Jack Hughes
*
* This code answers the question for WS4 - EX3.
*
* The class WebCrawler uses 3 methods to effectively read a provided link and find all the URLs on
* the page, and then return the URLs to the user.
* 
* The method readURL takes an argument URL, and downloads the HTML for that web page into
* a string.
* 
* The method split takes a string and splits it into an ArrayList that just contains the URLS
* that were present within the String.
* 
* The method collectURLs combines the above two methods, so that the user can input a URL, and 
* have returned an ArrayList of URLs of type String.
*
**/

// List of imported libraries:
import java.util.*; // Imports Scanner & arrays
import java.io.*; // Imports input/output library
import java.net.*; // Other imports
import java.lang.*; // Imports split
import java.util.regex.Matcher; // Imports regex matcher
import java.util.regex.Pattern; // Imports regex pattern

class WebCrawler { 
	
	// No field variables
	
	// Constructor so that object is always created when called
	public WebCrawler() {
	}

	Scanner scanner = new Scanner(System.in); // Creates a scanner object
  
	// All Methods for class will be listed below:

		// Converts argument URL into a string of the HTML of the web page
	    public static String readUrl(String url) {
	        
	    	// Have to try exception as argument URL may not be a link/work
	        try {
	        	URL website = new URL(url);
	        	URLConnection connection = website.openConnection();
	        	BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                    connection.getInputStream()));

	        	StringBuilder response = new StringBuilder();
	        	String inputLine;

	        	while ((inputLine = in.readLine()) != null) 
	        		response.append(inputLine);

	        	in.close();
	        	
	        	String finalResponse = response.toString();
	        	return finalResponse;
	        }
	        // Throws exception if URL does not work/is not a URL
	        catch (Exception e) {
	            return "no access to URL: " + url;
	        }
	    }
  
  // Splits argument string (i.e. HTML of web page) into an array list of URLs that were present in string
  	public static ArrayList<String> split(String regex) {
  		
  		// Creates an ArrayList to hold just the URLs
  		ArrayList<String> urlArray = new ArrayList<String>();

  		// Splits the array once it finds a href="
  		String[] splitArray = regex.split("a href=\"");
  		
  		// Iterates through each element of array SplitArray
  		// It will then add the URL in between those splits to array urlArray
  		for (int x = 1; x < splitArray.length; x++) { // Starts at 1 to avoid first element (which cannot contain link)
  	  		
  			int y = 0; // Counter to iterate through each element of array
  			String[] endSplit =splitArray[x].split("\">"); // Splits an element if it finds ">
  			
	  		urlArray.add(endSplit[y]); // Adds just the URL to array urlArray
	  		y++;
	  		
 		}
  		// If there were no links in string, will return an empty array
 		return urlArray; // Method returns ArrayList of just URLs
  	}
  
  	// Converts an argument link to a string of its HTML page 
  	// and then finds the URLs within that string, and returns an
  	// array of just those values of type String
  	public static ArrayList<String> collectUrls(String urlString) {
  		
  		// Goes to argument web page, and downloads HTML as a String
  		String x = readUrl(urlString);
  		
  		// Splits the HTML string into an ArrayList of just URLs
  		ArrayList<String> y = split(x);
  		
  		// Returns an ArrayList of just the URLs
  		return y;

  	}

// Main method of class
    public static void main(String[] args) {

      WebCrawler x = new WebCrawler(); // Declares & Initializes new class
      
      // Turns string into object passed to split
      String y = readUrl("http://cs.bham.ac.uk/~mmk/teaching/java/test.html");   
     
      System.out.println("Read the URL:");
      System.out.println(y);
      
      ArrayList<String> splitURL = split(y);
    	  
		System.out.println("Each line represents a split:");
		int j = 0;
		while (splitURL.size() > j) {
			System.out.println(splitURL.get(j));
			j++;
		}
      
    }
}