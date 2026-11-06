public class Warrior extends Character {
    private static final int WARRIOR_HP = 120;
    private static final int WARRIOR_MIN_ATTACK = 8;
    private static final int WARRIOR_MAX_ATTACK = 16;
    private static final int SPECIAL_DAMAGE = 25;
    private static final int HEAL_AMOUNT = 10;
    private static final int BLOCK_AMOUNT = 5;

    public Warrior() {
        super(WARRIOR_HP, WARRIOR_MIN_ATTACK, WARRIOR_MAX_ATTACK, HEAL_AMOUNT, BLOCK_AMOUNT);
    }

    @Override
    public String getSpecialDescription() {
        return String.format("Perform a heavy strike, hitting the enemy for %d damage.", SPECIAL_DAMAGE);
    }

    @Override
    public String useSpecial(Character target) {
        target.takeDamage(SPECIAL_DAMAGE);
        return getName() + " använder sin specialförmåga ";
    }
}
