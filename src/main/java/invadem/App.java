package invadem;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App extends PApplet {
    private final static int LEFT_BOUNDARY = 180;
    private final static int RIGHT_BOUNDARY = 460;
    private boolean isDataLoaded, ifLose, ifWon;
    private int nOfplayers;
    private Tank[] tanks;
    private Barrier[] barriers;
    private List<Invader> invaders;
    private List<Projectile> projectiles;
    private boolean left, right, space, W, A, D;
    private int frameOfLastPress, nOfSteps, frameOfWin, gameStart;
    private double gameTime;


    public static void main(String[] args) {
        PApplet.main("invadem.App");
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
        nOfplayers = 0;


    }

    /**
     * show objects on the window
     */
    @Override
    public void draw() {
        background(0);
        stroke(255);
        line(LEFT_BOUNDARY, 0, LEFT_BOUNDARY, 480);
        line(RIGHT_BOUNDARY, 0, RIGHT_BOUNDARY, 480);

        // ask the users to select the number of nOfplayers
        if (nOfplayers == 0) {
            choosePlayers();
            return;
        }

        // loadData here instead of inside setup() to optimize startup speed
        if (!isDataLoaded) {
            loadData();
        }


        if (ifLose) {
            image(loadImage("../resources/gameover.png"), LEFT_BOUNDARY + 100, height / 2);
        } else if (ifWon) {
            displayNextLevel();
        } else {
            getGameTime();
            // the user inputs to control the tank
            readKeys();

            displayTanks();
            // display barriers
            for (Barrier b : barriers) {
                b.display();
            }
            // display invaders
            displayInvaders();
            // display projectiles
            displayProjectiles();

            checkSituation();

        }
    }

    /**
     * Initialize nOfplayers, tanks, barriers, invaders and projectiles
     */
    public void loadData() {
        // init all objects on the screen
        AbstractObject.setPApplet(this);
        ifWon = false;
        nOfSteps = 0;
        // init the tank
        loadTanks();

        // init barriers
        loadBarriers();

        // init invaders
        loadInvaders();

        // init projectiles
        projectiles = new ArrayList<>();

        // update states
        gameStart = frameCount;
        isDataLoaded = true;


    }

    /**
     * Initialize tanks the the number of that depends on the number of nOfplayers
     */
    private void loadTanks() {
        if (nOfplayers == 1) {
            tanks = new Tank[1];
        } else {
            tanks = new Tank[2];
            tanks[1] = new Tank(2, width / 2 - 50, height - 30);
        }
        tanks[0] = new Tank(1, width / 2, height - 30);
    }

    /**
     * Initialize objects of Barriers
     */
    private void loadBarriers() {
        barriers = new Barrier[3];
        barriers[0] = new Barrier(LEFT_BOUNDARY + 20, 416);
        barriers[1] = new Barrier(width / 2 - Barrier.getWidth() / 2, 416);
        barriers[2] = new Barrier(RIGHT_BOUNDARY - 20 - Barrier.getWidth(), 416);
    }

    /**
     * initialize objects of invaders
     */
    private void loadInvaders() {
        invaders = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                invaders.add(new Invader(LEFT_BOUNDARY - 16 + 30 * j, 30 * (i + 1)));
            }
        }
        Invader.setDx(1);



    }

    public void checkSituation() {
        if (invaders.size() == 0) {
            ifWon = true;
            frameOfWin = frameCount;
        }else{
            Invader lowestInvader = invaders.get(invaders.size() - 1);
            if (lowestInvader.getY() + lowestInvader.getHeight() > 406) {
                ifLose = true;
            }
        }

        for (int i = 0; i < nOfplayers; i++) {
            if (!tanks[i].isAlive()) {
                ifLose = true;
            }
        }


    }

    /**
     * Display projectiles
     */
    private void displayProjectiles() {
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.display();
            projectile.move();
            if (checkCollision(projectile)) {
                iterator.remove();
            }
        }
    }

    /**
     * Check whether the projectile collide barrier, tank or invaderFs
     *
     * @param projectile check whether the projectile collide other objects
     */
    private boolean checkCollision(Projectile projectile) {
        ObjectEnum projectileType = projectile.getName();

        // the INVADER_PROJECTILE can hit the tank
        if (projectileType == ObjectEnum.INVADER_Projectile) {
            for (int i = 0; i < nOfplayers; i++) {
                Tank tank = tanks[i];
                if (projectile.collides(tank)) {
                    tank.isHit();
                    tank.boom(true);
                    tank.getHearts()[tank.getBlood()].isHit();
                    return true;
                }
            }

        }
        // the TANK_PROJECTILE can hit the invader
        if (projectileType == ObjectEnum.TANK_Projectile) {
            Iterator<Invader> iterator = invaders.iterator();
            while (iterator.hasNext()) {
                Invader invader = iterator.next();
                if (projectile.collides(invader)) {
                    iterator.remove();
                    return true;

                }
            }
        }

        // the TANK_PROJECTILE, INVADER_PROJECTILE and INVADER can hit barrier
        for (Barrier barrier : barriers) {
            for (Block block : barrier.getBlocks()) {
                if (block.isAlive() && projectile.collides(block)) {
                    block.isHit();
                    return true;
                }
            }
        }
        return false;

    }

    private void getGameTime() {
        gameTime = Math.round(((frameCount - gameStart) / 60.0) * 100) / 100.0;
        textSize(16);
        text(String.format("Game Time: %.2f", gameTime), width / 2 - 50, 20);
    }

    /**
     * Detect the number of nOfplayers the user choose
     */
    @Override
    public void mouseClicked() {
        if (mouseX > 210 && mouseX < 310 && mouseY > 240 && mouseY < 280) {
            nOfplayers = 1;
        }

        if (mouseX > 340 && mouseX < 440 && mouseY > 240 && mouseY < 280) {
            nOfplayers = 2;
        }

    }

    /**
     * Control tanks
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
        }

    }

    /**
     * Control tanks
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
        }
        if (key == 65 || key == 97) {
            A = false;
        }
        if (key == 68 || key == 100) {
            D = false;
        }
        if (key == 87 || key == 119) {
            W = false;
        }
    }

    /**
     * Display the Choose Player windows
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
     * Display and move invaders and its projectiles, and then make them fire
     */
    private void displayInvaders() {
        for (Invader invader : invaders) {
            invader.display();
        }
        // invaders move
        invadersMove();
        // invader fire randomly
        invadersFire();
    }

    /**
     * Make invaders fire
     */
    private void invadersFire() {
        if (gameTime % 5 == 0) {
            Invader invader;
            // if the invader is in the range of tank movement
            do {
                int i = (int) random(invaders.size());
                invader = invaders.get(i);
            } while (invader.getX() < LEFT_BOUNDARY && invader.getX() > RIGHT_BOUNDARY - invader.getWidth());

            projectiles.add(invader.fire());


        }
    }

    /**
     * Control the movement of invaders
     */
    private void invadersMove() {
        boolean every2Frame = (frameCount-gameStart) % 2 == 0;
        if (every2Frame) {
            for (Invader invader : invaders) {
                if (nOfSteps < 30) {
                    invader.moveHoriz();
                } else if (nOfSteps < 38) {
                    invader.moveDown();

                } else {
                    Invader.reverseDir();
                    nOfSteps = 0;
                }
            }
            nOfSteps++;

        }

    }

    private void displayTanks() {

        for (int i = 0; i < nOfplayers; i++) {
            Tank tank = tanks[i];

            tank.display();
            tank.boom(false);

            // display their hearts
            stroke(255, 0, 0);
            textSize(13);
            text("Player" + (i + 1), 2, 26 * (i + 1));
            for (Heart heart : tank.getHearts()) {
                heart.display();

            }

        }
    }

    /**
     * Control the actions when keys pressed
     */
    private void readKeys() {
        // movement of tank1
        if (left && tanks[0].getX() > LEFT_BOUNDARY) {
            tanks[0].moveLeft();
        }
        if (right && tanks[0].getX() < RIGHT_BOUNDARY - tanks[0].getWidth()) {
            tanks[0].moveRight();
        }

        // fire 1 projectile every 10 frames at most
        if (space) {
            if (frameCount - frameOfLastPress > 10) {
                projectiles.add(tanks[0].fire());
                frameOfLastPress = frameCount;
            }
        }

        if (nOfplayers == 1) {
            return;
        }
        // movement of tanks2

        if (A && tanks[1].getX() > LEFT_BOUNDARY) {
            tanks[1].moveLeft();
        }
        if (D && tanks[1].getX() < RIGHT_BOUNDARY - tanks[1].getWidth()) {
            tanks[1].moveRight();
        }

        // fire 1 projectile every 10 frames at most
        if (W) {
            if (frameCount - frameOfLastPress > 10) {
                projectiles.add(tanks[1].fire());
                frameOfLastPress = frameCount;
            }
        }


    }


    /**
     * Display Next Level when wining
     */
    private void displayNextLevel() {
        if (frameCount - frameOfWin < 90) {
            image(loadImage("../resources/nextlevel.png"), LEFT_BOUNDARY + 100, height / 2);
        } else {
            isDataLoaded = false;
        }


    }

}
