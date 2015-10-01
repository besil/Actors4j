package org.besil.actor.impl;

import org.besil.actor.Actor;
import org.besil.actor.communication.InMemoryCommunicationStrategy;

/**
 * A shortcut for Actor with an InMemoryCommunication strategy
 * @author besil
 *
 */
public abstract class LocalActor extends Actor {

	public LocalActor() {
		super(new InMemoryCommunicationStrategy());
	}

}
