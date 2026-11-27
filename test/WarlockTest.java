import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class WarlockTest {

    private Warlock warlock;
    private Warrior target;


    @BeforeEach
    void setUp() {
        warlock = new Warlock();
        target = new Warrior();

    }

    @Test
    void WarlockSpecialAddsCurse() {
        warlock.useSpecial(target);

        int damage = warlock.tickDamage();
        assertEquals(10, damage);
    }

    @Test
    void CursesCanStack() {
        warlock.useSpecial(target);
        warlock.useSpecial(target);

        int damage = warlock.tickDamage();
        assertEquals(20, damage);
    }
}