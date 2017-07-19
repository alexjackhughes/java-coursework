package ChatServer;

import java.util.Date;

/**
 * Message is the class used to contain a single message
 * in the chat. In the Server, a list of messages is kept
 * so that the chat client can keep a record of all the messages
 * sent.
 * 
 * @author Alexander Jack Hughes
 *
 */
public class Message {
	private String offset; // The number of the message
	private String username; // Username
	private Date time; // time is set within the constructor
	private String message; // Get the message content
	
	public Message(String offset, String username, String message){
		this.offset=offset;
		this.username=username;
		this.time = new Date(); // Sets the time
		this.message=message;
	}
	
	public String getOffset() { // Get the offset of message:
		return offset;
	}
	
	public String getUserName() { // Get the username of message:
		return username;
	}
	public String getTime() { // Get the time of message:
		return time.toString(); // date type is converted to string
	}
	public String getMessage() { // Get the message content:
		return message;
	}
}
