# actors4j
A lightweight, simple and efficient thread message passing library for Java.

Inspired by Akka, designed for Java.

### Installation

Download and install locally the project
```bash
git clone https://github.com/besil/actors4j.git
cd actors4j
mvn clean install -DskipTests
```

Now you can include it in your pom.xml
```xml
<dependency>
    <groupId>org.besil</groupId>
    <artifactId>actors4j</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Quickstart

Define your messages

```java
class Ping extends ActorMessageWithSender {
	public Ping(Actor sender) {
		super(sender);
	}
}
```

```java
class Pong extends ActorMessageWithSender {
	public Pong(Actor sender) {
		super(sender);
	}
}
```

```java	
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
	public void receive(Pong pong) {
		pingCount++;
		log.info("Ping");
		pong.getSender().send(new Ping(this));
	}
}
```

```java
class PongActor extends LocalActor {
	int pongCount = 0;
	public void receive(Ping ping) {
		pongCount++;
		log.info("Pong");
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
