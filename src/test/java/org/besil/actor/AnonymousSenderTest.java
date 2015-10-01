package org.besil.actor;

public class AnonymousSenderTest {
//	private boolean ok = false;
//	
//	class SimpleActor extends LocalActor {
//		public void receive(IntMessage mex) {
//			mex.getSender();
//			// this should never be reached: getSender() throws an AnonymousSenderException
//			ok = true;
//		}
//	}
//	
//	@Test
//	public void test() {		
//		SimpleActor sa = new SimpleActor();
//		sa.start();
//
//		sa.send(new IntMessage(0));
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		assertFalse(ok);
//		sa.shutdown();
//	}
}
