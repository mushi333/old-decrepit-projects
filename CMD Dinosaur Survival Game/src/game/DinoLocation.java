package game;

import java.util.Random;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An extension of the engine's location class to enable a dynamic spread of vegetation
 *
 */

public class DinoLocation extends Location {

  private boolean read = false;
  private NextTurn action = NextTurn.SAME;
  protected Random rand = new Random();

  /**
   * Constructor
   */
  public DinoLocation(GameMap map, int x, int y) {
    super(map, x, y);
  }

  /**
   * As time passes, there is a chance of trees spreading to neighbouring locations. Other locations
   * have a chance of growing grass.
   */
  @Override
  public void tick() {
    read = !read;
    if (read) {
      int aliveNeighbours = aliveNeighboursCount();
      boolean aliveHere = getGround().hasSkill(GrowStatus.ALIVE);
      boolean growBlock = getGround().hasSkill(GrowStatus.BLOCK);

      // cannot grow here (e.g. Wall or Floor)
      if (growBlock)
        action = NextTurn.SAME;

      // birth
      else if (!aliveHere && aliveNeighbours >= 2)
        action = NextTurn.GROW;

      // Death by isolation
      else if (aliveHere && aliveNeighbours <= 1)
        action = NextTurn.DIE;

      // No trees nearby, try grow grass instead
      else {
        action = NextTurn.GRASS;

        // Grow Reeds if the ground is water
        if (getGround() instanceof Water)
          action = NextTurn.REED;

        // Spawn fish here if the ground is reed
        if (getGround() instanceof  Reed)
          action = NextTurn.FISH;
      }
    } else {

      // 5% chance to grow Tree on Dir or Grass
      if (action == NextTurn.GROW && rand.nextDouble() >= 0.95) {
        if (getGround() instanceof Dirt || getGround() instanceof Grass)
          setGround(new Tree());
      }

      // 1% chance to grow Grass on Dirt
      else if (action == NextTurn.GRASS && rand.nextDouble() >= 0.99) {
        if (getGround() instanceof Dirt)
          setGround(new Grass());
      }

      // Die here
      else if (action == NextTurn.DIE && getGround() instanceof Tree) {
        setGround(new Dirt());
      }

      // Spawning reeds
      else if (action == NextTurn.REED) {
        int amountOfReeds = 0;
        for (int i = 0; i < getExits().size(); i++) {
          // Checks to see if land is next to the location
          if (getExits().get(i).getDestination().getGround() instanceof Land) {
            if (rand.nextDouble() >= 0.9) {
              if (getGround() instanceof Water)
                setGround(new Reed());
            }
          }
          else if (getExits().get(i).getDestination().getGround() instanceof Reed) {
            amountOfReeds++;
            if (amountOfReeds > 6) {
              setGround(new Water());
            }
            if (rand.nextDouble() >= 0.95) {
              if (getGround() instanceof Water)
                setGround(new Reed());
            }
          }
        }
      }

      // Spawn fish here in the reeds
      else if (action == NextTurn.FISH && rand.nextDouble() >= 0.90) {
        if (getGround() instanceof Reed && !this.containsAnActor())
          addActor(new Fish());
      }
    }

    super.tick();
  }


  /**
   * @return the number of neighbouring locations with Ground set with ALIVE
   */
  private int aliveNeighboursCount() {
    return (int) getExits().stream().map(exit -> exit.getDestination().getGround())
        .filter(ground -> ground.hasSkill(GrowStatus.ALIVE)).count();
  }

  /**
   * enum for marking what will happen to a location its next turn
   */
  private enum NextTurn {
    GROW, DIE, SAME, GRASS, REED, FISH
  }
}
