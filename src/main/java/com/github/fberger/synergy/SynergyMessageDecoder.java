package com.github.fberger.synergy;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;

public class SynergyMessageDecoder extends ReplayingDecoder<SynergyMessageDecoder.State> {
	
	enum State {
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer, State state) throws Exception {
		if (buffer.readableBytes() < 4) {
			return null;
		}
		buffer.markReaderIndex();
		int length = buffer.readInt();
		if (buffer.readableBytes() < length) {
			buffer.resetReaderIndex();
			return null;
		}
		return parseMessage(buffer, length);
	}

	private Message parseMessage(ChannelBuffer buffer, int length) {
		for (int commandLength = 4; commandLength <= length; commandLength++) {
			String command = buffer.toString(buffer.readerIndex(), commandLength, Charset.forName("US-ASCII"));
			Log.df("command: {0}", command);
			MessageType type = MessageType.getByCommand(command);
			if (type != null) {
				buffer.readerIndex(buffer.readerIndex() + commandLength);
				return new Message(type, type.parse(buffer));
			}
		}
		Log.d("null message");
		return null;
	}

}
