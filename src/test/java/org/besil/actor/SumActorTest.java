package org.besil.actor;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.besil.actor.impl.LocalActor;
import org.besil.actor.messages.IntMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SumActorTest {
	private SumActor mockActor;
	
	@Before
	public void setUp() {
		mockActor = new SumActor();
		mockActor.start();
	}
	
	@After
	public void tearDown() {
		mockActor.shutdown();
	}
	
	@Test
	public void test() throws Exception {
		int result = IntStream.range(0, 10).sum();
		IntStream.range(0, 10).parallel().forEach(i -> {
			mockActor.send(new IntMessage(i));
		});
		
		Thread.sleep(1000);
		assertEquals(result, mockActor.getSum());
		
	}
	
	class SumActor extends LocalActor {
		private int sum = 0;

		public void receive(IntMessage im) {
			log.info("Retrieving "+im.n);
			this.sum += im.n;
		}
		
		public int getSum() {
			return sum;
		}
	}
}
