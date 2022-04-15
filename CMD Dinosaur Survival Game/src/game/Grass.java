package game;

import edu.monash.fit2099.engine.Ground;

public class Grass extends Land {
  private int age = 0;

  public Grass() {
    super(',');
    this.addSkill(FoodSkills.HERBIVORE_FOOD);
    // addSkill(GrowStatus.ALIVE);
  }

  // @Override
  // @Override
  // public void tick(Location location) {
  // super.tick(location);
  //
  // age++;
  // if (age == 10)
  // displayChar = ',';
  // // if (age == 20)
  // // displayChar = 'S';
  // }

  /**
   * @return the amount to heal a herbivore by after being eaten
   */
  @Override
  public int getHealValue() {
    return 10;
  }

}
