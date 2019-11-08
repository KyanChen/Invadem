package invadem;

import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * the invaders
 */
public class Invader extends AbstractObject {
    private static int dx = 1;

    public Invader(int x, int y) {
        super(x, y, 1, 16, 16);


    }

    /**
     * initialize objects of invaders
     */
    public static List<Invader> loadInvaders() {
        List<Invader> invaders = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int x = App.LEFT_BOUNDARY - 16 + 30 * j;
                int y = 30 * (i + 1);
                if (i == 0) {
                    invaders.add(new ArmouredInvader(x, y));

                } else if (i == 1) {
                    invaders.add(new PowerInvader(x, y));

                } else {
                    invaders.add(new Invader(x, y));
                }
            }
        }
        setDx(1);
        return invaders;
    }

    public static void setDx(int dx) {
        Invader.dx = dx;
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
        return new Projectile("invader", x, y, 1);

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

}
