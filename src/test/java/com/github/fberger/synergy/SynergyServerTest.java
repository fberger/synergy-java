package com.github.fberger.synergy;

import java.util.concurrent.CountDownLatch;

import junit.framework.TestCase;

import org.junit.Test;

import com.github.fberger.synergy.events.EventListener;

public class SynergyServerTest extends TestCase {

	
	@Test(timeout = 3000)
	public void testClientConnectEvent() throws Exception {
		SynergyServer server = new SynergyServer();
		server.start();
		final CountDownLatch latch = new CountDownLatch(1);
		server.addListener(new EventListener<ServerEvent>() {
			public void handleEvent(ServerEvent event) {
				switch (event.getType()) {
				case CLIENT_CONNECTED:
					SynergyClient client = event.getClient();
					System.out.println(client.getName());
					latch.countDown();
				}
			}
		});
		latch.await();
		server.stop();
	}
	
	@Test
	public void testMoveMouse() throws Exception {
		SynergyServer server = new SynergyServer();
		server.start();
		SynergyClient client = server.getClient();
		Thread.sleep(1000);
		client.enterScreen(0, 0);
		client.moveMouse(400, 400);
		assertNotNull(client);
		server.stop();
	}
}
