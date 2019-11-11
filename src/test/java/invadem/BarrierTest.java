package invadem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BarrierTest {
    Barrier[] barriers;

    @Before
    public void testLoadBarriers() {
        barriers = Barrier.loadBarriers();
        assertEquals(3, barriers.length);
        for (Barrier barrier : barriers) {
            assertEquals(7, barrier.getBlocks().length);
        }
    }

    @Test
    public void testGetHeight() {
        for (Barrier barrier : barriers) {
            assertEquals(24, barrier.getHeight());
        }
    }

    @Test
    public void testGetWidth() {
        for (Barrier barrier : barriers) {
            assertEquals(24, barrier.getWidth());
        }
    }
}
