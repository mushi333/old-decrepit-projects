package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * For when the player dies in the game.
 */
public class PlayerDiesAction extends QuitGameAction{

    public PlayerDiesAction() {
    }

    /**
     * Returns a description of the player dying and the game being quit.
     * @param actor The player.
     * @param map The game map the player is in.
     * @return Nothing, game finishes.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println(menuDescription(actor));
        java.lang.System.exit(0);
        return "";
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " dies in the game. GAME OVER.";
    }
}
