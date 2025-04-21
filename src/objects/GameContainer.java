package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;
import utilz.Constants;

public class GameContainer extends GameObject {

    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    private void createHitbox() {
        if (objType == BOX) {
            initHitbox(25, 18);

            xDrawOffset = (int) (7 * Constants.Game.SCALE);
            yDrawOffset = (int) (12 * Constants.Game.SCALE);

        } else {
            initHitbox(23, 25);
            xDrawOffset = (int) (8 * Constants.Game.SCALE);
            yDrawOffset = (int) (5 * Constants.Game.SCALE);
        }

        hitbox.y += yDrawOffset + (int) (Constants.Game.SCALE * 2);
        hitbox.x += xDrawOffset / 2;
    }

    public void update() {
        if (doAnimation)
            updateAnimationTick();
    }
}
