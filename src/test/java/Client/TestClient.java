package Client;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Windows on 25.02.2017.
 */
public class TestClient {
    @Test
    public void testParseMessage(){
        Message message;
        Client client = new Client();
        String inputMessage = "test message";
        message = client.parseMessage(inputMessage);

        Assert.assertEquals(null,message.getSender());
        Assert.assertEquals(null,message.getReceiver());
        Assert.assertEquals(inputMessage,message.getBody());

        inputMessage = "@Alex:test message for Alex";
        message = client.parseMessage(inputMessage);

        Assert.assertEquals(null,message.getSender());
        Assert.assertEquals("Alex",message.getReceiver());
        Assert.assertEquals("test message for Alex",message.getBody());
    }
}
