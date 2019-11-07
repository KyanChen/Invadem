package invadem;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App extends PApplet {
    private final static int LEFT_BOUNDARY = 180;
    private final static int RIGHT_BOUNDARY = 460;
    private boolean isDataLoaded, ifLose, ifWon;
    private int nOfplayers, level;
    private AbstractTank[] tanks;
    private Barrier[] barriers;
    private List<Invader> invaders;
    private List<Projectile> projectiles;
    private boolean left, right, spaceReleased, a, d, wReleased;
    private int nOfSteps, frameOfWin, gameStart, frameOfLose;
    private double gameTime;
    private int highestScore, currentScore;


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
        textFont(createFont("src/main/resources/PressStart2P-Regular.ttf", 12));
        surface.setTitle("Invadem");
        frameRate(60);
        nOfSteps = 0;
        isDataLoaded = false;
        nOfplayers = 0;
        level = 1;
        spaceReleased = true;
        highestScore = 10000;
        currentScore = 0;


    }

    /**
     * show objects on the window
     */
    @Override
    public void draw() {
        background(0);
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
            gameOver();
        } else if (ifWon) {
            displayNextLevel();
        } else {
            // the user inputs to control the tank
            readKeys();
            // display the game time in second
            getGameTime();
            // display levels
            text("LEVEL: " + level, 350, 20);
            // display highest and current score
            displayScore();
            // display tanks
            displayTanks();
            // display barriers
            displayBarriers();
            // display invaders, make them move and fire
            invadersUpdate();
            // display projectiles
            displayProjectiles();
            // check whether the players win or lose
            checkSituation();

        }
    }

    private void displayBarriers() {
        // display barriers
        for (Barrier b : barriers) {
            b.display();
        }
    }

    private void invadersUpdate() {
        // display invaders
        displayInvaders();
        // invaders move
        invadersMove();
        // invader fire randomly
        invadersFire();
    }

    /**
     * Display invaders
     */
    private void displayInvaders() {
        for (Invader invader : invaders) {
            invader.display();
        }
    }

    /**
     * Make invaders fire
     */
    private void invadersFire() {
        int rate = level < 6 ? 6 - level : 1;
        if (gameTime % rate == 0) {
            Invader invader;
            // if the invader is in the range of tank movement
            do {
                int i = (int) random(invaders.size());
                invader = invaders.get(i);
            } while (invader.getX() < LEFT_BOUNDARY || invader.getX() > RIGHT_BOUNDARY - invader.getWidth());

            projectiles.add(invader.fire());
        }
    }

    /**
     * Control the movement of invaders
     */
    private void invadersMove() {
        boolean every2Frame = (frameCount - gameStart) % 2 == 0;
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

    private void displayScore() {
        text("HIGHEST: " + highestScore, 480, 20);
        text("CURRENT: " + currentScore, 10, 20);
    }

    /**
     * Initialize data required for the next level or new game after game over
     */
    public void loadData() {
        // init all objects on the screen
        AbstractObject.setPApplet(this);
        ifWon = false;
        ifLose = false;
        nOfSteps = 0;
        // init the tank
        tanks = AbstractTank.loadTanks(nOfplayers);
        // init barriers
        barriers = Barrier.loadBarriers(LEFT_BOUNDARY, RIGHT_BOUNDARY);
        // init invaders
        invaders = Invader.loadInvaders(LEFT_BOUNDARY);
        // init projectiles
        projectiles = new ArrayList<>();
        // update states
        gameStart = frameCount;
        isDataLoaded = true;

    }

    /**
     * check whether the players win or lose
     */
    public void checkSituation() {
        if (invaders.size() == 0) {
            // win if the all invaders are killed
            ifWon = true;
            frameOfWin = frameCount;
        } else {
            // lose if the lowest invader reaches the bottom boundary
            Invader lowestInvader = invaders.get(invaders.size() - 1);
            if (lowestInvader.getY() + lowestInvader.getHeight() > 406) {
                ifLose = true;
                frameOfLose = frameCount;
            }
        }

        // lose if one of the tanks is destroyed
        for (int i = 0; i < nOfplayers; i++) {
            AbstractTank tank = tanks[i];
            if (!tank.isAlive()) {
                ifLose = true;
                frameOfLose = frameCount;
            }
        }
    }

    /**
     * Display projectiles and make them move
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
     * Check whether the projectile collide barrier, tank or invaders
     *
     * @param projectile the projectile to be checked
     */
    private boolean checkCollision(Projectile projectile) {
        String owner = projectile.getOwner();

        // the INVADER_PROJECTILE can hit the tank
        if ("invader".equals(owner)) {
            for (int i = 0; i < nOfplayers; i++) {
                AbstractTank tank = tanks[i];
                if (projectile.collides(tank)) {
                    if (projectile instanceof PowerProjectile) {
                        tank.isHit();
                        tank.isHit();
                        tank.isHit();
                    } else {
                        tank.isHit();
                    }
                    tank.boom(true, frameCount);
                    tank.getHearts()[tank.getBlood()].isHit();
                    return true;
                }
            }


        }
        // the TANK_PROJECTILE can hit the invader
        if ("tank".equals(owner)) {
            Iterator<Invader> iterator = invaders.iterator();
            while (iterator.hasNext()) {
                Invader invader = iterator.next();
                if (projectile.collides(invader)) {
                    if (invader instanceof PowerInvader || invader instanceof ArmouredInvader) {
                        currentScore += 250;
                    } else {
                        currentScore += 100;
                    }
                    invader.isHit();
                    if (invader.getBlood() == 0) {
                        iterator.remove();
                    }
                    return true;

                }
            }
        }

        // the TANK_PROJECTILE, INVADER_PROJECTILE and INVADER can hit barrier
        for (Barrier barrier : barriers) {
            for (AbstractBlock block : barrier.getBlocks()) {
                if (block.isAlive() && projectile.collides(block)) {
                    block.isHit();
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * calculate and display the game time of current round
     */
    private void getGameTime() {
        gameTime = Math.round(((frameCount - gameStart) / 60.0) * 100) / 100.0;
        text(String.format("GAME TIME: %.2f", gameTime), 150, 20);
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
        if (key == 32 && spaceReleased) {
            spaceReleased = false;
            projectiles.add(tanks[0].fire());
        }


        if (nOfplayers == 2) {
            // if press a
            if (key == 65 || key == 97) {
                a = true;
            }

            // if press d
            if (key == 68 || key == 100) {
                d = true;
            }

            // wReleased pressed
            if ((key == 87 || key == 119) && wReleased) {
                wReleased = false;
                projectiles.add(tanks[1].fire());
            }

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

        if (key == 32) {
            spaceReleased = true;
        }


        if (nOfplayers == 2) {
            // a pressed
            if (key == 65 || key == 97) {
                a = false;
            }

            // d pressed
            if (key == 68 || key == 100) {
                d = false;
            }
        }

        // wReleased pressed
        if (key == 87 || key == 119) {
            wReleased = true;
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
        textSize(14);
        text("Select Number of Players", 200, 220);
        textSize(10);
        text("1 Player", 220, 265);
        text("2 Players", 350, 265);


    }

    private void displayTanks() {
        for (int i = 0; i < nOfplayers; i++) {
            AbstractTank tank = tanks[i];
            tank.display();
            tank.boom(false, frameCount);
            stroke(255, 0, 0);

            // display its blood
            text("Player " + (i + 1), 10, 45 * (i + 1));
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


        if (nOfplayers == 1) {
            return;
        }
        // movement of tanks2

        if (a && tanks[1].getX() > LEFT_BOUNDARY) {
            tanks[1].moveLeft();
        }
        if (d && tanks[1].getX() < RIGHT_BOUNDARY - tanks[1].getWidth()) {
            tanks[1].moveRight();
        }
    }


    /**
     * Display Next Level when wining
     */
    private void displayNextLevel() {
        if (frameCount - frameOfWin < 120) {
            image(loadImage("src/main/resources/nextlevel.png"), LEFT_BOUNDARY + 100, height / 2);
        } else {
            isDataLoaded = false;
            level++;
        }
    }

    /**
     * Display GameOver when losing
     */
    private void gameOver() {
        if (frameCount - frameOfLose < 120) {
            image(loadImage("src/main/resources/gameover.png"), LEFT_BOUNDARY + 100, height / 2);
        } else {
            isDataLoaded = false;
            if (currentScore > highestScore) {
                highestScore = currentScore;
            }
            currentScore = 0;
            level = 1;
        }
    }

}
