package org.besil.actor;

import static org.junit.Assert.assertTrue;

import org.besil.actor.Actor;
import org.besil.actor.impl.LocalActor;
import org.besil.messages.ActorMessage;
import org.junit.Test;

public class PingPongTest {
	class PingActor extends LocalActor {
		int pingCount = 0;
		public void receive(StartMex start) {
			pingCount++;
			log.info("Ping");
			start.getOther().send(new Ping(this));
		}
		public void receive(Pong pong) throws InterruptedException {
			log.info("Ping");
			Thread.sleep(1000);
			pong.getSender().send(new Ping(this));
		}
	}
	
	class PongActor extends LocalActor {
		int pongCount = 0;
		public void receive(Ping ping) throws InterruptedException {
			pongCount++;
			log.info("Pong");
			Thread.sleep(1000);
			ping.getSender().send(new Pong(this));
		}
	}
	
	class Ping implements ActorMessage {
		private PingActor sender;

		public Ping(PingActor pingActor) {
			this.sender = pingActor;
		}
		
		@Override
		public Actor getSender() {
			return this.sender;
		}
		
	}
	
	class Pong implements ActorMessage {
		private PongActor sender;

		public Pong(PongActor pongActor) {
			this.sender = pongActor;
		}
		@Override
		public Actor getSender() {
			return this.sender;
		}
	}
	
	class StartMex implements ActorMessage {
		private Actor other;
		public StartMex(Actor other) {
			this.other = other;
		}
		public Actor getOther() {
			return other;
		}
	}
	
	@Test
	public void pingPong() {
		PingActor pinger = new PingActor();
		PongActor ponger = new PongActor();
		
		pinger.start();
		ponger.start();
		
		pinger.send(new StartMex(ponger));
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(pinger.pingCount > 0);
		assertTrue(ponger.pongCount > 0);
		
		pinger.shutdown();
		ponger.shutdown();
	}
}
