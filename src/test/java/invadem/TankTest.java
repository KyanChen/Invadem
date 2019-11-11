package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TankTest {
    AbstractTank[] tanks;

    @Test
    public void testBoom() {
        tanks[0].boom(true, 100);
        assertEquals(1, tanks[0].getCurrentSpriteIndex());
        tanks[0].boom(false, 150);
        assertEquals(1, tanks[0].getCurrentSpriteIndex());
        tanks[0].boom(false, 321);
        assertEquals(0, tanks[0].getCurrentSpriteIndex());
    }

    @Test
    public void testGetHearts() {
        assertNotNull(tanks[0].getHearts());
        assertNotNull(tanks[1].getHearts());

    }

    @Test
    public void testIsHit() {
        assertEquals(3, tanks[0].getBlood());
        tanks[0].isHit(1);
        assertEquals(2, tanks[0].getBlood());
        tanks[0].isHit(1);
        assertEquals(1, tanks[0].getBlood());
        tanks[0].isHit(1);
        assertEquals(0, tanks[0].getBlood());
        assertFalse(tanks[0].isAlive());
    }

    @Before
    public void testLoadTanks() {
        tanks = AbstractTank.loadTanks(1);
        assertEquals(1, tanks.length);
        assertNotNull(tanks[0]);

        tanks = AbstractTank.loadTanks(2);
        assertEquals(2, tanks.length);
        assertNotNull(tanks[0]);
        assertNotNull(tanks[1]);
    }

    @Test
    public void testMoveLeft() {
        int x = tanks[0].getX();
        tanks[0].moveLeft();
        assertEquals(x - 1, tanks[0].getX());

        // tank can't pass LEFT BOUNDARY
        for (int i = 0; i < 500; i++) {
            tanks[0].moveLeft();
        }
        assertEquals(App.LEFT_BOUNDARY, tanks[0].getX());

    }

    @Test
    public void testMoveRight() {
        int x = tanks[0].getX();
        tanks[0].moveRight();
        assertEquals(x + 1, tanks[0].getX());

        // tank can't pass RIGHT BOUNDARY
        for (int i = 0; i < 500; i++) {
            tanks[0].moveRight();
        }
        assertEquals(App.RIGHT_BOUNDARY - tanks[0].getWidth(), tanks[0].getX());

    }

    @Test
    public void testReadKeys() {
        Map<String, Boolean> keys = new HashMap<>();
        // test Tank1
        // test moving left by 1 step
        keys.put("left", true);
        int x = tanks[0].getX();
        tanks[0].readKeys(keys);
        assertEquals(x - 1, tanks[0].getX());

        // test moving left by 500 steps
        for (int i = 0; i < 500; i++) {
            tanks[0].readKeys(keys);
        }
        assertEquals(App.LEFT_BOUNDARY, tanks[0].getX());
        keys.put("left", false);
        // test moving right by 1 step
        keys.put("right", true);
        x = tanks[0].getX();
        tanks[0].readKeys(keys);
        assertEquals(x + 1, tanks[0].getX());
        // test moving right by 500 steps
        for (int i = 0; i < 500; i++) {
            tanks[0].readKeys(keys);
        }
        assertEquals(App.RIGHT_BOUNDARY - tanks[0].width, tanks[0].getX());
        keys.put("right", false);

        // test Tank2
        // test moving left by 1 step
        keys.put("a", true);
        x = tanks[1].getX();
        tanks[1].readKeys(keys);
        assertEquals(x - 1, tanks[1].getX());

        // test moving left by 500 steps
        for (int i = 0; i < 500; i++) {
            tanks[1].readKeys(keys);
        }
        assertEquals(App.LEFT_BOUNDARY, tanks[1].getX());
        keys.put("a", false);
        // test moving right by 1 step
        keys.put("d", true);
        x = tanks[1].getX();
        tanks[1].readKeys(keys);
        assertEquals(x + 1, tanks[1].getX());
        // test moving right by 500 steps
        for (int i = 0; i < 500; i++) {
            tanks[1].readKeys(keys);
        }
        assertEquals(App.RIGHT_BOUNDARY - tanks[1].width, tanks[1].getX());
        keys.put("d", false);


    }

    @Test
    public void testTankConstruction() {
        assertNotNull(tanks[0]);
        assertNotNull(tanks[1]);
    }

    @Test
    public void testTankFire() {
        assertNotNull(tanks[0].fire());
        assertNotNull(tanks[1].fire());
    }

    @Test
    public void testTankIsAlive() {
        assertTrue(tanks[0].isAlive());
        assertTrue(tanks[1].isAlive());
    }

}
