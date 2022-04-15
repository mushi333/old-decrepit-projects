package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class BabyTRex extends Dinosaur {
  private final DinoType TYPE;
  protected boolean winning;

  /**
   * Constructor. A Baby T-Rex wiil be represented by a 'r' and hatch (from an egg) with 10 hit
   * points. Hit points are used to serve the purpose of hunger or food points.
   * 
   * @param name the name of this dinosaur
   */
  public BabyTRex(String name, boolean winning) {
    super(name);

    // If this is the winning dinosaur
    this.winning = winning;

    // A baby dinosaur represented by a lower-case
    this.hitPoints = 10;
    this.maxHitPoints = 100;
    this.displayChar = 'r';

    // So it can be feed carnivorous food.
    this.TYPE = DinoType.CARNIVORE;

    // Adds behaviours.
    this.actionFactories.add(new CarnivoreBehaviour());
    this.actionFactories.add(new WanderBehaviour());

    // Can move in land only
    this.addSkill(MovingSkills.CAN_MOVE_THROUGH_LAND);
  }


  /**
   * Select and return an action to perform on the current turn. After surviving 30 turns, a baby
   * can grow into its adult form.
   *
   * @param actions collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in
   *        conjunction with Action.getNextAction()
   * @param map the map containing the Actor
   * @param display the I/O object to which messages may be written
   * @return the Action to be performed
   */
  @Override
  public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
    Action originalAction = super.playTurn(actions, lastAction, map, display);

    if (this.age >= 30)
      return new GrowAction(this.name);

    return super.playTurn(actions, lastAction, map, display);
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
