package game;

/**
 * The abstract food class that will cover herbivore or carnivore food types.
 */
public abstract class Food extends PortableDinoItem {

  /**
   * Constructor. Sets out the food as a capital f in the map. Also sets out the name of the
   * specific food.
   * 
   * @param Name The name of the food.
   */
  public Food(String Name) {
    super(Name, 'F');
  }

  @Override
  public int getHealValue() {
    return 1000;
  }
}
