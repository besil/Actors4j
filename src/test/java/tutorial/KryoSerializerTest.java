package tutorial;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.besil.actor.messages.IntMessage;
import org.besil.messages.ActorMessage;
import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoSerializerTest {
	@Test
	public void testKryo() throws Exception {
		Kryo kryo = new Kryo();
		// ...
		Output output = new Output(new FileOutputStream("file.bin"));
		IntMessage someObject = new IntMessage(5);
//		kryo.writeObject(output, someObject);
		kryo.writeClassAndObject(output, someObject);
		output.close();
		
		// ...
		Input input = new Input(new FileInputStream("file.bin"));
//		ActorMessage inputObject = kryo.readObject(input, ActorMessage.class);
		ActorMessage inputObject = (ActorMessage) kryo.readClassAndObject(input);
		input.close();

		System.out.println(inputObject.getClass());
	}
}
