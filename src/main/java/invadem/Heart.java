package invadem;

import processing.core.PImage;


/**
 * display the blood of tanks
 */
public class Heart extends AbstractObject {

    public Heart(int x, int y) {
        super(x, y, 1, 24, 24);

    }

    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[1] = p.loadImage("src/main/resources/heart1.png");
        sprites[0] = p.loadImage("src/main/resources/heart2.png");
        currentSpriteIndex = 1;
        imageLoaded = true;
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
