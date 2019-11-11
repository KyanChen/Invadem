package invadem;

import processing.core.PImage;


/**
 * the hit point of the tank
 */
public class Heart extends AbstractObject {

    public Heart(int x, int y) {
        super(x, y, 1, 24, 24);

    }

    /**
     * load images of the heart
     */
    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[1] = p.loadImage("src/main/resources/heart1.png");
        sprites[0] = p.loadImage("src/main/resources/heart2.png");
        currentSpriteIndex = 1;

    }

    /**
     * update the index of the current sprite when blood changes
     */
    public void updateSprite() {
        currentSpriteIndex = blood;
    }
}
