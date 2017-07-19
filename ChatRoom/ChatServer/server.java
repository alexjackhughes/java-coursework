package ChatServer;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Protocol.SimpleProtocol;

/**
 * This server will accept input from a client, setting up a socket
 * connection and allowing the client to close the connection once
 * finished.
 * 
 * @author Alexander Jack Hughes
 */
public class server {

	int port;
	public static SimpleProtocol sp;
	public ExecutorService executor;
	public static HashMap<String, String> userMap; // HashMap == (key:username, value:password):
	public static List<Message> messageList; // Message consists of; offset, sender, time of message and the message body:
	
	/**
	 * Constructor for server.
	 * 
	 * @param port is the argument int given when program is run
	 */
	public server(int port) {
		userMap = new HashMap<String, String>(); 
		this.port = port; // given port number
		this.sp = new SimpleProtocol();
		this.executor = Executors.newFixedThreadPool(1000); // Creates the thread pool
		messageList = new ArrayList<>(); // Creates a list of messages
	}
	
	/**
	 * Starts the server, and initiates all the sockets and client thread:
	 */
	public void start() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);  // Initialises the server socket
            
            while(true) {
            	Socket clientSocket = serverSocket.accept(); // tries to initalise the client socket
            	System.out.println("");
            	executor.execute(new ClientThread(this, clientSocket)); // adds the client socket to its own thread
            }
		}
	    // Catch any I/O exceptions:
	    catch(IOException e) {
	        System.out.println("IOException on socket listen: " + e);
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Sign-up method:
	 * 
	 * Checks that the username and password are the correct length,
	 * and that the username isn't already registered before signing up the user:
	 * 
	 * @param user = the username of new user
	 * @param pass = the password given by new user
	 * @return boolean value depending on success of signing user up
	 */
	public boolean newUser(String user, String pass) {
		
		 if (user.length() < 5 || user.length() > 20 ||
		     pass.length() < 8 || pass.length() > 32) {
			 return false;
		 }
		 // If username is already in the map:
		 else if (userMap.containsKey(user)) {
			 return false;
		 }		 
		 else {
			 this.userMap.put(user, pass); // add the user to the map:
			 return true;
		 }
	}
	
	/**
	 * Sign-In Method: 
	 * 
	 * Checks that the password for the given username is the password
	 * saved.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(String user, String pass) {
		// If password (value) == the returned key value, login user in:
		if (userMap.get(user).equals(pass)) {
			return true;
		}
		// Otherwise, do not log the user in:
		else {
			return false;
		}
	}
	
	/**
	 * Returns a list of the messages.
	 * 
	 * @return a list of messages, based on the given offset:
	 */
	public List<Message> getMessage(int offset) {
		return messageList.subList(offset, messageList.size());
	}
	
	/**
	 * Append a new message:
	 */
	public int append(String username, String message) {
		String offset = "" + messageList.size();
		
		Message x = new Message(offset, username, message); // Creates a message
		messageList.add(x);
		
		// Otherwise, return a negative value if message cannot be added: 
		if (messageList.size() == 0) {
			return messageList.size();
		} else {
			return -999;
		}

	}
	
	// Main method initates the server - requires 
	// a port number when the class is run:
	public static void main (String [] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (args.length != 1) { // Catches Exception if no port number is chosen
				      System.err.println("Use like this from terminal: java ChatServer <port number>");
				      System.exit(1);
					}   
					
					int port = Integer.parseInt(args[0]); // Gets the port number
					server x = new server(port);
					x.start();
			}
		});
	}
}
