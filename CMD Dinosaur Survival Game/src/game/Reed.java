package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A simple class of reeds that can only grow in water.
 */
public class Reed extends Ground {
    private int healValue = 5;

    public Reed() {
        super('|');
        this.addSkill(GrowStatus.ALIVE);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public int getHealValue() {
        return 0;
    }
}
