package com.triman.wifitest.test;

import java.util.Scanner;

import com.triman.wifitest.utils.netty.Message;
import com.triman.wifitest.utils.netty.MessageClient;

public class TestClientOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MessageClient client = new MessageClient();
		client.start("127.0.0.1", 9090, "Client1");
		
		Scanner in = new Scanner(System.in);
		
		while(true){
			String msg = in.next();
			if(msg != null && msg.equals("0")){
				break;
			} else if (msg !=null && !msg.equals("")){
				client.sendMessage(new Message(msg, Message.NORMAL_MESSAGE, 1));
			}
		}
		in.close();
		client.shutdown();
	}

}
