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

    public Character(String name, int hp, int minAttack, int maxAttack, int healAmount, int blockAmount) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.healAmount = healAmount;
        this.blockAmount = blockAmount;
    }

    @Override
    public abstract String useSpecial(Character target);

    public int calculateDamage() {
        return Math.clamp(0, maxAttack, random.nextInt(minAttack, maxAttack + 1) - blockAmount);
    }

    public void takeDamage(int amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }

    public void heal() {
        hp += healAmount;
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

    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}



