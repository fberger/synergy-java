package com.github.fberger.synergy.events;

public interface ListenerSupport<E> {

	public void addListener(EventListener<E> listener);
	
	public void removeListener(EventListener<E> listener);
}
