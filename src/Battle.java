import java.util.Random;
import java.util.Scanner;

public class Battle {
    private final Character player;
    private final Character computer;
    private final String playerName;
    private final Scanner scanner;
    private final Random random = new Random();
    private boolean playerBlocking = false;
    private boolean computerBlocking = false;

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

        // Ett block gäller bara fram till blockarens nästa drag
        setBlocking(attacker, false);

        switch (action) {
            case ATTACK:
                int damage = attacker.calculateDamage();
                System.out.println(playerAction(attacker) + " attackerar.");
                if (isBlocking(defender)) {
                    int blocked = Math.min(damage, defender.getBlockAmount());
                    damage = damage - blocked;
                    setBlocking(defender, false);
                    System.out.println(playerAction(defender) + " blockerar " + blocked + " skada!");
                }
                defender.takeDamage(damage);
                break;
            case SPECIAL:
                System.out.println(attacker.useSpecial(defender));
                break;
            case BLOCK:
                setBlocking(attacker, true);
                System.out.println(playerAction(attacker) + " gör sig redo att blocka nästa attack.");
                break;
            case HEAL:
                int hpBeforeHeal = attacker.getHp();
                attacker.heal();
                int healed = attacker.getHp() - hpBeforeHeal;
                System.out.println(playerAction(attacker) + " healar " + healed + " HP.");
                break;
        }

        int actualDamage = hpBefore - defender.getHp();
        if (actualDamage > 0) {
            System.out.println(playerAction(attacker) + " gjorde " + actualDamage + " skada.");
        }
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
            System.out.println("2. Blocka Skada");
            System.out.println("3. Heala Skada");
            System.out.println("4. Special: " + player.getSpecialDescription());
            System.out.print("Dina val (1-4): ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                return ActionType.ATTACK;
            } else if (choice.equals("2")) {
                return ActionType.BLOCK;
            } else if (choice.equals("3")) {
                return ActionType.HEAL;
            } else if (choice.equals("4")) {
                return ActionType.SPECIAL;

            } else {
                System.out.println("Ogiltigt val. Välj 1-4.");
            }
        }
    }

    private ActionType randomAction() {
        ActionType[] actions = ActionType.values();
        return actions[random.nextInt(actions.length)];
    }

    private boolean isBlocking(Character character) {
        if (character == player) {
            return playerBlocking;
        } else {
            return computerBlocking;
        }
    }

    private void setBlocking(Character character, boolean blocking) {
        if (character == player) {
            playerBlocking = blocking;
        } else {
            computerBlocking = blocking;
        }
    }

    private String playerAction(Character character) {
        if (character == player) {
            return playerName;
        } else {
            return "Datorn";
        }
    }
}

