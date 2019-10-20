package invadem;

import processing.core.PImage;

public class Projectile extends AbstractObject {
    private int dy;

    public Projectile(ObjectEnum name, int x, int y, int dy) {
        super(name, x, y, 1, 1, 3);
        this.dy = dy;
        sprites = new PImage[1];
        sprites[0] = p.loadImage("../resources/projectile.png");
        currentSpriteIndex = 0;
    }

    public void move() {
        this.y += dy;
    }


}
