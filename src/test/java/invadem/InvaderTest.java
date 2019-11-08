package invadem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;


public class InvaderTest {
    List<Invader> invaders;

    @Test
    public void testFire() {
        for (Invader invader : invaders) {
            assertNotNull(invader.fire());
        }
    }

    @Before
    public void testLoadInvaders() {
        invaders = Invader.loadInvaders();
        assertEquals(40, invaders.size());
        for (int i = 0; i < 10; i++) {
            assertTrue(invaders.get(i) instanceof ArmouredInvader);
        }

        for (int i = 10; i < 20; i++) {
            assertTrue(invaders.get(i) instanceof PowerInvader);
        }
        for (int i = 20; i < 40; i++) {
            assertNotNull(invaders.get(i));
        }
    }

    @Test
    public void testMove() {
        for (Invader invader : invaders) {
            int x = invader.getX();
            int y = invader.getY();
            for (int i = 0; i < 20; i++) {
                invader.move();
            }
            assertEquals(x + 10, invader.getX());

            for (int i = 0; i < 50; i++) {
                invader.move();
            }
            assertEquals(x + 30, invader.getX());
            assertEquals(y + 5, invader.getY());

            for (int i = 0; i < 30; i++) {
                invader.move();
            }
            assertEquals(x + 18, invader.getX());
            assertEquals(y + 8, invader.getY());

        }
    }

    @Test
    public void testMoveDown() {
        for (Invader invader : invaders) {
            int y = invader.getY();
            invader.moveDown();
            assertEquals(y + 1, invader.getY());
            assertEquals(1, invader.getCurrentSpriteIndex());
        }
    }

    @Test
    public void testMoveHoriz() {
        for (Invader invader : invaders) {
            int x = invader.getX();
            invader.moveHoriz();
            assertEquals(x + 1, invader.getX());
            assertEquals(0, invader.getCurrentSpriteIndex());
        }
    }

    @Test
    public void testReverseDir() {
        for (Invader invader : invaders) {
            assertEquals(-1, invader.reverseDir());
        }
    }

}
