package com.triman.wifitest.test;

import java.util.Scanner;

import com.triman.wifitest.utils.netty.Message;
import com.triman.wifitest.utils.netty.MessageServer;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MessageServer server = new MessageServer();
		server.start(9090, "Server");
		
		Scanner in = new Scanner(System.in);
		
		while(true){
			String msg = in.next();
			if(msg != null && msg.equals("0")){
				break;
			} else if (msg !=null && !msg.equals("")){
				server.sendBroadcastMessage(new Message(msg, Message.NORMAL_MESSAGE, 0));
			}
		}
		in.close();
		server.shutdown();
	}

}
