package game;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

  /**
   * Constructor. Blocks Vegetation from growing on top of it.
   */
  public Floor() {
    super('_');
    this.addSkill(GrowStatus.BLOCK);
  }

  /**
   * @return zero as Herbivores should not eat Floors. Floors are not very nutritious
   */
  @Override
  public int getHealValue() {
    return 0;
  }

}
