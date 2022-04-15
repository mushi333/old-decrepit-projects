package game;

import edu.monash.fit2099.engine.*;

/**
 * Special Action that allows Actors to buy items.
 */
public class BuyAction extends PickUpItemAction {

    /**
     * Constructor.
     *
     * @param item the item to buy.
     */
    public BuyAction(Item item) {
        super(item);
    }

    /**
     * Buys the item.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return a description of the action suitable for feedback in the UI, i.e. player bought x item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.locationOf(actor).getGround() instanceof Floor){
            // Check to see if the player has enough money.
            if (actor.getBalance() - item.getPrice() >= 0) {
                actor.withdraw(item.getPrice());
                // Check to see if the player bought an trex egg, which has special winning attribute
                if (item instanceof TRexEgg)
                    item = new TRexEgg(false);
                actor.addItemToInventory(item);
                return menuDescriptionBought(actor);
            }
            else {
                return menuDescriptionNotBought(actor);
            }
        }
        else {
            actor.removeItemFromInventory(item);
            map.locationOf(actor).addItem(item);
            return menuDescription(actor);
        }
    }

    /**
     * A string describing the action suitable for displaying in the UI menu, for when the item is just picked up.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player drops X item.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up " + item;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu, for when the item is bought.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player buys the X item for Y amount.
     */
    private String menuDescriptionBought(Actor actor) {
        return actor + " buys the " + item + " for " + item.getPrice();
    }

    /**
     * A string describing the action suitable for displaying in the UI menu, for when the item is bought.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player couldn't buy x due to price.
     */
    private String menuDescriptionNotBought(Actor actor) {
        return actor + " couldn't buy " + item + " because " +
                "player doesn't have $" + item.getPrice();
    }
}
