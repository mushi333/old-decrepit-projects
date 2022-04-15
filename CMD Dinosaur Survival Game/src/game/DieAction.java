package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Action performed when a dinosaur dies from starvation.
 */
public class DieAction extends Action {

  /**
   * Random number generator
   */
  protected Random rand = new Random();

  /**
   * Constructor.
   * 
   */
  public DieAction() {

  }

  /**
   * Remove the dying actor from the map.
   * 
   *
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return a description of what happened that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    Location location = map.locationOf(actor);

    map.removeActor(actor);

    if (actor instanceof BabyVelociraptor || actor instanceof Velociraptor) {

      map.at(location.x(), location.y()).addItem(new VelociraptorCorpse());

    } else if (actor instanceof BabyProtoceratops || actor instanceof Protoceratops) {

      map.at(location.x(), location.y()).addItem(new ProtoceratopsCorpse());

    } else if (actor instanceof BabyPteranodon || actor instanceof Pteranodon) {

      map.at(location.x(), location.y()).addItem(new PteranodonCorpse());

    } else if (actor instanceof BabyPlesiosaurs || actor instanceof Plesiosaurs) {

      map.at(location.x(), location.y()).addItem(new PlesiosaursCorpse());

    } else if (actor instanceof BabyTRex || actor instanceof TRex) {

      map.at(location.x(), location.y()).addItem(new PteranodonCorpse());

    } else {

      throw new IllegalArgumentException("This actor does not have an associated corpse item.");

    }


    String result = actor + " died from starvation";

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " can die from starvation";
  }
}
