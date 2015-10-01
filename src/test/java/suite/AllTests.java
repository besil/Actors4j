package suite;

import org.besil.actor.AnonymousSenderTest;
import org.besil.actor.PingPongTest;
import org.besil.actor.SumActorTest;
import org.besil.actor.impl.LocalActorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({LocalActorTest.class, SumActorTest.class, PingPongTest.class, AnonymousSenderTest.class})
public class AllTests {

}
