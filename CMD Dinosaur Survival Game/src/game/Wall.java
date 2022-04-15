package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A wall. Actors can't go through walls.
 *
 */
public class Wall extends Ground {

  /**
   * Constructor. Blocks vegetation from growing on top of it.
   */
  public Wall() {
    super('#');
    this.addSkill(GrowStatus.BLOCK);
  }

  /**
   * Actors can't go through walls.
   */
  @Override
  public boolean canActorEnter(Actor actor) {
    return false;
  }

  /**
   * You can't throw objects through walls.
   */
  @Override
  public boolean blocksThrownObjects() {
    return true;
  }

  /**
   * @return zero as Herbivores should not eat Walls. Walls are not very nutritious
   */
  @Override
  public int getHealValue() {
    return 0;
  }
}
