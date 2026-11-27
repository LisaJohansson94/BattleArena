import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BattleTest {

    @Test
    void CorrectWinnerWhenTargetDies() {
        var archer = new Archer();
        var target = new Warrior();

        target.takeDamage(999);

        assertEquals(0, target.getHp());

    }


}