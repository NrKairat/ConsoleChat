package Server;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Windows on 25.02.2017.
 */
public class TestServer {
    @Test
    public void testIsNameFree(){
        ServerExample serverExample = new ServerExample();
        boolean isName = serverExample.isNameFree("Alex");
        Assert.assertEquals(true,isName);

        ClientHandler handler = new ClientHandler(null,null,1);
        handler.setName("Alex");
        serverExample.clients.add(handler);

        isName = serverExample.isNameFree("Alex");
        Assert.assertEquals(false,isName);
    }
}
