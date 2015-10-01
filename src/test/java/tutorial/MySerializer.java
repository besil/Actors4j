package tutorial;

import org.besil.actor.messages.TestMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by besil on 9/22/15.
 */
public class MySerializer {
    private Gson gson;

    public MySerializer() {
        this.gson = new GsonBuilder().create();
    }

    public TestMessage deserialize(String json) {
        return this.gson.fromJson(json, TestMessage.class);
    }

    public String serialize(TestMessage m) {
        return this.gson.toJson(m);
    }


}
