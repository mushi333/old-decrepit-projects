package game;

public class Plesiosaurs extends BabyPlesiosaurs {

  private final DinoType TYPE;

  public Plesiosaurs(String name) {
    super(name);

    this.maxHitPoints = 100;
    this.displayChar = 'O';

    // Add type of diet so it can be fed


    // Adds behaviours
    this.actionFactories.add(new CarnivoreBehaviour());
    this.actionFactories.add(new WanderBehaviour());

    // Add ability to move in water only
    this.addSkill(MovingSkills.CAN_MOVE_THROUGH_WATER);

    // Sets it to receive carnivore food.
    this.TYPE = DinoType.MARINE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeItemFromInventory(int i) {}

  /**
   * {@inheritDoc}
   */
  @Override
  public DinoType getTYPE() {
    return DinoType.CARNIVORE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deposit(int value) {}

  /**
   * {@inheritDoc}
   */
  @Override
  public void withdraw(int value) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getBalance() {
    return 0;
  }

}
