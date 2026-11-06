public abstract class Character implements SpecialAbility {
    private String name;
    private int hp;
    private int maxHp;
    private int minAttack;
    private int maxAttack;

    public Character(String name, int hp, int minAttack, int maxAttack) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
    }

    public int attack() {
        return minAttack + (int) (Math.random() * ((maxAttack - minAttack) + 1));
    }

    @Override
    public abstract String useSpecial(Character target);

    public void takeDamage(int amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp += amount;
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



