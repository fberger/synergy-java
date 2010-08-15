package com.github.fberger.synergy;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class SynergyMessageEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		Message message = (Message)msg;
		ChannelBuffer buffer = message.getType().write(message.getArguments());
		int length = buffer.readableBytes();
		ChannelBuffer encoded = ChannelBuffers.buffer(4 + length);
		encoded.writeInt(length);
		encoded.writeBytes(buffer);
		return encoded;
	}

}
