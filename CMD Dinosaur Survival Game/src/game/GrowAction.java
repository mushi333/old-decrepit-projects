package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Special Action for a Baby Dinosaur to grow into it's adult form
 * 
 */
public class GrowAction extends Action {

  /**
   * The Name of the dinosaur that is to grow
   */
  protected String name;
  /**
   * Random number generator
   */
  protected Random rand = new Random();

  /**
   * Constructor.
   * 
   * @param name the name of the dinosaur growing up. This is used to preserve the attribute between
   *        the two different classes.
   */
  public GrowAction(String name) {
    this.name = name;
  }

  /**
   * Replace the BabyDinosaur instance with its respective Adult equivalent
   *
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return a description of what happened that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    Location location = map.locationOf(actor);

    if (!(actor.getClass().getSimpleName().contains("Baby"))) {
      throw new IllegalArgumentException("Only Baby Dinosaur classes can grow up.");
    }

    if (actor instanceof BabyVelociraptor) {

      map.removeActor(actor);
      Velociraptor newVelociraptor = new Velociraptor(this.name);
      newVelociraptor.actionFactories = actor.getActionFactories();
      map.at(location.x(), location.y()).addActor(newVelociraptor);

    } else if (actor instanceof BabyProtoceratops) {

      map.removeActor(actor);
      Protoceratops newProtoceratops = new Protoceratops(this.name);
      newProtoceratops.actionFactories = actor.getActionFactories();
      map.at(location.x(), location.y()).addActor(newProtoceratops);

    } else if (actor instanceof BabyPteranodon) {

      map.removeActor(actor);
      Pteranodon newPteranodon = new Pteranodon(this.name);
      map.at(location.x(), location.y()).addActor(newPteranodon);

    } else if (actor instanceof BabyPlesiosaurs) {

      map.removeActor(actor);
      Plesiosaurs newPlesiosaurs = new Plesiosaurs(this.name);
      map.at(location.x(), location.y()).addActor(newPlesiosaurs);

    } else if (actor instanceof BabyTRex) {

      map.removeActor(actor);
      TRex newTRex = new TRex(this.name, ((BabyTRex) actor).winning);
      map.at(location.x(), location.y()).addActor(newTRex);

    } else {
      throw new IllegalArgumentException("This actor cannot grow up.");
    }

    String result = "Baby " + actor.getClass().getSimpleName() + " " + this.name + " grew up.";

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String menuDescription(Actor actor) {
    return this.name + " can grow up.";
  }
}
