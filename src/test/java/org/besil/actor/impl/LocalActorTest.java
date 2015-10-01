package org.besil.actor.impl;

import static org.junit.Assert.assertEquals;

import org.besil.actor.Actor;
import org.besil.actor.impl.LocalActor;
import org.besil.actor.messages.StringMessage;

public class LocalActorTest extends SimpleActorTest {
	protected class TestActor extends LocalActor {
		public String message = "";

		public void receive(StringMessage sm) {
			log.info("Message is: "+sm.getMessage());
			this.message = sm.getMessage();
		}		
	}
	
	@Override
	protected Actor getActor() {
		return new TestActor();
	}

	@Override
	protected void checkResult(Actor actor) {
		TestActor ta = (TestActor) actor;
		assertEquals(mex, ta.message);
	}
	
}
