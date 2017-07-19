package ChatServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Protocol.SimpleProtocol;

/**
 * Client Thread is called in the server, allowing
 * mutiple chat clients to work concurrently by assigning
 * each chat to it's own thread.
 * 
 * @author Alexander Jack Hughes
 */
public class ClientThread extends Thread {
	private server server;
	private SimpleProtocol sp;
	private Socket socket;
	
	private BufferedReader input; // Input from server
	private DataOutputStream output; // output to server
	private boolean isStopped; // value dependent on if thread is running
	private String username; // Saves username for this chat client
	public static List<Message> messageList = new ArrayList<Message>(); // Message consists of; offset, sender, time of message and the message body:
	
	/*
	 * Constructor of the thread create a protocol for use
	 * within the thread, and sets the server and client socket to
	 * the ones provided by the server:
	 */
	public ClientThread(server server, Socket clientSocket){
		this.sp = new SimpleProtocol(); // creates the protocol
		this.server = server;
		this.socket = clientSocket;
		
		// Try and set the input and output
		try {
			input = new BufferedReader( new InputStreamReader(socket.getInputStream())); // set input
			output = new DataOutputStream(socket.getOutputStream()); // set output
			
			// Send welcome to server:
            output.writeBytes(sp.createMessage("welcome","Welcome to my server!") + "\n");
		// If an exception, return the error:
		} catch (IOException e) {
			System.out.println("Sorry, there was a problem with the client/server connection!");
            e.printStackTrace();
        }
	}
	
	/**
	 * Run method of the thread is called in
	 * the server, when a new client is created:
	 */
	public void run() {
		
		try {
			String[] input = getInput(); // Get decoded input message
			
			// So long as input message isn't empty:
			if(input.length != 0) {
				// If register message, try and register user:
				if(input[0].equals("sign-up")) {
					register(input[1],input[2]);
				// If sign-in message, try and sign user in:
				}else if(input[0].equals("sign-in")) {
					username = input[1]; // sets username
					signIn(input[1],input[2]);
				}
			}
			// While the thread hasn't been stopped:
			while(!isStopped) {
				String[] newInput = getInput(); // Decode any message recieved
				
				// So long as recieved message isn't empty:
				if(newInput.length != 0) {
					// if user is trying to get message
					if(newInput.equals("get-message") && messageList.size() > 0) {
						getMessage(Integer.parseInt(newInput[1])); // get message String from decoded message
					// Otherwise, if user is trying to send a message:
					} else if(newInput.equals("send-message")) {
						sendMessage(newInput[1]); // Try and send message
					}
				}
			}
	    // Catch any I/O errors:
		} catch(IOException e) {
			System.out.println("Sorry, there was a problem signing-in/registering");
			e.printStackTrace();
		}
	}
	
	/**
	 * getInput returns a list of all the messages that
	 * have been added - called preiodically by program:
	 * 
	 * @return a String array of all messages:
	 * @throws IOException
	 */
	public String[] getInput() throws IOException {
		
		// Takes a line of the input from server:
		String readLine = input.readLine();
		 
		// So long as the current line isn't null (i.e. nothing left to decode)
		if(readLine != null) {
			return sp.decodeMessage(readLine); // decodes message using protocol
		}
		// Otherwise, return null:
		return null;
	}
	
	/**
	 * register is the method user to create a new account on
	 * the chat room.
	 * 
	 * @param username will be used as the name of user on chat room
	 * @param password will be used to check the user is the one who registered
	 * this username
	 * @throws IOException
	 */
	public void register(String user, String pass) throws IOException {
		
		// if user's password is unique and 
		// the password and username are the correct length,
		// will return true:
		if(server.newUser(user, pass)) {
			output.writeBytes(sp.createMessage("sign-up", "true", "Thanks for registering! Now, please login.") + "\n");
		// Otherwise, new user will not be registered:
		} else {
            output.writeBytes(sp.createMessage("sign-up", "false", "Sorry! Please try a different username.") + "\n");
		}
		stopThread(); // Calls the stop thread method
	}
	
	/**
	 * Sign-In takes a username and password and queries the server
	 * to find out whether the password matches the password for that username.
	 * 
	 * @param username of the user
	 * @param password of the given username
	 * @throws IOException
	 */
	public void signIn(String user, String pass) throws IOException {
		
		// If the server can authenticate the user, send true message to server:
		if(server.authenticate(user, pass)) {
			this.username = user;
			output.writeBytes(sp.createMessage("sign-in", "true", "Welcome back " + user + "!") + "\n");
		// Otherwise, send false message and explain why:
		} else {
			output.writeBytes(sp.createMessage("sign-in", "false", "Sorry! Your username/password is incorrect.") + "\n");
			stopThread(); // Calls the stop thread method only if user can't sign-in
		}
	}
	
	/**
	 * getMessage returns the messages by getting the message
	 * list and splitting it into each element:
	 * 
	 * @param offset
	 * @throws IOException
	 */
	private void getMessage(int offset) throws IOException {
		
		List<Message> messages = server.getMessage(offset); 
		
			List<String> list = new ArrayList<String>();
			list.add("get-message"); // add begining of message
			
			// Loop
			for(int i = offset+1; i < messages.size(); i++) {
				list.add(String.valueOf(i));
				list.add(messageList.get(i).getUserName());
				list.add(messageList.get(i).getTime());
				list.add(messageList.get(i).getMessage());
			}
			
			String[] out = list.toArray(new String [list.size()]);
			
			// Send message to server of a message:
			output.writeBytes(sp.createMessage(out)+"\n");
		
	}
	
	/**
	 * sendMessages sends the message to the server
	 * 
	 * @param message is the message to be sent
	 * and added to the message list
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		
		// Try and append the message to server message list:
		int offset = server.append(this.username, message);
		String offsets = Integer.toString(offset);
		
		if(offset == -999) { // Message can't be appended to server list for some reason:
			output.writeBytes(sp.createMessage("send-message", "false", "Sorry, this message cannot be added!")+"\n");
		// Otherwise, send the message to server:
		} else {
			messageList.add(new Message(offsets, username, message));
			output.writeBytes(sp.createMessage("send-message", "true", "" + offset)+"\n");
		}
	}
	
	/**
	 * stopThread is used to changed the value of
	 * isStopped, and is used to stop the thread and close
	 * the client socket:
	 */
	public void stopThread() {
		
		this.isStopped = true; // stops thread
		
		try {
			socket.close(); // try to close thread
		}catch(IOException e){ // Otherwise, send error:
			System.out.println("Problem closing client socket!");
			e.printStackTrace();
		}
		
		this.interrupt(); // Finally, interrupt thread
	}
}
