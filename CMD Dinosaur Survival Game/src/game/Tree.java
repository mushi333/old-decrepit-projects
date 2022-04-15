package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A simple growing tree to exist within the map. Can be eaten by Herbivores.
 *
 */
public class Tree extends Land {
  private int age = 0;
  private int healValue = 5;

  /**
   * Constructor.
   */
  public Tree() {
    super('+');
    this.addSkill(FoodSkills.HERBIVORE_FOOD);
    this.addSkill(GrowStatus.ALIVE);
  }

  /**
   * As time passes, the tree gets older.
   */
  @Override
  public void tick(Location location) {
    super.tick(location);

    age++;
    if (age == 10) {
      this.displayChar = 't';
      this.healValue = 10;
    }
    if (age == 20) {
      this.displayChar = 'T';
      this.healValue = 20;
    }
  }

  /**
   * @return the amount to heal a herbivore by after being eaten
   */
  @Override
  public int getHealValue() {
    return this.healValue;
  }
}
