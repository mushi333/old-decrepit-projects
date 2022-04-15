package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.interfaces.ActorInterface;

import java.util.List;

/**
 * The feed action. The player is able to feed the dinosaurs directly from his inventory if he has the specified food
 * type.
 */
public class FeedAction extends Action {
    protected Actor target;

    /**
     * Constructor. Retrieves the target actor to feed.
     * @param target The target actor (which is a dinosaur) in which the player feeds.
     */
    public FeedAction(Actor target) {
        this.target = target;
    }

    /**
     * It checks to see if the player has the relevant skill in feeding the specific dino type (carnivores or
     * herbivore) and returns the result on whether it was successful or not.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string description of what happened, if it was successful in feeding.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        // Checks to see if both the player has herbivore food and if the dinosaur is a herbivore itself.
        if (target.getTYPE() == ActorInterface.DinoType.HERBIVORE) {
            if (actor.hasSkill(ItemSkills.CAN_FEED_HERBIVORE)) {
                int healBy = 1000;
                target.heal(healBy);
                result = actor + " fed " + target + ".";

                // Removing item feature
                List<Item> itemList = actor.getInventory();
                for (int i = 0; i < itemList.size(); i++) {
                    if (itemList.get(i) instanceof HerbivoreFood) {
                        actor.removeItemFromInventory(i);
                        break;
                    }
                }
            }
            else {
                result = actor + " does not have Herbivore food.";
            }
        }
        // Checks to see if both the player has Carnivore food and if the dinosaur is a Carnivore itself.
        else if (target.getTYPE() == ActorInterface.DinoType.CARNIVORE) {

            if (target.getTYPE() == ActorInterface.DinoType.CARNIVORE) {
                if (actor.hasSkill(ItemSkills.CAN_FEED_CARNIVORE)) {
                    int healBy = 1000;
                    target.heal(healBy);
                    result = actor + " fed " + target + ".";

                    // Removing item feature
                    List<Item> itemList = actor.getInventory();
                    for (int i = 0; i < itemList.size(); i++) {
                        if (itemList.get(i) instanceof CarnivoreFood) {
                            actor.removeItemFromInventory(i);
                            break;
                        }
                    }
                } else {
                    result = actor + " does not have Carnivore food.";
                }
            }
        }
        // Checks to see if both the player has Marine food and if the dinosaur is a Marine dinosaur itself.
        else if (target.getTYPE() == ActorInterface.DinoType.MARINE) {

            if (target.getTYPE() == ActorInterface.DinoType.MARINE) {
                if (actor.hasSkill(ItemSkills.CAN_FEED_MARINE)) {
                    int healBy = 1000;
                    target.heal(healBy);
                    result = actor + " fed " + target + ".";

                    // Removing item feature
                    List<Item> itemList = actor.getInventory();
                    for (int i = 0; i < itemList.size(); i++) {
                        if (itemList.get(i) instanceof MarineFood) {
                            actor.removeItemFromInventory(i);
                            break;
                        }
                    }
                } else {
                    result = actor + " does not have marine food.";
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }
}
