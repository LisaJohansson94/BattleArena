public class Mage extends Character {
    private static final int MAGE_HP = 90;
    private static final int MAGE_MIN_ATTACK = 8;
    private static final int MAGE_MAX_ATTACK = 18;
    private static final int HEAL_AMOUNT = 25;

    public Mage(String name) {

        super(name, MAGE_HP, MAGE_MIN_ATTACK, MAGE_MAX_ATTACK);
    }

    @Override
    public String useSpecial(Character target) {
        heal(HEAL_AMOUNT);
        return getName() + " använder sin heal";
    }
}







