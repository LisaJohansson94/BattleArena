public class Archer extends Character {
    private static final int ARCHER_HP = 100;
    private static final int ARCHER_MIN_ATTACK = 6;
    private static final int ARCHER_MAX_ATTACK = 14;
    private static final int SPECIAL_DAMAGE = 20;

    public Archer(String name) {
        super(name, ARCHER_HP, ARCHER_MIN_ATTACK, ARCHER_MAX_ATTACK);
    }

    @Override
    public String useSpecial(Character target) {
        target.takeDamage(SPECIAL_DAMAGE);
        return getName() + " använder sin specialförmåga ";
    }
}

