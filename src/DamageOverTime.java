public class DamageOverTime {
    private final int damagePerTick;
    private int roundsLeft;

    public DamageOverTime(int damagePerTick, int roundsLeft){
        this.damagePerTick = damagePerTick;
        this.roundsLeft = roundsLeft;
    }
    public int tick(){
        roundsLeft--;
        return damagePerTick;
    }
    public boolean isFinished(){
        return roundsLeft <= 0;
    }
}
