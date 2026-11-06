public class DamageOverTime {
    private final int damagePerTick;
    private int roundsleft;

    public DamageOverTime(int damagePerTick, int roundsLeft){
        this.damagePerTick = damagePerTick;
        this.roundsleft = roundsLeft;
    }
    public int tick(){
        roundsleft--;
        return damagePerTick;
    }
    public boolean isFinished(){
        return roundsleft <= 0;
    }
}
