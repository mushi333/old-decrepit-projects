package game;

import edu.monash.fit2099.engine.Action;

/**
 * Class for creating a Tag item for use by the player
 */

public class Tag extends PortableDinoItem {

  /**
   * Constructor
   */
  public Tag() {
    super("Dino Tag", 't');
    this.setPrice(0);
    this.addSkill(ItemSkills.CAN_TAG);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addAction(Action action) {
    this.allowableActions.add(action);
  }
}
