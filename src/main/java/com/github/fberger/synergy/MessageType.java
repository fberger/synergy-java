/**
 * 
 */
package com.github.fberger.synergy;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public enum MessageType {
	Hello("Synergy", "%2i%2i"),
	HelloBack("Synergy", "%2i%2i%s"),
	CNoop("CNOP", null),
	CClose("CBYE", null),
	CEnter("CINN", "%2i%2i%4i%2i"),
	CLeave("COUT", null),
	CClipboard("CCLP", "%1i%4i"),
	CScreenSaver("CSEC", "%1i"),
	CResetOptions("CROP", null),
	CInfoAck("CIAK", null),
	CKeepAlive("CALV", null),
	DKeyDown("DKDN", "%2i%2i%2i"),
	DKeyDown1_0("DKDN", "%2i%2i"),
	DKeyRepeat("DKRP", "%2i%2i%2i%2i"),
	DKeyRepeat1_0("DKRP", "%2i%2i%2i"),
	DKeyUp("DKUP", "%2i%2i%2i"),
	DKeyUp1_0("DKUP", "%2i%2i"),
	DMouseDown("DMDN", "%1i"),
	DMouseUp("DMUP", "%1i"),
	DMouseMove("DMMV", "%2i%2i"),
	DMouseRelMove("DMRM", "%2i%2i"),
	DMouseWheel("DMWM", "%2i%2i"),
	DMouseWheel1_0("DMWM", "%2i"),
	DClipboard("DCLP", "%1i%4i%s"),
	DInfo("DINF", "%2i%2i%2i%2i%2i%2i%2i"),
	DSetOptions("DSOP", "%4I"),
	QInfo("QINF", null),
	EIncompatible("EICV", "%2i%2i"),
	EBusy("EBSY", null),
	EUnknown("EUNK", null),
	EBad("EBAD", null);
	
	public static MessageType getByCommand(String command) {
		return messagesByCommand.get(command);
	}
	
	private final static Map<String, MessageType> messagesByCommand = new HashMap<String, MessageType>();
	
	static {
		for (MessageType message: MessageType.values()) {
			MessageType old = messagesByCommand.put(message.command, message);
			assert old == null;
		}
	}
	
	private final String command;
	private final String arguments;

	MessageType(String command, String arguments) {
		this.command = command;
		this.arguments = arguments;
	}
	
	public ChannelBuffer write(Object...args) {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeBytes(toAsciiBytes(command));
		if (arguments == null) {
			return buffer;
		}
		int arg = 0;
		for (int i = 0; i < arguments.length(); i++) {
			char c = arguments.charAt(i);
			assert c == '%';
			String type = arguments.substring(++i, i+1);
			if (type.equals("%")) {
				buffer.writeByte('%');
			} else if (type.equals("s")) {
				String ascii = (String)args[arg++];
				writeByteString(buffer, toAsciiBytes(ascii));
			} else {
				type += arguments.substring(++i, i+1);
				if (type.equals("1i")) {
					int b = (Integer)args[arg++];
					buffer.writeByte(b);
				} else if (type.equals("2i")) {
					int s = (Integer)args[arg++];
					buffer.writeShort(s);
				} else if (type.equals("4i")) {
					int d = (Integer)args[arg++];
					buffer.writeInt(d);
				} else if (type.equals("4I")) {
					List<Integer> list = (List<Integer>)args[arg++];
					buffer.writeInt(list.size());
					for (Integer d : list) {
						buffer.writeInt(d);
					}
				}
			}
		}
		return buffer;
	}
	
	public Object[] parse(ChannelBuffer buffer) {
		if (arguments == null) {
			return new Object[0];
		}
		List<Object> values = new ArrayList<Object>();
		for (int i = 0; i < arguments.length(); i++) {
			char c = arguments.charAt(i);
			assert c == '%';
			String type = arguments.substring(++i, i+1);
			if (type.equals("%")) {
				// buffer.writeByte('%'); TODO what to do?
			} else if (type.equals("s")) {
				values.add(readByteString(buffer));
			} else {
				type += arguments.substring(++i, i+1);
				if (type.equals("1i")) {
					values.add(0xFF & buffer.readByte());
				} else if (type.equals("2i")) {
					values.add(0xFFFF & buffer.readShort());
				} else if (type.equals("4i")) {
					values.add(buffer.readInt());
				} else if (type.equals("4I")) {
					int size = buffer.readInt();
					List<Integer> list = new ArrayList<Integer>(size);
					for (i = 0; i < size; i++) {
						list.add(buffer.readInt());
					}
					values.add(list);
				}
			}
		}
		return values.toArray();
	}
	
	public static final byte[] toAsciiBytes(String string) {
		try {
			return string.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static ChannelBuffer writeByteString(ChannelBuffer buffer, byte[] bytes) {
		buffer.writeInt(bytes.length);
		buffer.writeBytes(bytes);
		return buffer;
	}
	
	private static String readByteString(ChannelBuffer buffer) {
		int length = buffer.readInt();
		byte[] bytes = new byte[length];
		buffer.readBytes(bytes, 0, length);
		try {
			return new String(bytes, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}