public class Hulk extends Character {
    private static final int HULK_HP = 100;
    private static final int HULK_MIN_ATTACK = 8;
    private static final int HULK_MAX_ATTACK = 14;
    private static final int HULK_HEAL_AMOUNT = 15;
    private static final int HULK_BLOCK_AMOUNT = 5;
    private static final int RAGE_LIMIT = 60;

    public Hulk() {
        super(HULK_HP, HULK_MIN_ATTACK, HULK_MAX_ATTACK, HULK_HEAL_AMOUNT, HULK_BLOCK_AMOUNT);
    }

    @Override
    public String getSpecialDescription() {
        return String.format("Hulk är arg och orsakar dubbel skada på motståndaren när han har %d HP eller mindre", RAGE_LIMIT);
    }

    @Override
    public String useSpecial(Character target) {
        int damage = calculateDamage();

        if (getHp() <= RAGE_LIMIT) {
            damage = damage * 2;
            target.takeDamage(damage);
            return getName() + " är arg och gör dubbel skada: " + damage + "!";
        }

        target.takeDamage(damage);
        return getName() + " är inte arg än och gör vanlig skada: " + damage;
    }
}















