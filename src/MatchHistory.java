import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MatchHistory {
    private static final Path historyFile = Path.of("match_history.txt");

    static void main(String[] args) throws IOException {
        MatchHistory matchHistory = new MatchHistory();
        matchHistory.saveMatch("Warlock won");
        matchHistory.saveMatch("hulk Won");
        List<String> historyList = readHistory();
        for (String rad : historyList) {
            System.out.println(rad);
        }
    }

    public static List<String> readHistory() throws IOException {
        if (!Files.exists(historyFile)) {
            return List.of();
        }
        return Files.readAllLines(historyFile);
    }

    public void saveMatch(String matchResults) throws IOException {
        Files.writeString(historyFile, matchResults + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }


}

