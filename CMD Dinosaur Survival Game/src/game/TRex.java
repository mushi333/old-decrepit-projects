package game;

import edu.monash.fit2099.engine.*;

public class TRex extends BabyTRex {
  private final DinoType TYPE;

  /**
   * Constructor. A T-Rex wiil be represented by a 'R'. Hit points are used to serve the purpose of
   * hunger or food points. This Dinosaur has a lot of hitpoints.
   * 
   * @param name the name of this dinosaur
   */
  public TRex(String name, boolean winning) {
    super(name, winning);
    this.maxHitPoints = 1000;
    this.displayChar = 'R';
    this.addSkill(DinoSkills.CAN_BREED);

    // Check to see if the player won
    this.winning = winning;
    if (this.winning) {
      System.out.println("You have won the game.");
      java.lang.System.exit(0);
    }
    else
      this.winning = true;

    // Sets it to receive carnivore food.
    this.TYPE = DinoType.CARNIVORE;

    // Can move in land only
    this.addSkill(MovingSkills.CAN_MOVE_THROUGH_LAND);

    this.addSkill(DinoSkills.CAN_BREED);
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
