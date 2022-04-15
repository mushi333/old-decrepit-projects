package game;

/**
 * The herbivore food that the Protoceratops will eat.
 */
public class HerbivoreFood extends Food {

  /**
   * Constructor. Sets the name out as "Herbivore Food" and allows the player to have the skill of
   * being able to feed herbivores.
   */
  public HerbivoreFood() {
    super("Herbivore Food");
    this.addSkill(ItemSkills.CAN_FEED_HERBIVORE);
    this.addSkill(FoodSkills.HERBIVORE_FOOD);
    this.setPrice(20);
  }

}
