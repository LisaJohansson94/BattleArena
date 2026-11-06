import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Warlock extends Character {
    private static final double BLOCK_PERCENT = 0.40;
    private boolean specialDefending;
    private static final int TICK_ROUNDS = 3;
    private final List<DamageOverTime> activeTicks = new ArrayList<>();

    public Warlock(int hp, int minDamage, int maxDamage) {
        super(
                hp,
                minDamage,
                maxDamage);

    }

    public void attackTick() {
        activeTicks.add(new DamageOverTime(attack(), TICK_ROUNDS));
    }

    public int tickDamage() {
        int total = 0;
        for (DamageOverTime tick : activeTicks) {
            total += tick.tick();
        }
        activeTicks.removeIf(DamageOverTime::isFinished);
        return total;
    }

    @Override
    public int attack() {
        return random.nextInt(getmaxDamage() - getminDamage() + 1) + getminDamage();
    }

    @Override
    public int useSpecial() {
        specialDefending = true;
        return 0;
    }

    @Override
    public void takeDamage(int dmg) {
        if (specialDefending) {
            dmg = (int) Math.round(dmg * (1 - BLOCK_PERCENT));
            specialDefending = false;
        }
        super.takeDamage(dmg);
    }

}
}
