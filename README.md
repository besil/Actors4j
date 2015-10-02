# actors4j
A lightweight, simple and efficient thread message passing library for Java.

Inspired by Akka, designed for Java.

## Quickstart

Define your messages

```java
class Ping extends ActorMessageWithSender {
	public Ping(PingActor pingActor) {
		super(pingActor);
	}
	
}
	
class Pong extends ActorMessageWithSender {
	public Pong(PongActor pongActor) {
		super(pongActor);
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
```

Define your actors
```java
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
```

Use them!

```java
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
```
