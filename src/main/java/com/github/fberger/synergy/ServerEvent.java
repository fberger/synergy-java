package com.github.fberger.synergy;

public class ServerEvent {
	private final Type type;
	private final SynergyClient client;

	public enum Type {
		CLIENT_CONNECTED
	}
	
	public ServerEvent(Type type, SynergyClient client) {
		this.type = type;
		this.client = client;
	}
	
	public Type getType() {
		return type;
	}
	
	public SynergyClient getClient() {
		return client;
	}
}
