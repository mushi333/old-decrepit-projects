package game;

public class BabyPteranodon extends Dinosaur {

  public BabyPteranodon(String name) {
    super(name);

    this.hitPoints = 10;
    this.maxHitPoints = 50;
    this.displayChar = 'd';

    // Add type of diet so it can be fed


    // Adds behaviours
    FeedingBehaviour fastCarnivore = new CarnivoreBehaviour();
    fastCarnivore.setMoveSpeed(2);
    this.actionFactories.add(fastCarnivore);
    this.actionFactories.add(new WanderBehaviour());

    // Can move in water and land
    this.addSkill(MovingSkills.CAN_MOVE_THROUGH_WATER);
    this.addSkill(MovingSkills.CAN_MOVE_THROUGH_LAND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeItemFromInventory(int i) {

  }

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
