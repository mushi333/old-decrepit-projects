package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * The egg of the Protoceratops. Hatches into a Protoceratops or can be sold.
 */
public class ProtoceratopsEgg extends Egg {

  /**
   * Constructor. Sets the name to "Protoceratop Egg" and takes 8 turns to hatch. Also sells for 50.
   */
  public ProtoceratopsEgg() {
    super("Protoceratop Egg");
    this.setTurnsTillHatch(5);
    this.setPrice(50);

    this.addSkill(FoodSkills.CARNIVORE_FOOD);
  }

  /**
   * Decrements the turns till hatched. Can only occur if the egg is on the ground. Will also spawn
   * the Protoceratops there.
   * 
   * @param currentLocation The location of the ground on which we lie.
   */
  @Override
  public void tick(Location currentLocation) {
    if (!(currentLocation.getGround() instanceof Floor)) {
      this.decrementTurns();

      if (this.getTurnsTillHatch() <= 0 && !currentLocation.containsAnActor()) {
        currentLocation.addActor(new BabyProtoceratops("Protoceratops"));
        currentLocation.removeItem(this);
      }
    }
  }

  @Override
  public int getHealValue() {
    return 50;
  }
}
