package com.github.fberger.synergy;

public class Message {
	
	private final MessageType type;
	private final Object[] arguments;

	public Message(MessageType type, Object...arguments) {
		this.type = type;
		this.arguments = arguments;
	}
	
	public MessageType getType() {
		return type;
	}
	
	public Object[] getArguments() {
		return arguments;
	}
}
