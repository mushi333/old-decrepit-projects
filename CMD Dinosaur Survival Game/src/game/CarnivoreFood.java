package game;

/**
 * The food that carnivorous Velociraptors eat.
 */
public class CarnivoreFood extends Food {

  /**
   * Constructor. Sets the name to "Carnivore Food" and adds the skill that it can feed carnivores.
   */
  public CarnivoreFood() {
    super("Carnivore Food");
    this.addSkill(ItemSkills.CAN_FEED_CARNIVORE);
    this.addSkill(FoodSkills.CARNIVORE_FOOD);
    this.setPrice(30);
  }

}
