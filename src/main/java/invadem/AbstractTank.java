package invadem;

import java.util.Map;

/**
 * Tanks controlled by users
 */
public abstract class AbstractTank extends AbstractObject {
    protected Heart[] hearts;
    private int dx;
    private int lastBoomFrame;


    public AbstractTank(int x, int y) {
        super(x, y, 3, 22, 16);
        this.hitPoint = 3;
        this.dx = 1;
        hearts = new Heart[3];
        loadHearts();


    }

    /**
     * load tanks
     * @param nOfplayers Initialize tanks and the number of that depends on the number of players
     * @return the array of tanks
     */
    public static AbstractTank[] loadTanks(int nOfplayers) {
        AbstractTank[] tanks = new AbstractTank[nOfplayers];
        tanks[0] = new Tank1(320, 450);
        if (nOfplayers == 2) {
            tanks[1] = new Tank2(320, 450);
        }
        return tanks;
    }

    /**
     * load hearts of the tank
     */
    public abstract void loadHearts();

    /**
     * show the boom effect when hit
     * @param ifBoom whether or not show the boom effect
     * @param frameCount the current frame
     */
    public void boom(boolean ifBoom, int frameCount) {
        if (ifBoom) {
            currentSpriteIndex = 1;
            lastBoomFrame = frameCount;
            return;
        }
        if (frameCount - lastBoomFrame > 120) {
            currentSpriteIndex = 0;
        }
    }

    /**
     * tank fires
     * @return the projectile
     */
    public Projectile fire() {
        return new Projectile("tank", x + width / 2, y, -1);

    }

    /**
     * Control the actions when keys pressed
     * @param keys keys pressed
     */
    public void readKeys(Map<String, Boolean> keys) {
        if (this instanceof Tank1) {
            // movement of tank1
            if (keys.containsKey("left") && keys.get("left") && x > App.LEFT_BOUNDARY) {
                moveLeft();
            }
            if (keys.containsKey("right") && keys.get("right") && x < App.RIGHT_BOUNDARY - width) {
                moveRight();
            }

        } else {
            if (keys.containsKey("a") && keys.get("a") && x > App.LEFT_BOUNDARY) {
                moveLeft();
            }
            if (keys.containsKey("d") && keys.get("d") && getX() < App.RIGHT_BOUNDARY - width) {
                moveRight();
            }
        }
    }

    /**
     * move left by 1 pixel
     */
    public void moveLeft() {
        if (x > App.LEFT_BOUNDARY) {
            x -= dx;
        }
    }

    /**
     * move right by 1 pixel
     */
    public void moveRight() {
        if (x < App.RIGHT_BOUNDARY - width) {
            x += dx;
        }
    }

    /**
     * @return the array of heats of this tank
     */
    public Heart[] getHearts() {
        return hearts;
    }
}
