import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final String host = "localhost";
    
    public void SendResult(String result) {
        try (Socket socket = new Socket(host, Server.PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println(result);
            System.out.println("Sent to server: " + result);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
