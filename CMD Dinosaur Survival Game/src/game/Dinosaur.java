package game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * An abstract dinosaur class intended as a basis of implementing various types of dinosaurs
 */
public abstract class Dinosaur extends Actor {

  // Collection of Behaviours for a Dinosaur who could perform a range of behaviours
  public List<Behaviour> actionFactories = new ArrayList<Behaviour>();
  protected int age = 0;
  protected Random rand = new Random();


  /**
   * Constructor. Hit points are used to serve the purpose of hunger or food points.
   * 
   * @param name the name of this dinosaur
   */
  public Dinosaur(String name) {
    super(name, 'd', 100);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
    Actions list = super.getAllowableActions(otherActor, direction, map);
    list.add(new AttackAction(this));
    list.add(new FeedAction(this));
    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Behaviour> getActionFactories() {
    return this.actionFactories;
  }

  /**
   * Select and return an action to perform on the current turn. This is the standard Dinosaur turn,
   * which checks for some priority event-based actions (staving to death, hunger alerts, random
   * breeding) before playing out a standard turn based on behaviours assigned to the instance.
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

    if (!this.isConscious())
      return new DieAction();

    if (this.isHungry())
      System.out.println(this.name + " at (" + map.locationOf(this).x() + ","
          + map.locationOf(this).y() + ") is hungry.");


    // If a dinosaur is over 50% hitpoints, there is a 1% chance it will lay an egg (Breed)
    if (((float) this.hitPoints / this.maxHitPoints) >= 0.5 && rand.nextDouble() >= 0.99
        && this.hasSkill(DinoSkills.CAN_BREED)) {
      return new BreedAction();
    }

    this.age++;
    this.hurt(1);

    for (Behaviour factory : actionFactories) {
      Action action = factory.getAction(this, map);
      if (action != null) {
        return action;
      }
    }

    return new DoNothingAction();

  }

  /**
   * A dinosaur is considered hungry if its percentage of hitpoints is <= 75%
   * 
   * @return True if dinosaur is considered hungry
   */
  @Override
  public boolean isHungry() {
    return ((float) this.hitPoints / this.maxHitPoints) <= 0.75;
  }

}
