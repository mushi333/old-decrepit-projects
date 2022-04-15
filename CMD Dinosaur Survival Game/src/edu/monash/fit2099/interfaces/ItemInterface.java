package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the
 * engine, or downcasting references in the game.
 */
public interface ItemInterface {
  /**
   * Retrieves the value of which it heals/feeds the dinosaurs.
   * @return An integer value of the healing/feeding.
   */
  public int getHealValue();

  /**
   * Gets the price of the item, used in depositing money to the player's account after selling,.
   * @return An integer value of the item.
   */
  public int getPrice();
}
