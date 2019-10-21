package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectileTest {

   @Test
   public void testProjectileConstruction() {

       Projectile proj = new Projectile(ObjectEnum.TANK_Projectile, 100, 100, 1);
       assertNotNull(proj);
   }
   
//
//    @Test
//    public void testProjectileIsFriendly() {
//        Projectile proj = /* Your Constructor Here */
//        assertTrue(proj.isFriendly());
//    }
//
//    @Test
//    public void testProjectileIsNotFriendly() {
//        Projectile proj = /* Your Constructor Here */
//        assertFalse(proj.isFriendly());
//    }
//
//    @Test
//    public void testProjectileIntersect() {
//        Projectile proj = /* Your Constructor Here */
//        INVADER inv = /* Your Constructor Here */
//        TANK tank = /* Your Constructor Here */
//        assertFalse(proj.intersect(inv));
//        assertFalse(proj.intersect(tank));
//    }

}
