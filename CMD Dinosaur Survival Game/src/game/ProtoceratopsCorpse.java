package game;

/**
 * The corpse of the Protoceratops. Used to sell.
 */
public class ProtoceratopsCorpse extends Corpse {

  /**
   * Constructor. Sets the name to "Protoceratop Corpse" and sets the price to 50.
   */
  public ProtoceratopsCorpse() {
    super("Protoceratops Corpse");
    this.setPrice(50);
    this.addSkill(FoodSkills.CARNIVORE_FOOD);
  }
}
