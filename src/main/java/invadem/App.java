package invadem;

import processing.core.PApplet;

public class App extends PApplet {
    private final static int LEFT_BOUNDARY = 180;
    private final static int RIGHT_BOUNDARY = 460;
    private boolean win, lose;
    private boolean isDataLoaded;
    private int players;
    private Tank[] tanks;
    private Barrier[] barriers;
    private Invader[][] invaders;
    private boolean left, right, space, W, A, D;
    private int frameOfLastPress, nOfSteps, frameOfWin;


    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

    /**
     *  Initialize players, tanks, barriers, invaders and projectiles
     */
    public void loadData() {
        // init all objects on the screen
        AbstractObject.setPApplet(this);
        // init the tank
        if (players == 1) {
            tanks = new Tank[1];
        } else {
            tanks = new Tank[2];
            tanks[1] = new Tank(2, width / 2 - 50, height - 30);
        }
        tanks[0] = new Tank(1, width / 2, height - 30);


        // init barriers
        barriers = new Barrier[3];
        loadBarriers();
        // load invaders
        invaders = new Invader[4][10];
        loadInvaders();

        isDataLoaded = true;
    }

    /**
     * set the size of the window
     */
    @Override
    public void settings() {
        size(640, 480);
    }


    /**
     * Initialize the window
     */
    @Override
    public void setup() {
        // init windows
        background(0);
        surface.setTitle("Invadem");
        frameRate(60);
        frameOfLastPress = 0;
        nOfSteps = 0;
        isDataLoaded = false;
        players = 0;


    }

    /**
     * show objects on the window
     */
    @Override
    public void draw() {
        background(0);

        if (players == 0) {
            choosePlayers();
            return;
        }
        // loadData here instead of inside setup() to optimize startup speed
        if (!isDataLoaded) {
            loadData();
            return;
        }
        // display tank
        if (win) {
            won();

        } else if (lose) {
            image(loadImage("../resources/gameover.png"), LEFT_BOUNDARY + 100, height / 2);
        } else {

            if (displayTanks()) {
                return;
            }
            // display barriers
            for (Barrier b : barriers) {
                b.display();
            }
            // display projectile
            // display invaders
            displayInvaders();
            // the user inputs to control the tank
            readKeys();


        }


    }

    /**
     * detect the number of players the user choose
     */
    @Override
    public void mouseClicked() {
        if (mouseX > 210 && mouseX < 310 && mouseY > 240 && mouseY < 280) {
            players = 1;
        }

        if (mouseX > 340 && mouseX < 440 && mouseY > 240 && mouseY < 280) {
            players = 2;
        }

    }


