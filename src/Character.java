import java.util.Random;

public abstract class Character implements SpecialAbility {
    protected final Random random = new Random();
    private final String name;
    private final int maxHp;
    private final int minAttack;
    private final int maxAttack;
    private final int healAmount;
    private final int blockAmount;
    private int hp;

    public Character(int hp, int minAttack, int maxAttack, int healAmount, int blockAmount) {
        this.name = this.getClass().getSimpleName();
        this.hp = hp;
        this.maxHp = hp;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.healAmount = healAmount;
        this.blockAmount = blockAmount;
    }

    public String getStats() {
        return String.format("Health: %d, Damage: %d-%d, Heal Amount: %d, Block Amount: %d",
                hp, minAttack, maxAttack, healAmount, blockAmount);
    }

    public int calculateDamage() {
        return Math.clamp(random.nextInt(minAttack, maxAttack + 1) - blockAmount, 0, maxAttack);
    }

    public void takeDamage(int amount) {
        hp = hp - amount;
        if (hp < 0) hp = 0;
    }

    public void heal() {
        hp = hp + healAmount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public String getName() {
        return name;
    }
}
