package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableDinoItem extends Item {
  protected int itemPrice;

  public PortableDinoItem(String name, char displayChar) {
    super(name, displayChar, true);
  }

  /**
   * {@inheritDoc}
   */
  public void addAction(Action action) {
    this.allowableActions.add(action);
  }

  /**
   * Returns the price of the item.
   *
   * @return The price.
   */
  public int getPrice() {
    return this.itemPrice;
  }

  /**
   * Sets the price of the item.
   * 
   * @param newPrice The integer value of the price of the item.
   */
  protected void setPrice(int newPrice) {
    this.itemPrice = newPrice;
  }

  /**
   * @return the value to heal actor when consumed.
   */
  public int getHealValue() {
    return 0;
  }

  /**
   * {@inheritDoc}
   */
  public DropItemAction getDropAction() {
    if(portable)
      return new SellAction(this);

    return null;
  }

  /**
   * {@inheritDoc}
   */
  public PickUpItemAction getPickUpAction() {
    if(portable)
      return new BuyAction(this);

    return null;
  }
}
