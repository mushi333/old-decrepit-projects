package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class Velociraptor extends BabyVelociraptor {
  private final DinoType TYPE;

  /**
   * Constructor. A Velociraptor wiil be represented by a 'V'. Hit points are used to serve the
   * purpose of hunger or food points.
   * 
   * @param name the name of this dinosaur
   */
  public Velociraptor(String name) {
    super(name);
    this.maxHitPoints = 100;
    this.displayChar = 'V';
    this.addSkill(DinoSkills.CAN_BREED);

    // Sets it to receive carnivore food.
    this.TYPE = DinoType.CARNIVORE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DinoType getTYPE() {
    return TYPE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
    Actions list = super.getAllowableActions(otherActor, direction, map);
    list.add(new FeedAction(this));
    list.add(new TagAction(this));
    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeItemFromInventory(int i) {}
}
