package com.github.fberger.synergy;

import java.text.MessageFormat;

public class Log {

	public static void d(String message) {
		System.out.println(message);
	}
	
	public static void df(String message, Object...args) {
		System.out.println(MessageFormat.format(message, args));
	}
}
