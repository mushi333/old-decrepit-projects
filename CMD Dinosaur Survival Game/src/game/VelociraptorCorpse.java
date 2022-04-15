package game;

/**
 * The corpse of the Velociraptor. Can be sold for 100.
 */
public class VelociraptorCorpse extends Corpse {

    /**
     * Constructor. Sets the name to "Velociraptor Corpse" and has a selling price of 100.
     */
    public VelociraptorCorpse() {
        super("Velociraptor Corpse");
        this.setPrice(85);
    }
}
