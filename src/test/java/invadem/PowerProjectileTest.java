package invadem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PowerProjectileTest {
    Projectile projectile;

    @Before
    public void testFire() {
        Invader power = new PowerInvader(200, 100);
        projectile = power.fire();
    }

    @Test
    public void testGetAttack() {
        assertEquals(3, projectile.getAttack());
    }
}
