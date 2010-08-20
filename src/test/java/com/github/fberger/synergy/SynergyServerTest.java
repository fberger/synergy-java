package com.github.fberger.synergy;

import junit.framework.TestCase;

import org.junit.Test;

import com.github.fberger.synergy.events.EventListener;

public class SynergyServerTest extends TestCase {

	
	@Test
	public void testClientConnectEvent() {
		SynergyServer server = new SynergyServer();
		server.start();
		server.addListener(new EventListener<ServerEvent>() {
			public void handleEvent(ServerEvent event) {
				switch (event.getType()) {
				case CLIENT_CONNECTED:
					SynergyClient client = event.getClient();
					System.out.println(client.getName());
				}
			}
		});
	}
	
	@Test
	public void testSendKeyToClient() {
		SynergyServer server = new SynergyServer();
		server.start();
		SynergyClient client = server.getClient();
		// 
	}
}
