package game;

import java.util.List;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;

/**
 * An extension of the engine's GameMap class to enable the use of modified Locations
 *
 */

public class DinoGameMap extends GameMap {

  /**
   * Constructor.
   * 
   * @param groundFactory
   * @param lines
   */
  public DinoGameMap(GroundFactory groundFactory, List<String> lines) {
    super(groundFactory, lines);
  }

  /**
   * Override the existing makeNewLocation method that exists in the engine to make use of the new
   * behaviour of locations implemented in the DinoLocation.
   */
  @Override
  protected Location makeNewLocation(int x, int y) {
    return new DinoLocation(this, x, y);
  }
}
