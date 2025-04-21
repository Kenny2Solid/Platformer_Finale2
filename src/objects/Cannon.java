package objects;

import main.Game;
import utilz.Constants;

public class Cannon extends GameObject {

    private int tileY;

    public Cannon(int x, int y, int objType) {
        super(x, y, objType);
        tileY = y / Constants.Game.TILES_SIZE;
        initHitbox(40, 26);
        hitbox.x -= (int) (4 * Constants.Game.SCALE);
        hitbox.y += (int) (6 * Constants.Game.SCALE);
    }

    public void update() {
        if (doAnimation)
            updateAnimationTick();
    }

    public int getTileY() {
        return tileY;
    }

}
