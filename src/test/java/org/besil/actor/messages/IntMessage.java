package org.besil.actor.messages;

import org.besil.messages.ActorMessage;

public class IntMessage implements ActorMessage {
	public int n;
	
	public IntMessage() {
		
	}
	
	public IntMessage(int n) {
		this.n = n;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
}
