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
        this.blood = 3;
        this.dx = 1;
        hearts = new Heart[3];
        loadHearts();


    }

    public abstract void loadHearts();

    /**
     * Initialize tanks and the number of that depends on the number of nOfplayers
     *
     * @param nOfplayers the number of players
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
     * Control the actions when keys pressed
     */
    public static void readKeys(AbstractTank[] tanks, Map<String, Boolean> keys) {
        // movement of tank1
        if (keys.get("left") && tanks[0].getX() > App.LEFT_BOUNDARY) {
            tanks[0].moveLeft();
        }
        if (keys.get("right") && tanks[0].getX() < App.RIGHT_BOUNDARY - tanks[0].getWidth()) {
            tanks[0].moveRight();
        }

        if (tanks.length == 2) {
            if (keys.get("a") && tanks[1].getX() > App.LEFT_BOUNDARY) {
                tanks[1].moveLeft();
            }
            if (keys.get("d") && tanks[1].getX() < App.RIGHT_BOUNDARY - tanks[1].getWidth()) {
                tanks[1].moveRight();
            }
        }


    }

    /**
     * @return fire and return the projectile
     */
    public Projectile fire() {
        return new Projectile("tank", x + width / 2, y, -1);

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
     * @param ifBoom whether or not show the boom effect
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
     * @return the array of heats of this tank
     */
    public Heart[] getHearts() {
        return hearts;
    }
}
