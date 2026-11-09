import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean running = true;

        while (running) {
            System.out.println("\n=== VÄLKOMMEN ===");
            System.out.println("1. Starta nytt spel");
            System.out.println("2. Avsluta");
            System.out.println("Välj alternativ 1-2: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.println("\nAnge ditt namn: ");
                String playerName = scanner.nextLine();
                if (playerName.isEmpty()) {
                    playerName = "Spelare 1";
                }

                System.out.println("\nVälj din karaktär:");
                System.out.println("1. Archer");
                System.out.println("2. Warrior");
                System.out.println("3. Warlock");
                System.out.println("4. Hulk");
                System.out.println("Ange ditt val 1-4: ");

                String charChoise = scanner.nextLine();
                Character player = switch (charChoise) {
                    case "1" -> new Archer();
                    case "2" -> new Warrior();
                    case "3" -> new Warlock();
                    case "4" -> new Hulk();
                    default -> null;
                };
                Character computer = switch (random.nextInt(1, 5)) {
                    case 1 -> new Archer();
                    case 2 -> new Warrior();
                    case 3 -> new Warlock();
                    case 4 -> new Hulk();
                    default -> null;
                };

                Battle battle = new Battle(player, computer, playerName, scanner);
                battle.start();

            } else if (choice.equals("2")) {
                System.out.println("Avslutar programmet");
                running = false;
            } else {
                System.out.println("Ogiltigt val. Välj 1-2: ");
            }
        }
        scanner.close();
    }
}

