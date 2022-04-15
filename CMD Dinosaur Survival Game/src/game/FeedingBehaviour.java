package game;

import java.util.ArrayList;
import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.NumberRange;

/**
 * Abstract class as a basis for any feeding/eating behaviours an actor might implement
 *
 */

public abstract class FeedingBehaviour implements Behaviour {

  // A food target bound to a location (Ground, Item)
  protected Location foodTarget;

  // An actor (Dinosaur) that is considered prey
  protected Actor dinoTarget;

  // A modifier to enable some actors to move faster than others
  protected int moveSpeed = 1;

  /**
   * Constructor
   * 
   */
  public FeedingBehaviour() {

  }


  /**
   * Compute the Manhattan distance between two locations.
   * 
   * @param a the first location
   * @param b the first location
   * @return the number of steps between a and b if you only move in the four cardinal directions.
   */
  protected int distance(Location a, Location b) {
    return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
  }


  /**
   * Find the nearest edible food based on Ground.hasSkill, within a specified search radius. The
   * search area will expand until it finds a suitable food source, or it reaches its final
   * iteration.
   * 
   * @param map the current GameMap
   * @param location the current location of the hungry actor
   * @param radius the distance the dinosaur can search for food within. Perhaps larger dinosaurs
   *        can notice further away food sources.
   * @return ArrayList of nearby food sources
   */
  protected void edibleFoodSearch(GameMap map, Location location, int radius,
      FoodSkills foodSkill) {
    ArrayList<Location> radiusLocations = new ArrayList<>();

    // Search the immediately adjacent locations first
    List<Exit> currentExits = location.getExits();

    for (Exit radiusExit : currentExits) {
      radiusLocations.add(radiusExit.getDestination());
      if (isFoodSource(radiusExit.getDestination(), foodSkill)) {
        return;
      }

    }

    // If the dinosaur is not next to any food sources, it will look in the surrounding radius,
    // gradually expanding.
    int xMax = location.x();
    int xMin = location.x();
    int yMax = location.y();
    int yMin = location.y();

    NumberRange xRange = map.getXRange();
    NumberRange yRange = map.getYRange();


    for (int i = 0; i <= radius; i++) {

      // Check rows
      for (int x = xMin; x <= xMax && x >= xRange.min() && x <= xRange.max(); x++) {
        Location upper = map.at(x, yMax);
        if (isFoodSource(upper, foodSkill))
          return;
        Location lower = map.at(x, yMin);
        if (isFoodSource(lower, foodSkill))
          return;
      }

      // Check columns
      for (int y = yMin; y <= yMax && y >= yRange.min() && y <= yRange.max(); y++) {
        Location left = map.at(xMin, y);
        if (isFoodSource(left, foodSkill))
          return;
        Location right = map.at(xMax, y);
        if (isFoodSource(right, foodSkill))
          return;
      }

      if (xMax != xRange.max())
        xMax++;
      if (xMin != xRange.min())
        xMin--;
      if (yMax != yRange.max())
        yMax++;
      if (yMin != yRange.min())
        yMin--;
    }

    return;

  }

  /**
   * Checks a location for the various possible ways a food source could be positioned there.
   * 
   * @param location a location on the GameMap to be checked
   * @param foodSkill the food type being searched for (e.g. CARNIVORE_FOOD, HERBIVORE_FOOD)
   * @return
   */
  protected boolean isFoodSource(Location location, FoodSkills foodSkill) {

    Ground ground = location.getGround();
    if (ground.hasSkill(foodSkill)) {
      this.foodTarget = location;
      return true;
    }

    List<Item> items = location.getItems();
    for (Item item : items) {
      if (item.hasSkill(foodSkill)) {
        this.foodTarget = location;
        return true;
      }
    }

    if (location.containsAnActor()) {
      Actor actor = location.getActor();
      if (actor.hasSkill(foodSkill)) {
        this.dinoTarget = actor;
        return true;
      }
    }

    return false;
  }


  /**
   * Move towards the actors determined target, provided it still exists on the game map
   * 
   * @param map the current GameMap
   * @param actor the actor approaching a target
   * @param location the current location of the actor
   * @param foodSkill the type of food source the actor is approaching
   * @return
   */
  protected Action approachTarget(GameMap map, Actor actor, Location location,
      FoodSkills foodSkill) {
    if (this.foodTarget == null && this.dinoTarget == null)
      throw new IllegalArgumentException(
          "No target set for this instance of the behaviour. Cannot approach nothing.");

    if (this.foodTarget != null && this.dinoTarget != null)
      throw new IllegalArgumentException(
          "Two targets set for this instance of the behaviour. Cannot approach two targets at once.");


    // Check that targets still exist
    if (this.dinoTarget != null && !(map.contains(this.dinoTarget))) {
      this.dinoTarget = null;
      return null;
    }

    if (this.foodTarget != null && !isFoodSource(this.foodTarget, foodSkill)) {
      this.foodTarget = null;
      return null;
    }

    Action nextMoveAction = null;
    Location currentLocation = location;
    // Swap the target location with the location of the actor if approaching another actor
    Location targetLocation = this.foodTarget;
    if (this.dinoTarget != null)
      targetLocation = map.locationOf(this.dinoTarget);

    // repeat path-finding logic for the number of spaces an actor may move in their turn
    for (int i = 1; i <= this.moveSpeed; i++) {
      int currentDistance = distance(currentLocation, targetLocation);

      for (Exit exit : currentLocation.getExits()) {
        Location destination = exit.getDestination();
        if (destination.canActorEnter(actor)) {
          int newDistance = distance(destination, targetLocation);
          if (newDistance < currentDistance && this.dinoTarget != null) {
            // return new MoveActorAction(destination, exit.getName() + " towards its prey");
            nextMoveAction = new MoveActorAction(destination, exit.getName() + " towards its prey");
            currentLocation = destination;
          }
          if (newDistance < currentDistance && this.foodTarget != null) {
            // return new MoveActorAction(destination, exit.getName() + " towards food");
            nextMoveAction = new MoveActorAction(destination, exit.getName() + " towards its prey");
            currentLocation = destination;
          }
        }
      }
    }

    if (nextMoveAction != null)
      return nextMoveAction;

    return null;

  }

  protected boolean nextToTarget(GameMap map, Location location) {
    List<Exit> exits = location.getExits();

    for (Exit exit : exits) {
      if (exit.getDestination() == map.locationOf(this.dinoTarget)
          || exit.getDestination() == this.foodTarget)
        return true;
    }

    return false;
  }

  /**
   * Set a move speed value, allowing the user of this behaviour to move multiple spaces per turn.
   * 
   * @param modifier
   */

  public void setMoveSpeed(int modifier) {
    if (modifier < 1) {
      throw new IllegalArgumentException("An actor cannot move slower than 1");
    }

    this.moveSpeed = modifier;
  }
}
