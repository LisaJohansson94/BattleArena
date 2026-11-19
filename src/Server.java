import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Server {
    public static final int PORT = 8080;
    private static final Path SAVE_FILE_PATH = Path.of("matches.json");
    private static final ObjectMapper jsonMapper = new ObjectMapper();

    static void main() {


        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port: " + PORT);

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Client connected: " + clientSocket.getInetAddress());


                String clientMessage;

                while ((clientMessage = in.readLine()) != null) {
                    MatchJson match = jsonMapper.readValue(clientMessage, MatchJson.class);

                    System.out.printf("Received from client: %s%n", match.toString());
                    out.println("Server echo: Match saved");
                }
            }
            System.out.println("Server shut down");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveMatch(MatchJson json) throws IOException {
        var matches = getMatches();
        matches.add(json);
        Files.writeString(SAVE_FILE_PATH, jsonMapper.writeValueAsString(matches));
    }

    private List<MatchJson> getMatches() throws IOException {
        if (!Files.exists(SAVE_FILE_PATH)) {
            return new ArrayList<>();
        }

        MatchJson[] matchJsons = jsonMapper.readValue(Files.readAllBytes(SAVE_FILE_PATH), MatchJson[].class);
        return new ArrayList<>(Arrays.asList(matchJsons));
    }
}
