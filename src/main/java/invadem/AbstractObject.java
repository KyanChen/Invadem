package invadem;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * the parent class of Heart, Invader, Projectile, AbstractTank and AbstractBlock
 */
public abstract class AbstractObject {
    protected static PApplet p;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected PImage[] sprites;
    protected int currentSpriteIndex;
    protected int blood;


    public AbstractObject(int x, int y, int blood, int width, int height) {
        this.x = x;
        this.y = y;
        this.blood = blood;
        this.width = width;
        this.height = height;

    }

    /**
     * @param pApplet the PApplet object where to draw objects
     */
    public static void setPApplet(PApplet pApplet) {
        p = pApplet;
    }

    /**
     * display the object
     */
    public void display() {
        if (sprites == null) {
            loadImages();
        }
        p.image(sprites[currentSpriteIndex], x, y);
    }

    /**
     * load images of objects
     */
    public abstract void loadImages();

    /**
     * deduct the blood when hit
     */
    public void isHit(int deduction) {
        if (blood > deduction) {
            blood -= deduction;
        } else {
            blood = 0;
        }
    }

    /**
     * @return the blood of the object
     */
    public int getBlood() {
        return blood;
    }

    /**
     * @return the index of the current sprite
     */
    public int getCurrentSpriteIndex() {
        return currentSpriteIndex;
    }

    /**
     * @return height of the object
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return width of the object
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the x-coordinate of the object
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y-coordinate of the object
     */
    public int getY() {
        return y;
    }

    /**
     * @return true if bloods are greater than 0
     */
    public boolean isAlive() {
        return blood > 0;
    }
}
