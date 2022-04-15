package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class Land extends Ground {

    public Land(char displayChar) {
        super(displayChar);
    }

    /**
     * Non-water based actor's cannot enter.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasSkill(MovingSkills.CAN_MOVE_THROUGH_LAND)) {
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
