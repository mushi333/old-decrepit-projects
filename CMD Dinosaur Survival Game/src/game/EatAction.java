package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.ActorInterface.DinoType;


/**
 * Class allowing actors to perform an eat action on a given target
 * 
 * TODO: Handle Items
 * 
 * TODO: Dynamic handling of different healing values
 * 
 */

public class EatAction extends Action {

  private Location foodLocation;
  private Random rand = new Random();

  /**
   * Constructor
   * 
   * @param foodLocation the location on the map containing the food source to be eaten
   */

  public EatAction(Location foodLocation) {
    this.foodLocation = foodLocation;
  }


  /**
   * Handle disposal of eaten target, and heal executing actor by the appropriate amount. Currently
   * contains some randomness to keep things from getting too predictable.
   *
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return a description of what happened that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    int healValue = 0;
    Item removedItem = null;
    String returnString = "";

    // 90% chance the eat action is successful
    if (rand.nextDouble() >= 0.1) {

      if (foodLocation.getGround().hasSkill(FoodSkills.HERBIVORE_FOOD)) {
        healValue = foodLocation.getGround().getHealValue();
        returnString = actor + " ate " + foodLocation.getGround().getClass().getSimpleName();
        foodLocation.setGround(new Dirt());
      }

      if (foodLocation.getItems().size() != 0) {
        for (Item item : foodLocation.getItems()) {
          System.out.println(item.getClass().getSimpleName());
          if (item.hasSkill(FoodSkills.HERBIVORE_FOOD) && (actor.getTYPE() == DinoType.HERBIVORE)) {
            // TODO: set healValue properly
            healValue = item.getHealValue();
            removedItem = item;
            returnString = actor + " ate " + item;
          }
          if (item.hasSkill(FoodSkills.CARNIVORE_FOOD) && (actor.getTYPE() == DinoType.CARNIVORE)) {
            // TODO: set healValue properly
            healValue = item.getHealValue();
            removedItem = item;
            returnString = actor + " ate " + item;
          }
//          else {
//            throw new IllegalArgumentException("Problem eating item....");
//          }
        }
      }

      if (removedItem != null) {
        System.out.println("Removing item");
        foodLocation.removeItem(removedItem);
      }


      actor.heal(healValue);
      System.out.println("Dino healed by " + healValue);
      return returnString;
    } else {
      return actor + " tried to eat, but somehow failed.";
    }
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " eats tree";
  }
}
