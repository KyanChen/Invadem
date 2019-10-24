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
     * @param ifBoom whether or not show the boom effect
     */
    public void boom(boolean ifBoom,int frameCount) {
        if (ifBoom) {
            currentSpriteIndex = 1;
            lastBoomFrame = frameCount;
            return;
        }
        if (frameCount - lastBoomFrame > 30) {
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
