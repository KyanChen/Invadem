package invadem;

import processing.core.PImage;

/**
 * the invaders
 */
public class Invader extends AbstractObject {
    private static int dx = 1;


    public Invader(int x, int y) {
        super(x, y, 1, 16, 16);


    }

    /**
     * change its direction horizontally if move 30 steps
     */
    public static void reverseDir() {
        dx *= -1;
    }

    /**
     * @return fire a projectile
     */
    public Projectile fire() {
        return new Projectile("invader",x, y, 1);

    }

    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[0] = p.loadImage("src/main/resources/invader1.png");
        sprites[1] = p.loadImage("src/main/resources/invader2.png");
        currentSpriteIndex = 0;
        imageLoaded = true;
    }

    /**
     * move 1 step down
     */
    public void moveDown() {
        currentSpriteIndex = 1;
        int dy = 1;
        y += dy;


    }

    /**
     * move 1 step horizontally
     */
    public void moveHoriz() {
        currentSpriteIndex = 0;
        x += dx;
    }

    public static void setDx(int dx) {
        Invader.dx = dx;
    }

}
