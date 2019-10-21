package invadem;

import org.junit.BeforeClass;
import org.junit.Test;
import processing.core.PApplet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TankTest {

    @BeforeClass
    public static void setUp() {
        PApplet p = new PApplet(){
            public void settings() {
                size(640,480);
            }
        };

        AbstractObject.setPApplet(p);
    }

    @Test
    public void testTankConstruction() {
        Tank tank = new Tank(1, 340, 450);
        assertNotNull(tank);
    }

    @Test
    public void testTankIsNotDead() {
        Tank tank = new Tank(1, 340, 450);
        assertTrue(tank.isAlive());
    }

    @Test
    public void testTankProjectile() {
        Tank tank = new Tank(1, 340, 450);
        assertNotNull(tank.fire());
    }

}
