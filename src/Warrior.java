public class Warrior extends Character {
    private static final int WARRIOR_HP = 120;
    private static final int WARRIOR_MIN_ATTACK = 8;
    private static final int WARRIOR_MAX_ATTACK = 16;
    private static final int SPECIAL_DAMAGE = 25;

    public Warrior(String name) {

        super(name, WARRIOR_HP, WARRIOR_MIN_ATTACK, WARRIOR_MAX_ATTACK);
    }

    @Override
    public String useSpecial(Character target) {
        target.takeDamage(SPECIAL_DAMAGE);
        return getName() + " använder sin specialförmåga ";
    }
}