import Client.Client;
import Server.ServerExample;
import org.junit.Test;

/**
 * Created by Windows on 25.02.2017.
 */
public class TestAll {
    @Test
    public void testAll(){
        ServerExample serverExample = new ServerExample();
        serverExample.start();
        Client client = new Client();
        client.start();

    }
}
