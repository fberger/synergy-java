package com.github.fberger.synergy;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class SynergyServer {

	public static void main(String[] args) {
		NioServerSocketChannelFactory socketChannelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
				                          Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap(socketChannelFactory);
		
		bootstrap.setPipelineFactory(new SynergyChannelPipelineFactory());
		
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		
		bootstrap.bind(new InetSocketAddress(24800));
	}
}
