package com.github.fberger.synergy;





import java.util.concurrent.atomic.AtomicInteger;

import org.jboss.netty.channel.ChannelHandlerContext;

public class SynergyClient {
	
	private final String name;
	private final ChannelHandlerContext context;
	
	private final AtomicInteger sequenceNumber = new AtomicInteger(1);

	public SynergyClient(ChannelHandlerContext ctx, String name) {
		this.context = ctx;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void enterScreen(int x, int y) {
		// modifier mask of zero for now
		context.getChannel().write(new Message(MessageType.CEnter, x, y, sequenceNumber.getAndIncrement(), 0));
	}
	
	public void setClipboardData(String data) {
		context.getChannel().write(new Message(MessageType.DClipboard, 1, sequenceNumber.get(), data));
	}
	
	public void moveMouse(int x, int y) {
		context.getChannel().write(new Message(MessageType.DMouseMove, x, y));
	}
}
