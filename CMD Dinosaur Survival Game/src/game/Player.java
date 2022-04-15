package game;

import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
//import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Actor {
    private DinoType TYPE;
    private int bank;


	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 * @param bankBalance Player's starting number of money
	 */
	public Player(String name, char displayChar, int hitPoints, int bankBalance) {
		super(name, displayChar, hitPoints);
		this.TYPE = DinoType.NA;
		this.bank = bankBalance;
        this.addSkill(MovingSkills.CAN_MOVE_THROUGH_DOOR);
        this.addSkill(MovingSkills.CAN_MOVE_THROUGH_LAND);
	}

    /**
     * {@inheritDoc}
     */
    public DinoType getTYPE() {
        return TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeItemFromInventory(int i) {
        this.inventory.remove(i);
    }

    /**
     * {@inheritDoc}
     */
  @Override
  public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
      // When the player dies.
      if (!this.isConscious())
          return new PlayerDiesAction();
    // Handle multi-turn Actions
    if (lastAction.getNextAction() != null)
      return lastAction.getNextAction();
    // Always adds the quitting game action to the player's choice.
    actions.add(new QuitGameAction());
    System.out.println("Player Balance: $" + this.getBalance());
    return menu.showMenu(this, actions, display);
  }

    /**
     * {@inheritDoc}
     */
  @Override
  public List<Behaviour> getActionFactories() {
    return null;

  }

  // Player actor does not get hungry... yet.
    /**
     * {@inheritDoc}
     */
  @Override
  public boolean isHungry() {
    return false;
  }

	/**
	 * {@inheritDoc}
	 */
	public void deposit(int value) {
		this.bank = this.bank + value;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void withdraw(int value) {
        this.bank = this.bank - value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBalance() {
        return this.bank;
    }

}
