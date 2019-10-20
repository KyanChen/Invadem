package invadem;

import processing.core.PImage;

public class Tank extends AbstractObject {
    private int dx;
    private int lastBoomFrame;
    private int player;
    private PImage[][] tanks;
    private Heart[] hearts;


    public Tank(int player,int x, int y) {
        super(ObjectEnum.TANK, x, y, 3, 22, 16);
        this.blood = 3;
        this.dx = 1;
        this.player = player;
        tanks = new PImage[2][2];
        loadTanks();
        sprites = tanks[player - 1];

        hearts = new Heart[3];
        loadHearts();


        currentSpriteIndex = 0;

    }

    private void loadHearts() {
        int y = player == 1 ? 10 : 40;
        hearts[0] = new Heart(ObjectEnum.HEART, 50, y);
        hearts[1] = new Heart(ObjectEnum.HEART, 90, y);
        hearts[2] = new Heart(ObjectEnum.HEART, 130, y);

    }


    private void loadTanks() {
        tanks[0][0] = p.loadImage("../resources/tank1.png");
        tanks[0][1] = p.loadImage("../resources/tank1_boom.png");
        tanks[1][0] = p.loadImage("../resources/tank2.png");
        tanks[1][1] = p.loadImage("../resources/tank2_boom.png");
    }

    public Projectile shoot() {
        return new Projectile(ObjectEnum.TANK_Projectile, x + width / 2, y, -1);

    }

    public void moveLeft() {

        x -= dx;

    }

    public void moveRight() {

        x += dx;

    }

    public void boom(boolean ifBoom) {
        if (ifBoom) {
            currentSpriteIndex = 1;
            lastBoomFrame = p.frameCount;
            return;
        }
        if (p.frameCount - lastBoomFrame > 30) {
            currentSpriteIndex = 0;
        }

    }

    public Heart[] getHearts() {
        return hearts;
    }
}
