package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class BabyProtoceratops extends Dinosaur {
  private final DinoType TYPE;

  /**
   * Constructor. A BabyProtoceraptops wiil be represented by a 'p' and hatch (from an egg) with 10
   * hit points. Hit points are used to serve the purpose of hunger or food points.
   * 
   * @param name the name of this dinosaur
   */
  public BabyProtoceratops(String name) {
    super(name);

    this.hitPoints = 10;
    this.maxHitPoints = 50;
    this.displayChar = 'p';

    // Can be eaten by carnivorous dinosaurs
    this.addSkill(FoodSkills.CARNIVORE_FOOD);

    // So it can be feed herbivorous food.
    this.TYPE = DinoType.HERBIVORE;

    // So it can move on land but not water.
    this.addSkill(MovingSkills.CAN_MOVE_THROUGH_LAND);

    // Adds behaviours.
    this.actionFactories.add(new HerbivoreBehaviour());
    this.actionFactories.add(new WanderBehaviour());
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

    if (this.age >= 30 & this.displayChar == 'p')
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
    return DinoType.HERBIVORE;
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
