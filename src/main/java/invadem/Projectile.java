package invadem;

import processing.core.PImage;


/**
 * projectiles fired by invaders and tanks
 */
public class Projectile extends AbstractObject {
    private int dy;
    private String owner;

    public Projectile(String owner, int x, int y, int dy) {
        super(x, y, 1, 1, 3);
        this.dy = dy;
        this.owner = owner;
    }

    /**
     * check if this object collides another one
     *
     * @return whether the object is collided
     */
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

    @Override
    public void loadImages() {
        sprites = new PImage[1];
        sprites[0] = p.loadImage("src/main/resources/projectile.png");
        currentSpriteIndex = 0;
        imageLoaded = true;
    }

    /**
     * move down of up depends on dy
     */
    public void move() {
        this.y += dy;
    }

    public String getOwner() {
        return owner;
    }


}
