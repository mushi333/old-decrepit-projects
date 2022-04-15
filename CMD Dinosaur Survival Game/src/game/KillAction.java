package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Special Action for a Predator to kill another actor
 */
public class KillAction extends Action {

  /**
   * The instance of actor that is being killed
   */
  protected Actor target;
  /**
   * Random number generator
   */
  protected Random rand = new Random();

  /**
   * Constructor.
   * 
   */
  public KillAction(Actor target) {
    this.target = target;
  }

  /**
   * If the attack was successful, replace the actor with a corpse item.
   * 
   * TODO: Replace the actor with a corpse item
   *
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return a description of what happened that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    if (rand.nextBoolean()) {
      Location targetLocation = map.locationOf(target);
      map.removeActor(target);
      map.at(targetLocation.x(), targetLocation.y()).addItem(new ProtoceratopsCorpse());
      return actor + " killed its prey!";
    } else {
      return actor + " attacked its prey, but missed.";
    }
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " attacks prey";
  }
}
