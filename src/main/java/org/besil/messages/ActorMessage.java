package org.besil.messages;

import org.besil.actor.Actor;
import org.besil.actor.exceptions.AnonymousSenderException;

/**
 * Interface for handling actor communication
 * @author besil
 *
 */
public interface ActorMessage {

	/**
	 * Returns the sender, if specified
	 * @return
	 */
	default public Actor getSender() {
		throw new AnonymousSenderException("Sender is anonymous! Your message will be discarded");
	}
	
}