    /**
     * control tanks
     */
    @Override
    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == LEFT) {
                left = true;
            }
            if (keyCode == RIGHT) {
                right = true;
            }

        }

        // if press space key
        if (key == 32) {
            space = true;
            System.out.println("space pressed");
        }

        // if press A
        if (key == 65 || key == 97) {
            A = true;
        }

        // if press D
        if (key == 68 || key == 100) {
            D = true;
        }

        // if press W
        if (key == 87 || key == 119) {
            W = true;
            System.out.println("W pressed");

        }

    }

    /**
     * control tanks
     */
    @Override
    public void keyReleased() {
        if (key == CODED) {
            if (keyCode == LEFT) {
                left = false;
            }
            if (keyCode == RIGHT) {
                right = false;
            }

        }

        // if press space key
        if (key == 32) {
            space = false;
            System.out.println("space released");
        }
        if (key == 65 || key == 97) {
            A = false;
        }
        if (key == 68 || key == 100) {
            D = false;
        }
        if (key == 87 || key == 119) {
            W = false;
            System.out.println("W released");
        }
    }

    /**
     * check whether the projectile collide barrier, tank or invader
     * @param object check whether the object collide other objects
     */
    private void checkCollision(AbstractObject object) {
        if (!object.isAlive()) {
            return;
        }

        // if shooter is true, the object is of tank. Otherwise, it's of invader
        ObjectEnum name = object.getName();

        // the INVADER_PROJECTILE can hit the tank
        if (name == ObjectEnum.INVADER_Projectile) {
            for (int i = 0; i < players; i++) {
                Tank tank = tanks[i];
                if (object.collides(tank)) {
                    tank.isHit();
                    tank.boom(true);
                    tank.getHearts()[tank.getBlood()].isHit();
                    object.isHit();
                }
            }

        }
        // the TANK_PROJECTILE can hit the invader
        if (name == ObjectEnum.TANK_Projectile) {
            for (Invader[] invaderCol : invaders) {
                for (Invader invader : invaderCol) {
                    if (invader.isAlive() && object.collides(invader)) {
                        invader.isHit();
                        object.isHit();
                        ifWon();
                    }
                }
            }
        }
        // the TANK_PROJECTILE, INVADER_PROJECTILE and INVADER can hit barrier
        for (Barrier barrier : barriers) {
            for (Block block : barrier.getBlocks()) {
                if (block.isAlive() && object.collides(block)) {
                    if (name == ObjectEnum.INVADER) {
                        lose = true;
                        return;
                    }
                    block.isHit();
                    object.isHit();
                }
            }
        }

    }


    /**
     * load the Choose Player objects
     */
    private void choosePlayers() {
        fill(0, 153, 0);
        rect(210, 240, 100, 40, 7);
        rect(340, 240, 100, 40, 7);
        fill(255, 255, 255);
        textSize(20);
        text("Select Number of Players", 200, 220);
        text("1 Player", 220, 265);
        text("2 Players", 350, 265);


    }


    /**
     * display and move invaders and its projectiles, and then make them fire
     */
    private void displayInvaders() {
        for (Invader[] invaderCol : invaders) {
            for (Invader invader : invaderCol) {
                if (invader.isAlive()) {
                    invader.display();
                    for (Projectile projectile : invader.getProjectiles()) {
                        checkCollision(projectile);
                        if (projectile.isAlive()) {
                            projectile.display();
                            projectile.move();
                            // detect collision
                        }
                    }


                }
            }
        }
        // invaders move
        moveInvaders();
        // invader shoot randomly
        invaderShoot();
    }

    /**
     * @return // if any tank is not alive, return true
     */
    private boolean displayTanks() {

        for (int i = 0; i < players; i++) {
            Tank tank = tanks[i];
            if (!tank.isAlive()) {
                lose = true;
                return true;
            } else {
                tank.display();
                tank.boom(false);

                // display their hears
                stroke(255, 0, 0);
                textSize(13);
                text("Player" + (i + 1), 2, 26 * (i + 1));
                for (Heart heart : tank.getHearts()) {
                    heart.display();
                }
                for (Projectile projectile : tank.getProjectiles()) {
                    checkCollision(projectile);
                    if (projectile.isAlive()) {
                        projectile.display();
                        projectile.move();
                        // detect collision
                    }
                }
            }
        }
        return false;
    }

    /**
     * detect if the user wins
     */
    private void ifWon() {
        for (Invader[] invaderCol : invaders) {
            for (Invader invader : invaderCol) {
                if (invader.isAlive()) {
                    win = false;
                    return;
                }
            }
        }
        frameOfWin = frameCount;
        win = true;

    }


    /**
     * make invaders fire
     */
    private void invaderShoot() {
        if (frameCount == 1 || frameCount % 301 == 0) {
            Invader invader;

            // if the invader is alive and it's in the range of tank movement
            do {
                invader = invaders[(int) random(4)][(int) random(10)];
            } while (!invader.isAlive() && invader.getX() > LEFT_BOUNDARY && invader.getX() < RIGHT_BOUNDARY - invader.getWidth());
            invader.getProjectiles().add(invader.shoot());


        }
    }


    /**
     * create objects of Barriers
     */
    private void loadBarriers() {
        barriers[0] = new Barrier(LEFT_BOUNDARY + 20, 416);
        barriers[1] = new Barrier(width / 2 - Barrier.getWidth() / 2, 416);
        barriers[2] = new Barrier(RIGHT_BOUNDARY - 20 - Barrier.getWidth(), 416);
    }


    /**
     * create objects of invaders
     */
    private void loadInvaders() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                invaders[i][j] = new Invader(LEFT_BOUNDARY - 16 + 30 * j, 30 * (i + 1));
            }
        }
    }

    /**
     * control the movement of invaders
     */
    private void moveInvaders() {
        boolean every2Frame = frameCount % 2 == 0;
        if (every2Frame) {
            nOfSteps++;
            for (Invader[] invaderCol : invaders) {
                for (Invader invader : invaderCol) {
                    if (invader.isAlive()) {
                        if (nOfSteps < 30) {
                            invader.moveHoriz();
                        } else if (nOfSteps < 38) {
                            invader.moveDown();
                            if (invader.getY() + invader.getHeight() > 406) {
                                lose = true;
                            }
                        } else {
                            Invader.reverseDir();
                            nOfSteps = 0;
                        }
                    }
                }
            }

        }

    }

    /**
     * control the actions when keys pressed
     */
    private void readKeys() {
        // movement of tank1
        if (left && tanks[0].getX() > LEFT_BOUNDARY) {
            tanks[0].moveLeft();
        }
        if (right && tanks[0].getX() < RIGHT_BOUNDARY - tanks[0].getWidth()) {
            tanks[0].moveRight();
        }

        // shoot 1 projectile every 10 frames at most
        if (space) {
            if (frameCount - frameOfLastPress > 10) {
                tanks[0].getProjectiles().add(tanks[0].shoot());
                frameOfLastPress = frameCount;
                System.out.println("1 shoot");
            }
        }

        if (players == 1) {
            return;
        }
        // movement of tanks2

        if (A && tanks[1].getX() > LEFT_BOUNDARY) {
            tanks[1].moveLeft();
        }
        if (D && tanks[1].getX() < RIGHT_BOUNDARY - tanks[1].getWidth()) {
            tanks[1].moveRight();
        }

        // shoot 1 projectile every 10 frames at most
        if (W) {
            if (frameCount - frameOfLastPress > 10) {
                tanks[1].getProjectiles().add(tanks[1].shoot());
                frameOfLastPress = frameCount;
                System.out.println("2 shoot");
            }
        }


    }


    /**
     * display Next Level when wining
     */
    private void won() {
        int time = (frameCount - frameOfWin) / 60;
        if (time < 2) {
            image(loadImage("../resources/nextlevel.png"), LEFT_BOUNDARY + 100, height / 2);
        } else {
            win = false;
            loadData();
        }
    }

}
