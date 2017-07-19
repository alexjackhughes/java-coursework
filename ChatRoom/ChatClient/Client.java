package ChatClient;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import Protocol.SimpleProtocol;

public class Client {
	
	private Socket clientSocket;			// socket connecting to server
	private DataOutputStream outToServer;	// output stream to server
	private BufferedReader inFromServer;	// input stream from server
	private SimpleProtocol protocol = new SimpleProtocol();		// pack and unpack messages
	public Integer offset = -1;		// offset of messages, at the beginning it is -1. Update it to the offset of the latest message
	private String host = "";		// IP address of server
	private Integer port = 0;		// Port number of server
	
	/*
	 * 		Read a line from server and unpack it using SimpleProtocol
	 */
	public String[] getResponse(){
		try {
			return protocol.decodeMessage(inFromServer.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 		Send sign-up request to server, return the response to GuiSignUp
	 */
	public String[] signup(String user, String pass){
		String string = protocol.createMessage("sign-up", user, pass);
		try {
			outToServer.writeBytes(string + "\n");
			String[] response = this.getResponse();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 		Send sign-in request to server, return the response to GuiSignIn
	 */
	public String[] signin(String user, String pass){
		String string = protocol.createMessage("sign-in", user, pass);
		try {
			outToServer.writeBytes(string + "\n");
			String[] response = this.getResponse();
			if(response[1].equals("true")){
				System.out.println("Sign-in successful.");
			}else{
				System.out.println(response[2]);
			}
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 		Send get-message request to server
	 */
	public void get_message(){
		String string = protocol.createMessage("get-message", this.offset.toString());
		try {
			outToServer.writeBytes(string + "\n");
		} catch (IOException e) {
			System.out.println("Unable to get message");
			e.printStackTrace();
		}
	}
	
	/*
	 * 		Send a message to server.
	 */
	public void send_message(String msg){
		String string = protocol.createMessage("send-message", msg);
		try {
			outToServer.writeBytes(string + "\n");
		} catch (IOException e) {
			System.out.println("Unable to send message.");
			e.printStackTrace();
		}
	}
	
	/*
	 * 		Initialize socket and input/output streams
	 */
	public void start(){
		try {
			clientSocket = new Socket(this.host, this.port);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println(this.getResponse()[1]);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 		Close socket
	 */
	public void stop(){
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
}
