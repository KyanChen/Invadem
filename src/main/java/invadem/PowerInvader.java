package invadem;

import processing.core.PImage;

/**
 * Power invaders have the same amount of health as an invader
 * but will destroy the tank or barrier component in one shot
 */
public class PowerInvader extends Invader {

    public PowerInvader(int x, int y) {
        super(x, y);
        points = 250;
    }

    /**
     * @return the projectile
     */
    @Override
    public Projectile fire() {
        return new PowerProjectile("invader", x, y, 1);

    }

    /**
     * load images of the invader
     */
    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[0] = p.loadImage("src/main/resources/invader1_power.png");
        sprites[1] = p.loadImage("src/main/resources/invader2_power.png");
        currentSpriteIndex = 0;

    }
}
