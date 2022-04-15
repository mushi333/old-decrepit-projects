package game;

import edu.monash.fit2099.engine.Location;

/**
 * The TRex's egg. Takes 10 turns to hatch and can be sold for a high price.
 */
public class TRexEgg extends Egg {
    protected boolean winning;

    /**
     * Constructor. Sets the name to "TRex Egg", takes 10 turns to hatch and can be sold for
     * for a high price.
     */
    public TRexEgg(boolean winning) {
        super("TRex Egg");
        this.setTurnsTillHatch(10);
        this.setPrice(200);
        this.winning = winning;
    }

    /**
     * Decrements the turns till hatched. Can only occur if the egg is on the ground. Will also spawn
     * the TRex there.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (!(currentLocation.getGround() instanceof Floor)) {
            this.decrementTurns();

            if (this.getTurnsTillHatch() <= 0 && !currentLocation.containsAnActor()) {
                currentLocation.addActor(new BabyTRex("TRex", this.winning));
                currentLocation.removeItem(this);
            }
        }
    }
}