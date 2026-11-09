import java.util.Random;
import java.util.Scanner;

public class Battle {
    private final Character player;
    private final Character computer;
    private final String playerName;
    private final Scanner scanner;
    private final Random random = new Random();

    public Battle(Character player, Character computer, String playerName, Scanner scanner) {
        this.player = player;
        this.computer = computer;
        this.playerName = playerName;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("\n=== STRIDEN BÖRJAR ===");
        System.out.println(playerName + " (" + player.getName() + ") mot Datorn (" + computer.getName() + ")");

        int round = 1;
        while (player.isAlive() && computer.isAlive()) {
            System.out.println("\n--- Runda " + round + " ---");

            applyCurses(player, computer);
            if (!computer.isAlive()) {
                break;
            }
            performAction(player, computer, choosePlayerAction());
            if (!computer.isAlive()) {
                break;
            }

            applyCurses(computer, player);
            if (!player.isAlive()) {
                break;
            }
            performAction(computer, player, randomAction());

            round++;
        }

        System.out.println("\n=== STRIDEN ÄR SLUT ===");
        if (player.isAlive()) {
            System.out.println(playerName + " vinner!");
        } else {
            System.out.println("Datorn vinner!");
        }
    }

    public void performAction(Character attacker, Character defender, ActionType action) {
        int hpBefore = defender.getHp();

        switch (action) {
            case ATTACK:
                defender.takeDamage(attacker.calculateDamage());
                System.out.println(playerAction(attacker) + " attackerar.");
                break;
            case SPECIAL:
                System.out.println(attacker.useSpecial(defender));
                break;
        }

        int actualDamage = hpBefore - defender.getHp();
        System.out.println(playerAction(attacker) + " gjorde " + actualDamage + " skada.");
        System.out.println(playerAction(attacker) + " HP: " + attacker.getHp() + "/" + attacker.getMaxHp());
        System.out.println(playerAction(defender) + " HP: " + defender.getHp() + "/" + defender.getMaxHp());
    }

    private void applyCurses(Character owner, Character target) {
        if (owner instanceof Warlock warlock) {
            int hpBefore = target.getHp();
            target.takeDamage(warlock.tickDamage());
            int actualDamage = hpBefore - target.getHp();
            if (actualDamage > 0) {
                System.out.println("Förbannelsen gör " + actualDamage + " skada på " + playerAction(target)
                        + ". HP: " + target.getHp() + "/" + target.getMaxHp());
            }
        }
    }

    private ActionType choosePlayerAction() {
        while (true) {
            System.out.println("Välj handling:");
            System.out.println("1. Attackera");
            System.out.println("2. Special: " + player.getSpecialDescription());
            System.out.print("Ditt val (1-2): ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                return ActionType.ATTACK;
            } else if (choice.equals("2")) {
                return ActionType.SPECIAL;
            } else {
                System.out.println("Ogiltigt val. Välj 1 eller 2.");
            }
        }
    }

    private ActionType randomAction() {
        ActionType[] actions = ActionType.values();
        return actions[random.nextInt(actions.length)];
    }

    private String playerAction(Character character) {
        if (character == player) {
            return playerName;
        } else {
            return "Datorn";
        }
    }
}
