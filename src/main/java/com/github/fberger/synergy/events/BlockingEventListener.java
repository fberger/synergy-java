package com.github.fberger.synergy.events;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class BlockingEventListener<E> implements EventListener<E> {

	private final CountDownLatch latch = new CountDownLatch(1);
	private final AtomicReference<E> event = new AtomicReference<E>();
	
	@Override
	public void handleEvent(E e) {
		event.set(e);
		latch.countDown();
	}
	
	public E get() throws InterruptedException {
		latch.await();
		return event.get();
	}

}
