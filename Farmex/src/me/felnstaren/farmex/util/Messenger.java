package me.felnstaren.farmex.util;

public class Messenger {

	public static String color(String message) {
		return message.replace('&', '§');
	}
	
	public static String uncolor(String message) {
		message = color(message);
		char[] msg = message.toCharArray();
		String newmsg = "";
		
		for(int i = 0; i < msg.length; i++) {
			if(i > 0 && msg[i] != '§' && msg[i - 1] != '§') newmsg += msg[i];
			if(i == 0 && msg[i] != '§') newmsg += msg[i];
		}
		
		return newmsg;
	}
	
}
