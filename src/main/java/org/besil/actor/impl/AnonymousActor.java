package org.besil.actor.impl;

import org.besil.actor.Actor;
import org.besil.actor.communication.CommunicationStrategy;
import org.besil.actor.exceptions.AnonymousSenderException;
import org.besil.messages.ActorMessage;

@Deprecated
public class AnonymousActor extends Actor {
	public AnonymousActor() {
		super(new AnonymousCommunicationStrategy());
	}
	
}

class AnonymousCommunicationStrategy implements CommunicationStrategy {

	@Override
	public ActorMessage getMessage() {
		throw new AnonymousSenderException("Sender is anonymous! Your message will be discarded");
	}

	@Override
	public void send(ActorMessage mex) {
		throw new AnonymousSenderException("Sender is anonymous! Your message will be discarded");
	}
	
}