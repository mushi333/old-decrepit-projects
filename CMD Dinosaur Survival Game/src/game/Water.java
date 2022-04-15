package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents water.
 */
public class Water extends Ground {

    public Water() {
        super('~');
        this.addSkill(GrowStatus.DEAD);
    }

    /**
     * Non-water based actor's cannot enter.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasSkill(MovingSkills.CAN_MOVE_THROUGH_WATER)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public int getHealValue() {
        return 0;
    }
}
