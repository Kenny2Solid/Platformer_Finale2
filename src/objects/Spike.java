package objects;

import main.Game;
import utilz.Constants;

public class Spike extends GameObject{

    public Spike(int x, int y, int objType) {
        super(x, y, objType);

        initHitbox(32, 16);
        xDrawOffset = 0;
        yDrawOffset = (int)(Constants.Game.SCALE * 16);
        hitbox.y += yDrawOffset;

    }

}
