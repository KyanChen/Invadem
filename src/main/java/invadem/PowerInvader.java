package invadem;

import processing.core.PImage;

public class PowerInvader extends Invader {

    public PowerInvader(int x, int y) {
        super(x, y);
    }

    /**
     * @return fire a projectile
     */
    @Override
    public Projectile fire() {
        return new PowerProjectile("invader", x, y, 1);

    }

    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[0] = p.loadImage("src/main/resources/invader1_power.png");
        sprites[1] = p.loadImage("src/main/resources/invader2_power.png");
        currentSpriteIndex = 0;

    }
}
