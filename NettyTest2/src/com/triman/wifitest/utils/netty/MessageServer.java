package com.triman.wifitest.utils.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

public class MessageServer {
	
	private ServerBootstrap bootstrap;
	
	public void start(String host, int port){
		ChannelFactory factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		bootstrap = new ServerBootstrap(factory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(
						new ObjectDecoder(ClassResolvers.cacheDisabled(this
		                .getClass().getClassLoader())),
		                new ObjectEncoder(),
		                new ServerMessageHandler("Server"));
			}
		});
		bootstrap.bind(new InetSocketAddress(host, port));
	}
	
	public void sendBroadcastMessage(Message msg){
		int n = ConnectionManager.getInstance().getConnectionCount();
		while(n > 0){
			ConnectionManager.getInstance().getConnection(n).getChannel().write(msg);
			n--;
		}
	}
	
	public void shutdown(){
		bootstrap.releaseExternalResources();
		bootstrap.shutdown();
	}
}