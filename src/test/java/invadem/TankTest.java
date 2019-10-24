package invadem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TankTest {
    private Tank1 tank1;
    private Tank2 tank2;
    @Before
    public void before() {
        tank1 = new Tank1(100, 100);
        tank2 = new Tank2(100, 100);
    }
    @Test
    public void testTankConstruction() {
        assertNotNull(tank1);
        assertNotNull(tank2);

    }

    @Test
    public void testTankIsAlive() {
        assertTrue(tank1.isAlive());
        assertTrue(tank2.isAlive());

    }

    @Test
    public void testTankFire() {
        assertNotNull(tank1.fire());
        assertNotNull(tank2.fire());

    }

    @Test
    public void testMoveLeft() {
        tank1.moveLeft();
        assertEquals(tank1.getX(), 99);
        tank2.moveLeft();
        assertEquals(tank2.getX(), 99);

    }

    @Test
    public void testMoveRight() {
        tank1.moveRight();
        assertEquals(tank1.getX(), 101);
        tank2.moveRight();
        assertEquals(tank2.getX(), 101);
    }

    @Test
    public void testGetHearts() {
        assertNotNull(tank1.getHearts());
        assertNotNull(tank2.getHearts());

    }

    @Test
    public void testBoom() {
        tank1.boom(true, 100);
        assertEquals(tank1.getCurrentSpriteIndex(), 1);
        tank1.boom(false, 150);
        assertEquals(tank1.getCurrentSpriteIndex(), 0);
        

    }

}
