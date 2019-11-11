package invadem;

import processing.core.PImage;

/**
 * Projectile fired by the power invader
 */
public class PowerProjectile extends Projectile {
    public PowerProjectile(String owner, int x, int y, int dy) {
        super(owner, x, y, dy);
        attack = 3;
    }


    /**
     * load images of the projectile
     */
    @Override
    public void loadImages() {
        sprites = new PImage[1];
        sprites[0] = p.loadImage("src/main/resources/projectile_lg.png");
        currentSpriteIndex = 0;

    }


}
