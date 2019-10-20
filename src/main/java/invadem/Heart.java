package invadem;

import processing.core.PImage;


/**
 * display the blood of tanks
 */
public class Heart extends AbstractObject {

    public Heart(ObjectEnum name, int x, int y) {
        super(name, x, y, 1, 24, 24);
        sprites = new PImage[2];
        sprites[1] = p.loadImage("../resources/heart1.png");
        sprites[0] = p.loadImage("../resources/heart2.png");
        currentSpriteIndex = 1;
    }

    /**
     * deduct bloods and change it sprite
     */
    @Override
    public void isHit() {
        if (blood > 0) {
            blood--;
            currentSpriteIndex = blood;
        }
    }
}
