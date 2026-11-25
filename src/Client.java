

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {
    static void main() {
        String host = "localhost";
        AtomicBoolean connected = new AtomicBoolean(true);


        try (Socket socket = new Socket(host, Server.PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            Thread.startVirtualThread(() -> {
                try {
                    String response;

                    while ((response = in.readLine()) != null) {
                        System.out.println("Response from server: " + response);
                    }
                    System.out.println("Server closed the connection.");
                } catch (IOException e) {
                    System.out.println("Connection lost: " + e.getMessage());
                }
                connected.set(false);
            });

            while (connected.get()) {
                String input = scanner.nextLine().trim();
                Random rng = new Random();

                if (!connected.get()) break;


                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                String matchResult = "shin: " + rng.nextInt(10, 101);
                out.println(matchResult);
                System.out.println("Sent to server: " + matchResult);


            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
