package game;

import edu.monash.fit2099.engine.Location;

/**
 * The Velociraptor's egg. Takes 5 turns to hatch and can be sold for 75.
 */
public class VelociraptorEgg extends Egg {

  /**
   * Constructor. Sets the name to "Velociraptor Egg", takes 5 turns to hatch and can be sold for
   * 75.
   */
  public VelociraptorEgg() {
    super("Velociraptor Egg");
    this.setTurnsTillHatch(4);
    this.setPrice(75);
  }

  /**
   * Decrements the turns till hatched. Can only occur if the egg is on the ground. Will also spawn
   * the Velociraptor there.
   * 
   * @param currentLocation The location of the ground on which we lie.
   */
  @Override
  public void tick(Location currentLocation) {
    if (!(currentLocation.getGround() instanceof Floor)) {
      this.decrementTurns();

      if (this.getTurnsTillHatch() <= 0 && !currentLocation.containsAnActor()) {
        currentLocation.addActor(new BabyVelociraptor("Velociraptor"));
        currentLocation.removeItem(this);
      }
    }
  }
}
