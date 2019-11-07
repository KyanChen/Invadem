package invadem;

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
        AbstractTank[] tanks = new AbstractTank[2];
        if (nOfplayers == 2) {
            tanks[1] = new Tank2(320, 450);
        }
        tanks[0] = new Tank1(320, 450);
        return tanks;
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
     * @return fire and return the projectile
     */
    public Projectile fire() {
        return new Projectile("tank", x + width / 2, y, -1);

    }

    /**
     * move left by 1 pixel
     */
    public void moveLeft() {

        x -= dx;

    }

    /**
     * move right by 1 pixel
     */
    public void moveRight() {

        x += dx;

    }

    /**
     * @return the array of heats of this tank
     */
    public Heart[] getHearts() {
        return hearts;
    }
}
