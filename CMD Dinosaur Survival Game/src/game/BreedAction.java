package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Special Action for a Dinosaur to breed (lay an egg)
 * 
 * FIXME: String matching definitely isnt the most robust or extensible design choice. Should
 * revisit if possible
 */
public class BreedAction extends Action {

  /**
   * Constructor.
   */
  public BreedAction() {

  }

  /**
   * Add the relevant egg to the location the actor is currently at.
   * 
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return a description of what happened that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    Location location = map.locationOf(actor);

    if (actor instanceof Velociraptor)
        map.at(location.x(), location.y()).addItem(new VelociraptorEgg());
    else if (actor instanceof Protoceratops)
        map.at(location.x(), location.y()).addItem(new ProtoceratopsEgg());
    else if (actor instanceof TRex)
      map.at(location.x(), location.y()).addItem(new TRexEgg(true));

    String result = actor + " layed an egg.";

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " can lay an egg.";
  }
}
