package game;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Land {

  public Dirt() {
    super('.');
    this.addSkill(GrowStatus.DEAD);
  }

  /**
   * @return zero as Herbivores won't be eating dirt.
   */
  @Override
  public int getHealValue() {
    return 0;
  }
}
