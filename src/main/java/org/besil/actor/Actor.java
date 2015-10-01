package org.besil.actor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.besil.actor.communication.CommunicationStrategy;
import org.besil.messages.ActorMessage;
import org.besil.messages.DeathMessage;

/**
 * Actor main class
 * 
 * @author besil
 *
 */
public abstract class Actor implements Runnable {
	protected final static Logger log = Logger.getLogger(Actor.class.getName());
	private CommunicationStrategy communicationStrategy;
	private boolean isFinished = false;
	private Thread thread;

	public Actor(CommunicationStrategy cl) {
		this.communicationStrategy = cl;
	}

	/**
	 * Start the current actor
	 */
	public final void start() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Actor main loop. 
	 * The only way to stop an actor is via @shutdown method, or enqueue a DeathMessage
	 */
	@Override
	public final void run() {
		while (!this.isFinished()) {
			ActorMessage mex;
			mex = communicationStrategy.getMessage();
			this.receive(mex);
		}
	}

	/**
	 * Send a message to this actor
	 * @param mex
	 */
	public final void send(ActorMessage mex) {
		communicationStrategy.send(mex);
	}

	/**
	 * Stop the actor
	 */
	public final void shutdown() {
		this.send(new DeathMessage());
	}

	/**
	 * Generic method for handling @ActorMessage
	 * @param o
	 */
	public final void receive(ActorMessage o) {
		try {
			Method m = getClass().getMethod("receive", new Class[] { o.getClass() });
			m.setAccessible(true);
			m.invoke(this, new Object[] { o });
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			log.warning("No handler found for message " + o.getClass());
			e.printStackTrace();
		}
	}

	public final void receive(DeathMessage dm) {
		this.isFinished = true;
	}

	protected final boolean isFinished() {
		return isFinished;
	}
	
	protected Thread getThread() {
		return thread;
	}

}
