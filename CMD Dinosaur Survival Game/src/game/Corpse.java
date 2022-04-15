package game;

/**
 * The abstract class that represents the different corpses in the game.
 */

public abstract class Corpse extends PortableDinoItem {
  private int price;

  /**
   * Constructor. Sets the given name of the corpse and displays it as a capital c.
   * 
   * @param Name The given name of the corpse.
   */
  public Corpse(String Name) {
    super(Name, 'C');
    this.addSkill(FoodSkills.CARNIVORE_FOOD);
  }

  @Override
  public int getHealValue() {
    return 50;
  }
}
