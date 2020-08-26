package invadem;

import processing.core.PImage;

/**
 * the armoured invaders have triple the hit points of a regular invader but fires a regular
 * projectile
 */
public class ArmouredInvader extends Invader {
    public ArmouredInvader(int x, int y) {
        super(x, y);
        hitPoint = 3;
        points = 250;
    }

    /**
     * load images of armoured invaders
     */
    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[0] = p.loadImage("src/main/resources/invader1_armoured.png");
        sprites[1] = p.loadImage("src/main/resources/invader2_armoured.png");
        currentSpriteIndex = 0;
    }
}
