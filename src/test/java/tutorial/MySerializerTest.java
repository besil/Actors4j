package tutorial;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.besil.actor.messages.TestMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by besil on 9/22/15.
 */
public class MySerializerTest {
    private MySerializer serializer;

    @Before
    public void setUp() throws Exception {
        serializer = new MySerializer();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void serializeTest() {
        TestMessage mex = new TestMessage("silvio", "surname", 26, Arrays.asList("piano", "running"));
        String json = serializer.serialize(mex);
        assertEquals(json, "{\"name\":\"silvio\",\"surname\":\"surname\",\"age\":26,\"hobbies\":[\"piano\",\"running\"]}");
    }

    @Test
    public void deserializeTest() {
        TestMessage mex = serializer.deserialize("{name: 'Silvio', surname: 'MySurname', age: 26, hobbies: ['piano', 'running']}");
        assertEquals("Silvio", mex.getName());
        assertEquals("MySurname", mex.getSurname());
        assertEquals(26, mex.getAge());
        assertEquals(2, mex.getHobbies().size());
    }

}