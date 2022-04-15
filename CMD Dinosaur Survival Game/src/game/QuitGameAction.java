package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Player quits the game.
 */
public class QuitGameAction extends Action {

    public QuitGameAction() {
    }

    /**
     * Returns a description of the player quitting and quits the game.
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
        return actor + " quits the game.";
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String hotkey() {
        return "0";
    }
}
