package invadem;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectileTest {

    @Test
    public void testProjectileConstruction() {
        Projectile proj = new Projectile("tank", 100, 100, 1);
        assertNotNull(proj);
    }

    @Test
    public void testProjectileCollides() {
        Projectile proj = new Projectile("tank", 100, 100, 1);
        Invader inv = new Invader(100, 100);
        Tank1 tank = new Tank1(100, 100);
        assertTrue(proj.collides(inv));
        assertTrue(proj.collides(tank));
    }


}
