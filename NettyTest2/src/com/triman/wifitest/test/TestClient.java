package com.triman.wifitest.test;

import java.util.Scanner;

import com.triman.wifitest.utils.netty.Message;
import com.triman.wifitest.utils.netty.MessageClient;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MessageClient client = new MessageClient();
		client.start("127.0.0.1", 9090);
		
		Scanner in = new Scanner(System.in);
		
		while(true){
			String msg = in.next();
			if(msg != null && msg.equals("0")){
				break;
			} else if (msg !=null && !msg.equals("")){
				client.sendMessage(new Message(msg, Message.REQUEST));
			}
		}
		in.close();
		client.shutdown();
	}

}
