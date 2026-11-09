public class Archer extends Character {
    private static final int ARCHER_HP = 100;
    private static final int ARCHER_MIN_ATTACK = 6;
    private static final int ARCHER_MAX_ATTACK = 14;
    private static final int SPECIAL_DAMAGE = 20;
    private static final int HEAL_AMOUNT = 15;
    private static final int BLOCK_AMOUNT = 5;

    public Archer() {
        super(ARCHER_HP, ARCHER_MIN_ATTACK, ARCHER_MAX_ATTACK, HEAL_AMOUNT, BLOCK_AMOUNT);
    }

    @Override
    public String getSpecialDescription() {
        return String.format("Uses Sniper Shot, hitting the enemy for %d damage. ", SPECIAL_DAMAGE);
    }

    @Override
    public String useSpecial(Character target) {
        if (random.nextInt(1, 6) == 3) {
            target.takeDamage(SPECIAL_DAMAGE * 2);
            return getName() + " gör en kritisk träff! ";
        } else {
            target.takeDamage(SPECIAL_DAMAGE);
            return getName() + " använder sin specialförmåga ";

        }
    }
}
