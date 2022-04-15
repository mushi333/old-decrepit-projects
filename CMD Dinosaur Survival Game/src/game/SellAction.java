package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special Action that allows Actors to sell items.
 */
public class SellAction extends DropItemAction {

	/**
	 * Constructor.
	 *
	 * @param item the item to sell.
	 */
	public SellAction(Item item) {
		super(item);
	}

	/**
	 * Drop the item.
	 *
	 * @param actor The actor performing the action
	 * @param map The map the actor is on
	 * @return a description of the action suitable for feedback in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (map.locationOf(actor).getGround() instanceof Floor){
			// Here it means we are at the shop floor.
			int sellPrice = (int) Math.round(item.getPrice() * 0.9);
			actor.deposit(sellPrice);
			actor.removeItemFromInventory(item);
			return menuDescriptionSold(actor);
		}
		else {
			actor.removeItemFromInventory(item);
			map.locationOf(actor).addItem(item);
			return menuDescription(actor);
		}

	}

	/**
	 * A string describing the action suitable for displaying in the UI menu, for when the item is just dropped.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Player drops X item.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " drops " + item;
	}

	/**
	 * A string describing the action suitable for displaying in the UI menu, for when the item is sold.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Player sells the X item for Y amount.
	 */
	private String menuDescriptionSold(Actor actor) {
		return actor + " sells the " + item + " for " + item.getPrice();
	}
}
