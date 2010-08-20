package com.github.fberger.synergy;

import org.jboss.netty.channel.ChannelHandlerContext;

public class SynergyClient {
	
	private final String name;
	private final ChannelHandlerContext context;

	public SynergyClient(ChannelHandlerContext ctx, String name) {
		this.context = ctx;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
