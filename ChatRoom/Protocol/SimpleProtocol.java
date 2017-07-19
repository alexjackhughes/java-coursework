package Protocol;

public class SimpleProtocol implements Protocol {
	
	private static String escape(String str){
		str = str.replace("\\", "\\\\");
		str = str.replace("_", "\\_");
		str = str.replace("\n", "\\n");
		return str;
	}
	
	private static String unescape(String str){
		str = str.replace("\\\\", "\\");
		str = str.replace("\\_", "_");
		str = str.replace("\\n", "\n");
		return str;
	}
	
	public String createMessage(String... args){
		String result = "";
		for(String str: args){
			if(result!="") result = result + ":__:";
			result = result + escape(str);
		}
		System.out.println("send: " + result);
		return result;
	}
	
	public String[] decodeMessage(String str) {
		System.out.println("receive: " + str);
		if(str == null || str.equals("")){
			return new String[0];
		}
		String[] messages = str.split(":__:");
		for(int i=0; i<messages.length; i++){
			messages[i] = unescape(messages[i]);
		}
		if(messages.length == 1 && messages[0].equals("")){
			return new String[0];
		}
		return messages;
	}

	public static void main(String[] args) {
		Protocol protocol = new SimpleProtocol();
		String[] strings = protocol.decodeMessage(protocol.createMessage("_aaaa_", "bbbb", "cccc", "dddd"));
		for(String str:strings){
			System.out.println(str);
		}
	}

}
