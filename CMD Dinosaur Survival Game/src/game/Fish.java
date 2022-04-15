package game;

import edu.monash.fit2099.engine.*;

import java.util.List;


public class Fish extends Actor {
    private Behaviour behaviour;

    public Fish() {
        super("Fish", 'f', 5);
        this.addSkill(MovingSkills.CAN_MOVE_THROUGH_WATER);
        behaviour = new WanderBehaviour();
    }

    /**
     * Fish will only wander around the lake.
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.hitPoints--;
        if (this.isConscious()) {
            Action wander = behaviour.getAction(this, map);
            if (wander != null)
                return wander;

            return new DoNothingAction();
        }
        else {
            map.removeActor(this);
        }
        return new DoNothingAction();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void removeItemFromInventory(int i) {

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public DinoType getTYPE() {
        return DinoType.NA;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<Behaviour> getActionFactories() {
        return null;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean isHungry() {
        return false;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void deposit(int value) {

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void withdraw(int value) {

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public int getBalance() {
        return 0;
    }
}
