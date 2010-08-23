package com.github.fberger.synergy;

import java.net.InetSocketAddress;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.github.fberger.synergy.ServerEvent.Type;
import com.github.fberger.synergy.events.BlockingEventListener;
import com.github.fberger.synergy.events.EventListener;
import com.github.fberger.synergy.events.EventListenerList;
import com.github.fberger.synergy.events.ListenerSupport;

public class SynergyServer implements ListenerSupport<ServerEvent> {
	
	private final EventListenerList<ServerEvent> listeners = new EventListenerList<ServerEvent>();
	
	private final Set<SynergyClient> clients = new CopyOnWriteArraySet<SynergyClient>();

	private final ServerBootstrap bootstrap;
	
	public SynergyServer() {
		NioServerSocketChannelFactory socketChannelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		bootstrap = new ServerBootstrap(socketChannelFactory);
		
		bootstrap.setPipelineFactory(new SynergyChannelPipelineFactory(this));
		
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
	}
	
	public void start() {
		bootstrap.bind(new InetSocketAddress(24800));
	}
	
	public void stop() {
		bootstrap.releaseExternalResources();
	}
	
	public static void main(String[] args) {
		new SynergyServer().start();
	}

	@Override
	public void addListener(EventListener<ServerEvent> listener) {
		listeners.addListener(listener);
	}

	@Override
	public void removeListener(EventListener<ServerEvent> listener) {
		listeners.removeListener(listener);
	}
	
	void addClient(SynergyClient client) {
		clients.add(client);
		listeners.handleEvent(new ServerEvent(Type.CLIENT_CONNECTED, client));
	}

	public SynergyClient getClient() {
		BlockingEventListener<ServerEvent> listener = new BlockingEventListener<ServerEvent>();
		addListener(listener);
		try {
			ServerEvent serverEvent = listener.get();
			return serverEvent.getClient();
		} catch (InterruptedException e) {
		} finally {
			removeListener(listener);
		}
		return null;
	}

}
