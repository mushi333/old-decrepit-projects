package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A door. Player can enter but dinos cannot.
 *
 */
public class Door extends Ground {

    /**
     * Constructor. Blocks vegetation from growing on top of it.
     */
    public Door() {
        super(':');
        this.addSkill(GrowStatus.BLOCK);
    }

    /**
     * Actors can't go through walls.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasSkill(MovingSkills.CAN_MOVE_THROUGH_DOOR)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * You can't throw objects through walls.
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * @return zero as Herbivores should not eat doors. doors are not very nutritious.
     */
    @Override
    public int getHealValue() {
        return 0;
    }
}
