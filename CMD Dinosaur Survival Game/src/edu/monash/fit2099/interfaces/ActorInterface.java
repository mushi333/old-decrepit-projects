package edu.monash.fit2099.interfaces;

import java.util.List;
import game.Behaviour;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the
 * engine, or downcasting references in the game.
 */

public interface ActorInterface {
    /**
     * Removes the item from the player's inventory based on the index. Used to remove item once it's used on the
     * dinosaur.
     * @param i The Given index of the item to remove.
     */
    void removeItemFromInventory(int i);

    /**
     * Enum for different types of dinosaurs, has HERBIVORE, CARNIVORE and NA for the player.
     */
    enum DinoType {
        HERBIVORE, CARNIVORE, MARINE, NA;
    }

    /**
     * A getter method that retrieves the type of dinosaur it is. For the player it will retrieve N/A, as it is not
     * applicable.
     * @return Enum type, N/A for player.
     */
    DinoType getTYPE();

    /**
     * Getter for actionFactories. Gets overridden.
     * @return The actionFactory as a list of behaviours.
     */
    List<Behaviour> getActionFactories();

    /**
     * Returns whether the dinosaur is hungry or not.
     * @return Boolean value if the dinosaur is hungry.
     */
    boolean isHungry();

    /**
     * Deposits money into the players account after selling an item.
     * @param value The integer value of how much should get deposited into the player's balance.
     */
    void deposit(int value);

    /**
     * Withdraws value amounts of money from the actor's balance.
     * @param value The integer value of how much should get withdrawn into the player's balance.
     */
    void withdraw(int value);

    /**
     * Gets how much money the actor/player has in his balance.
     * @return The integer value of how much money is left in the player's account.
     */
    int getBalance();
}
