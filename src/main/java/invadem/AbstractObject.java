package invadem;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * the parent class of Heart, Invader, Projectile, Tank and Block
 */
public abstract class AbstractObject {
    protected static PApplet p;
    protected ObjectEnum name;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected PImage[] sprites;
    protected int currentSpriteIndex;
    protected int blood;


    public AbstractObject(ObjectEnum name, int x, int y, int blood, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.blood = blood;
        this.width = width;
        this.height = height;

    }



    /**
     * @return width of the object
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return height of the object
     */
    public int getHeight() {
        return height;
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
     * display the object
     */
    public void display() {
        p.image(sprites[currentSpriteIndex], x, y);
    }

    /**
     * deduct the blood
     */
    public void isHit() {
        blood--;
    }

    /**
     * @param pApplet the PApplet object where to display objects
     */
    public static void setPApplet(PApplet pApplet) {
        p = pApplet;
    }

    /**
     * @return true if bloods are greater than 0
     */
    public boolean isAlive() {
        return blood > 0;
    }

    /**
     * @return the blood of the object
     */
    public int getBlood() {
        return blood;
    }

    /**
     * @return the type of the object
     */
    public ObjectEnum getName() {
        return name;
    }

}
