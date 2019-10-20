package invadem;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

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
    private List<Projectile> projectiles;


    public AbstractObject(ObjectEnum name, int x, int y, int blood, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.blood = blood;
        this.width = width;
        this.height = height;
        projectiles = new ArrayList<>();

    }

    public void display() {
        p.image(sprites[currentSpriteIndex], x, y);
    }

    public boolean collides(AbstractObject object) {
        int ObjWidth = object.getWidth();
        int ObjHeight = object.getHeight();
        int ObjX = object.getX();
        int ObjY = object.getY();
        return (x < ObjX + ObjWidth)
                && (x + width > ObjX)
                && (y < ObjY + ObjHeight)
                && (y + height > ObjY);

    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void isHit() {
        blood--;
    }

    public static void setPApplet(PApplet pApplet) {
        p = pApplet;
    }

    public boolean isAlive() {
        return blood > 0;
    }

    public int getBlood() {
        return blood;
    }

    public ObjectEnum getName() {
        return name;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
