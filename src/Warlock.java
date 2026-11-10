import java.util.ArrayList;
import java.util.List;

public class Warlock extends Character {
    private static final int WARLOCK_HP = 100;
    private static final int WARLOCK_MIN_ATTACK = 6;
    private static final int WARLOCK_MAX_ATTACK = 12;
    private static final int HEAL_AMOUNT = 10;
    private static final int BLOCK_AMOUNT = 5;
    private static final int CURSE_DAMAGE = 10;
    private static final int CURSE_ROUNDS = 3;

    private final List<DamageOverTime> activeCurses = new ArrayList<>();

    public Warlock() {
        super(WARLOCK_HP, WARLOCK_MIN_ATTACK, WARLOCK_MAX_ATTACK, HEAL_AMOUNT, BLOCK_AMOUNT);
    }

    public int tickDamage() {
        int total = 0;
        for (DamageOverTime curse : activeCurses) {
            total += curse.tick();
        }
        activeCurses.removeIf(DamageOverTime::isFinished);
        return total;
    }

    @Override
    public String getSpecialDescription() {
        return String.format("Förbannar fienden och gör %d skada per runda i %d rundor. Förbannelser kan staplas.",
                CURSE_DAMAGE, CURSE_ROUNDS);
    }

    @Override
    public String useSpecial(Character target) {
        activeCurses.add(new DamageOverTime(CURSE_DAMAGE, CURSE_ROUNDS));
        return getName() + " förbannar " + target.getName() + " i " + CURSE_ROUNDS + " rundor ";
    }
}




