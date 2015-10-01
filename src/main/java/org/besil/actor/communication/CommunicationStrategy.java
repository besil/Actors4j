package org.besil.actor.communication;

import org.besil.messages.ActorMessage;

/**
 * Communication strategy interface between actors.
 * A communication strategy layer encapsulates the transport logic of messages between actors (send and get message)
 * @author besil
 *
 */
public interface CommunicationStrategy {
	/**
	 * Get a new message from the communication layer.
	 * This is blocking, until a new message arrives in the inbox
	 * @return
	 */
	public ActorMessage getMessage();
	
	/**
	 * Sends a message to this actor.
	 * This operations should enqueue the message in the inbox of this actor
	 * @param mex
	 */
	public void send(ActorMessage mex);
}
