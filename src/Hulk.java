public class Hulk extends Character {
    private static final int HULK_HP = 100;
    private static final int HULK_MIN_ATTACK = 8;
    private static final int HULK_MAX_ATTACK = 14;
    private static final int RAGE_LIMIT = 40;

    public Hulk(String name) {
        super(name, HULK_HP, HULK_MIN_ATTACK, HULK_MAX_ATTACK);
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