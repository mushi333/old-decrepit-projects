package game;


import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A herbivorous dinosaur.
 *
 */
public class Protoceratops extends BabyProtoceratops {
  // Will need to change this to a collection if Protoceratops gets additional Behaviours.
  // private Behaviour behaviour;
  public final DinoType TYPE;

  /**
   * Constructor. A Protoceraptops wiil be represented by a 'P' and by default will start with 30
   * hit points. Hit points are used to serve the purpose of hunger or food points.
   *
   * @param name the name of this dinosaur
   */
  public Protoceratops(String name) {
    super(name);
    this.hitPoints = 30;
    this.maxHitPoints = 50;
    this.displayChar = 'P';
    // Can be eaten by carnivorous dinosaurs
    this.addSkill(FoodSkills.CARNIVORE_FOOD);
    this.addSkill(DinoSkills.CAN_BREED);

    this.TYPE = DinoType.HERBIVORE;

    if (this.actionFactories.size() == 0) {
      this.actionFactories.add(new HerbivoreBehaviour());
      this.actionFactories.add(new WanderBehaviour());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DinoType getTYPE() {
    return TYPE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
    Actions list = super.getAllowableActions(otherActor, direction, map);
    list.add(new FeedAction(this));
    list.add(new TagAction(this));
    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeItemFromInventory(int i) {}
}
