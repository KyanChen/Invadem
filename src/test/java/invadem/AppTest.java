package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AppTest extends App {
    @Before
    public void before() {
        PApplet.runSketch(new String[]{"Test"}, this);
//        surface.setVisible(false);
        delay(500);
    }

    @Test
    public void testDoublePlayers() {
        testPlayer(2);
        stop();
    }

    @Test
    public void testSinglePlayers() {
        testPlayer(1);
        stop();
    }

    public void testPlayer(int nOfPlayers) {
        // tanks move and hit
        testMouseClicked(nOfPlayers);
        testKeyPressed(nOfPlayers);
        // all invaders are killed then next level
        testKillAllInvaders();
        // test scores
        int score = currentScore += 20000;
        // invaders reach the bottom boundary and game over
        testInvadersReachBarriers();
        // check scores
        assertEquals(0, currentScore);
        assertEquals(score, highestScore);
        // tanks are hit and destroyed and game over
        testHitTank(nOfPlayers);
        testPowerProjectile();
        delay(1000);
    }

    private void testKillAllInvaders() {
        invaders = new ArrayList<>();
        delay(2100);

    }

    private void testInvadersReachBarriers() {
        for (int i = 0; i < 280; i++) {
            for (Invader invader : invaders) {
                invader.moveDown();
            }
        }
        delay(2100);
    }

    private void testPowerProjectile() {
        projectiles.add(new PowerProjectile("invader", 321, 440, 1));
        delay(2100);
    }

    private void testHitTank(int n) {
        for (int i = 0; i < n; i++) {
            tanks[i].isHit(1);
        }
        delay(100);
        tanks[0].isHit(5);
        delay(2100);
    }

    private void testMouseClicked(int n) {
        if (n == 1) {
            mouseX = 220;
            mouseY = 250;
            mouseClicked();
            delay(500);
        } else {
            mouseX = 350;
            mouseY = 250;
            mouseClicked();
            delay(500);
        }
    }

    private void testKeyPressed(int n) {
        for (int i = 0; i < 100; i++) {
            testAct((int) random(n * 3));
        }
    }

    private void testAct(int n) {
        switch (n) {
            case 0:
                key = CODED;
                keyCode = LEFT;
                break;
            case 1:
                key = CODED;
                keyCode = RIGHT;
                break;
            case 2:
                key = 32;
                break;
            case 3:
                key = 65;
                break;
            case 4:
                key = 68;
                break;
            case 5:
                key = 87;
                break;
        }
        keyPressed();
        delay(200);
        keyReleased();
    }
}

