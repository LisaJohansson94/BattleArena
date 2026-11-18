import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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
        try {
            var server = new Server();
            server.saveMatch(new MatchJson("colin", 745));
            var matches = new Server().getMatches();
            System.out.println("test");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            System.out.println("Server is listening on port: " + PORT);
//
//            try (Socket clientSocket = serverSocket.accept();
//                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
//
//                System.out.println("Client connected: " + clientSocket.getInetAddress());
//
//                var tempMatch = new MatchJson("shin", 10);
//                String clientMessage;
//
//                while ((clientMessage = jsonMapper.writeValueAsString(tempMatch)) != null) {
//                    MatchJson match = jsonMapper.readValue(clientMessage, MatchJson.class);
//
//                    System.out.printf("Received from client: %s%n", match.toString());
//                    out.println("Server echo: Match saved");
//                }
//            }
//            System.out.println("Server shut down");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
