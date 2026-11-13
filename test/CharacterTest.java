import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private Archer archer;
    private Warrior target;

    @BeforeEach
    void setUp() {
        archer = new Archer();
        target = new Warrior();
    }

    @Test
    void HpStaysAtZero() {
        archer.takeDamage(999);
        assertEquals(0, archer.getHp());
    }

    @Test
    void HealDoesNotExceedMaxHp() {
        archer.takeDamage(5);
        archer.heal();
        assertEquals(archer.getMaxHp(), archer.getHp());
    }

    @Test
    void CalculateDamageStaysWithinBounds() {
        for (int i = 0; i < 50; i++) {
            int damage = archer.calculateDamage();
            assertTrue(damage >= 0);
            assertTrue(damage <= archer.getMaxHp());
        }


    }

    @Test
    void newCharacterShouldBeAlive() {

        assertTrue(archer.isAlive());
    }

    @Test
    void characterWithZeroHpShouldNotBeAlive() {
        archer.takeDamage(999);
        assertFalse(archer.isAlive());
    }
}
