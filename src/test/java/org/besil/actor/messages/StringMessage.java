package org.besil.actor.messages;

import org.besil.messages.ActorMessage;

public class StringMessage implements ActorMessage {
	private String mex;
	public StringMessage(String mex) {
		this.mex = mex;
	}
	
	public String getMessage() {
		return mex;
	}

}
