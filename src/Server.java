import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static final int PORT = 8080;

    static void main() {
        MatchHistory matchHistory = new MatchHistory();


        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                Thread.startVirtualThread(() -> handleClient(clientSocket, matchHistory));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket, MatchHistory matchHistory) {
        try (clientSocket;
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Client connected: " + clientSocket.getInetAddress());


            String clientMessage;

            while ((clientMessage = in.readLine()) != null) {
                matchHistory.saveMatch(clientMessage);

                System.out.printf("Received from client: %s%n", clientMessage);
                out.println("Server echo: Match saved");
            }
            System.out.println("Client Disconnected: " + clientSocket.getInetAddress());
        } catch (IOException e) {
            System.out.println("Problem with Client " + e.getMessage());
        }
    }
}
