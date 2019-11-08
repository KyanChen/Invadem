package invadem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PowerInvaderTest {
    PowerInvader invader;

    @Before
    public void testConstructor() {
        invader = new PowerInvader(320, 100);
        assertNotNull(invader);
    }

    @Test
    public void testFire() {
        assertTrue(invader.fire() instanceof PowerProjectile);
    }

}
