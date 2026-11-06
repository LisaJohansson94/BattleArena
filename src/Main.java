public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Game game = new Game();
    boolean running = true;

    while (running) {
        System.out.println("\n=== VÄLKOMMEN ===");
        System.out.println("1. Starta nytt spel");
        System.out.println("2. Avsluta");
        System.out.print("Välj ett alternativ (1-2): ");

        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            game.startNewGame();
        } else if (choice.equals("2")) {
            System.out.println("Avslutar programmet.");
            running = false;
        } else {
            System.out.println("Ogiltigt val. Välj 1 eller 2.");
        }
    }

    scanner.close();
}
