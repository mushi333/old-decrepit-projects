package game;

/**
 * 
 * Properties a class may have to mark it as a potential food source. These skills can belong to an
 * Actor, Ground, or Item
 *
 */
public enum FoodSkills {

  /**
   * Indicate that Herbivores consider this class a food source and can eat it
   */
  HERBIVORE_FOOD,


  /**
   * Indicate that Carnivores consider this class a food source and can eat it
   */
  CARNIVORE_FOOD,

  /**
   * Indicate that Marine Dinosaurs considers this class a food source and can eat it
   */
  MARINE_FOOD
}
