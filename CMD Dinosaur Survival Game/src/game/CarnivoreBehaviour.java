package game;

import java.util.List;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class that determines the actions of a Carnivore. It will seek out Carnivore food sources, and
 * attack prey.
 *
 */
public class CarnivoreBehaviour extends FeedingBehaviour implements Behaviour {

  private Random random = new Random();


  /**
   * Constructor.
   * 
   */
  public CarnivoreBehaviour() {

  }

  /**
   * Determine next move based on Carnivore behaviours. Dinosaur will search for, move towards, and
   * eventually eat, an edible food source
   * 
   * @param actor the actor performing this behaviour
   * @param map the current game map the actor is located on
   * @return the next action the actor will perform
   */
  @Override
  public Action getAction(Actor actor, GameMap map) {

    // If dinosaur is not hungry, it won't perform its feeding behaviour
    if (!actor.isHungry())
      return null;

    Location location = map.locationOf(actor);
    List<Exit> exits = location.getExits();

    if (isFoodSource(location, FoodSkills.CARNIVORE_FOOD)) {
      this.foodTarget = null;
      this.dinoTarget = null;
      return new EatAction(location);
    }

    // Search for new target if dinosaur doesn't have one
    if (this.foodTarget == null && this.dinoTarget == null) {
      // TODO: Tweak radius option for balancing
      edibleFoodSearch(map, location, 30, FoodSkills.CARNIVORE_FOOD);
    }

    // No nearby food or prey, carnivore behavior cannot be performed
    if (this.foodTarget == null && this.dinoTarget == null)
      return null;

    // If the Carnivore has a target and is next to it. it will attempt to kill it.
    if (this.dinoTarget != null) {
      if (nextToTarget(map, location)) {
        return new KillAction(this.dinoTarget);
      }
    }

    if (this.foodTarget != null) {
      if (nextToTarget(map, location)) {
        System.out.println("Eating item they are next to!");
        return new EatAction(this.foodTarget);
      }
    }

    // Move towards the found food
    return approachTarget(map, actor, location, FoodSkills.CARNIVORE_FOOD);

  }

}
