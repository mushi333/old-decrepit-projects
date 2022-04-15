package game;

import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class for Player to perform a tag action
 */
public class TagAction extends Action {
  private Actor target;

  /**
   * Constructor
   * 
   * @param target the actor being tagged with the action
   */
  public TagAction(Actor target) {
    this.target = target;
  }

  /**
   * Tag Dinosaur for sale. This will remove the tag item from the Players inventory and increase
   * players money
   *
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return a description of what happened that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    String result = actor + " tagged " + target.toString();

    // Checks to see if the player has a tag and if the target is conscious.
    if (actor.hasSkill(ItemSkills.CAN_TAG) && target.isConscious()) {
      map.removeActor(target);

      // Removing item feature
      List<Item> itemList = actor.getInventory();
      // Loops through and searches for the item, then removesit.
      for (int i = 0; i < itemList.size(); i++) {
        if (itemList.get(i) instanceof Tag) {
          actor.removeItemFromInventory(i);
          // Deposits money into the players account
          int price = 100;
          if (target instanceof Protoceratops)
            actor.deposit(price);
          else if (target instanceof Velociraptor) {
            price = 200;
            actor.deposit(price);
          }
          else if (target instanceof Plesiosaurs) {
            price = 300;
            actor.deposit(price);
          }
          else if (target instanceof Pteranodon) {
            price = 400;
            actor.deposit(price);
          }
          else if (target instanceof TRex) {
            price = 1000;
            actor.deposit(price);
          }
          result += " for the price of $" + price;
          return result;
        }
      }
      // If the player doesn't have the tag.
    } else if (!(actor.hasSkill(ItemSkills.CAN_TAG))) {
      result = actor + " does not have a tag.";
    }
    // Currently conscious counts as being healthy.
    else if (!(target.isConscious())) {
      result = target + " is not healthy enough.";
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " tags " + target;
  }
}
