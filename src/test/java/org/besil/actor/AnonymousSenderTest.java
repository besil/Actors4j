package org.besil.actor;

import static org.junit.Assert.assertFalse;

import org.besil.actor.impl.LocalActor;
import org.besil.actor.messages.IntMessage;
import org.junit.Test;

public class AnonymousSenderTest {
	private boolean ok = false;
	
	class SimpleActor extends LocalActor {
		public void receive(IntMessage mex) {
			mex.getSender();
			// this should never be reached: getSender() throws an AnonymousSenderException
			ok = true;
		}
	}
	
	@Test
	public void test() {		
		SimpleActor sa = new SimpleActor();
		sa.start();

		sa.send(new IntMessage(0));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertFalse(ok);
		sa.shutdown();
	}
}
