package org.besil.messages;

import org.besil.actor.Actor;

public abstract class ActorMessageWithSender implements ActorMessage {
	private Actor sender;
	
	public ActorMessageWithSender(Actor sender) {
		this.sender = sender;
	}
	
	/**
	 * Returns the sender of this message
	 * @return
	 */
	public final Actor getSender() {
		return sender;
	}
}
