package com.github.fberger.synergy.events;

public interface EventListener<E> {
	public void handleEvent(E e);
}
