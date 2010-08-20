package com.github.fberger.synergy.events;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventListenerList<E> implements ListenerSupport<E>, EventListener<E> {

	private final List<EventListener<E>> listeners = new CopyOnWriteArrayList<EventListener<E>>();
	
	@Override
	public void addListener(EventListener<E> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(EventListener<E> listener) {
		listeners.remove(listener);
	}

	@Override
	public void handleEvent(E e) {
		for (EventListener<E> listener : listeners) {
			listener.handleEvent(e);
		}
	}

}
