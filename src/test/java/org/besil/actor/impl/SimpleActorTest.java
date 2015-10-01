package org.besil.actor.impl;

import org.besil.actor.Actor;
import org.besil.actor.messages.StringMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class SimpleActorTest {
	private Actor mockActor;
	protected final String mex = "Hello actor";
	
	@Before
	public void setUp() {
		mockActor = this.getActor();
		mockActor.start();
	}
	
	@After
	public void tearDown() {
		mockActor.shutdown();
	}
	
	protected abstract Actor getActor();

	@Test
	public void testSend() {		
		mockActor.send(new StringMessage(mex));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.checkResult(mockActor);
	}

	protected abstract void checkResult(Actor actor);
}
