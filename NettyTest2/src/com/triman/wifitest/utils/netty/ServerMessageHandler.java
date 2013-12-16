package com.triman.wifitest.utils.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;

public class ServerMessageHandler extends MessageHandler{

	public ServerMessageHandler(String id) {
		super(id);
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		Connection c = ConnectionManager.getInstance().newConnection(ctx);
		System.out.println("Client " + c.getId() + " has connected!");
		c.getChannel().write(new Message("Connected!",Message.RESPONSE));
		super.channelConnected(ctx, e);
	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.channelDisconnected(ctx, e);
	}
}