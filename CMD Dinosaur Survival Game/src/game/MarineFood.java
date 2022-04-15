package game;

/**
 * The herbivore food that the Protoceratops will eat.
 */
public class MarineFood extends Food {

    /**
     * Constructor. Sets the name out as "Herbivore Food" and allows the player to have the skill of
     * being able to feed herbivores.
     */
    public MarineFood() {
        super("Marine Food");
        this.addSkill(ItemSkills.CAN_FEED_MARINE);
        this.addSkill(FoodSkills.MARINE_FOOD);
        this.setPrice(50);
    }

}