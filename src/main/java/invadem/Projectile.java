package invadem;

import processing.core.PImage;


/**
 * projectiles fired by invaders and tanks
 */
public class Projectile extends AbstractObject {
    private int dy;

    public Projectile(ObjectEnum name, int x, int y, int dy) {
        super(name, x, y, 1, 1, 3);
        this.dy = dy;
        sprites = new PImage[1];
        sprites[0] = p.loadImage("../resources/projectile.png");
        currentSpriteIndex = 0;
    }

    /**
     * move down of up depends on dy
     */
    public void move() {
        this.y += dy;
    }

    /**
     * check if this object collides another one
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


}
