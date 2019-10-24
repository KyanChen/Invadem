package invadem;

import org.junit.Test;
import static org.junit.Assert.*;


public class InvaderTest {

   @Test
   public void testInvaderConstruction() {
       // AbstractObject is the super class of Invader
       AbstractObject.setPApplet(new App());
       Invader inv = new Invader(50, 50);
       assertNotNull(inv);

    }

//    @Test
//    public void testInvaderFireProjectile() {
//        INVADER inv = /* Your Constructor Here */
//        assertNotNull(inv.fire());
//    }

//    @Test
//    public void testInvaderIsNotDead() {
//        INVADER inv = /* Your Constructor Here */
//        assertEquals(false, inv.isDead());
//    }

//    @Test
//    public void testInvaderIsDead() {
//        INVADER inv = /* Your Constructor Here */
//        inv.hit();
//        assertEquals(true, inv.isDead());
//    }

//    @Test
//    public void testInvaderIntersectWithPlayerProjectile() {
//        INVADER inv = /* Your Constructor Here */
//        Projectile proj = /* Your Constructor Here */
//        assertTrue(proj.intersect(inv));

//    }

//    @Test
//    public void testInvaderIntersectWithPlayerProjectile() {
//        INVADER inv = /* Your Constructor Here */
//        Projectile proj = /* Your Constructor Here */
//        assertFalse(proj.intersect(inv));

//    }

}
