package game;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class that determines the actions of a Herbivore. It will seek out Herbivore food sources.
 * 
 */
public class HerbivoreBehaviour extends FeedingBehaviour implements Behaviour {

  private Random random = new Random();


  /**
   * Constructor.
   * 
   */
  public HerbivoreBehaviour() {

  }

  /**
   * Determine next move based on herbivore behaviours. Dinosaur will search for, move towards, and
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

    ArrayList<Action> actions = new ArrayList<Action>();

    Location location = map.locationOf(actor);

    // Realistically, Herbivores won't target an actor (dinoTarget)
    if (isFoodSource(location, FoodSkills.HERBIVORE_FOOD)) {
      this.foodTarget = null;
      return new EatAction(location);
    }



    // Search for new target if dinosaur doesn't have one
    if (this.foodTarget == null) {
      // TODO: Tweak radius option for balancing
      edibleFoodSearch(map, location, 30, FoodSkills.HERBIVORE_FOOD);
    }

    // No nearby food, herbivore behavior cannot be performed
    if (this.foodTarget == null)
      return null;

    // Move towards the found food
    return approachTarget(map, actor, location, FoodSkills.HERBIVORE_FOOD);

  }


}
