package com.github.fberger.synergy;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

public class SynergyServerHandler extends SimpleChannelHandler {

	public static final String TAG = "Server";
	
	private final Timer timer = new HashedWheelTimer();
	
	public final static long KEEP_ALIVE_INTERVAL = 3000;
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Message message = (Message) e.getMessage();
		Log.d(message.getType().toString());
		final Channel channel = e.getChannel();
		switch (message.getType()) {
		case HelloBack:
			Log.df("name: {2}, version{0}{1}", message.getArguments());
			channel.write(new Message(MessageType.QInfo));
			break;	
		case DInfo:
			Log.df("x{0}, y{1}, w{2}, h{3}, warp size{4}, mouse x{5}, mouse y{6}", message.getArguments());
			channel.write(new Message(MessageType.CInfoAck));
			channel.write(new Message(MessageType.CResetOptions));
			//channel.write(new Message(MessageType.DSetOptions, Collections.emptyList()));
			channel.write(new Message(MessageType.CKeepAlive));
			break;
		case CNoop:
			Log.d("noop received");
			break;
		case CKeepAlive:
			Log.d("keep alive received");
			timer.newTimeout(new TimerTask() {
				@Override
				public void run(Timeout timeout) throws Exception {
					channel.write(new Message(MessageType.CKeepAlive));
				}
			}, KEEP_ALIVE_INTERVAL, TimeUnit.MILLISECONDS);
			break;
		}
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		Channel channel = e.getChannel();
		channel.write(new Message(MessageType.Hello, 1, 3));
	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		Log.df("disconnected {0}", e);
		super.channelDisconnected(ctx, e);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		Log.df("exception {0}", e);
		Channel ch = e.getChannel();
		ch.close();
	}
	
}