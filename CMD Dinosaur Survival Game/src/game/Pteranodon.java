package game;

public class Pteranodon extends BabyPteranodon {

  public Pteranodon(String name) {
    super(name);

    this.maxHitPoints = 100;
    this.displayChar = 'D';

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
