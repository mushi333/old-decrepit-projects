package game;

/**
 * Sets up the abstract class for the eggs in the game.
 */
public abstract class Egg extends PortableDinoItem {
  private int turnsTillHatch;

  /**
   * Constructor. Sets the name of the egg and makes the display char as the capital e.
   * 
   * @param Name
   */
  public Egg(String Name) {
    super(Name, 'E');
  }

  /**
   * Gets the amount of turns needed for that egg to hatch.
   * 
   * @return The integer value of how long left the egg has to hatch.
   */
  public int getTurnsTillHatch() {
    return this.turnsTillHatch;
  }

  protected void setTurnsTillHatch(int turns) {
    this.turnsTillHatch = turns;
  }

  protected void decrementTurns() {
    this.turnsTillHatch--;
  }
}
