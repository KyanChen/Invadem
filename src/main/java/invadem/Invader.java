package invadem;

import processing.core.PImage;

public class Invader extends AbstractObject {
    private static int dx = 1;


    public Invader(int x, int y) {
        super(ObjectEnum.INVADER, x, y, 1, 16, 16);
        sprites = new PImage[2];
        sprites[0] = p.loadImage("../resources/invader1.png");
        sprites[1] = p.loadImage("../resources/invader2.png");
        currentSpriteIndex = 0;
    }

    public void moveHoriz() {
        currentSpriteIndex = 0;
        x += dx;
    }

    public void moveDown() {
        currentSpriteIndex = 1;
        int dy = 1;
        y += dy;

    }

    public static void reverseDir() {
        dx *= -1;
    }

    public Projectile shoot() {
        return new Projectile(ObjectEnum.INVADER_Projectile, x, y, 1);

    }

}
