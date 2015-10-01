package org.besil.actor.communication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.besil.messages.ActorMessage;

/**
 * In memory communication strategy implementation
 * Get and send operation add a message in a blocking queue
 * @author besil
 *
 */
public class InMemoryCommunicationStrategy implements CommunicationStrategy {
	private BlockingQueue<ActorMessage> queue;

	public InMemoryCommunicationStrategy() {
		this.queue = new LinkedBlockingQueue<>();
	}

	@Override
	public ActorMessage getMessage() {
		try {
			return this.queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Actor interrupted!");
	}

	public void send(ActorMessage mex) {
		this.queue.add(mex);
	}
}
