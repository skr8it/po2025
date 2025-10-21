import org.junit.Test;

import static org.junit.Assert.*;

public class CodingBatTest {
    CodingBat cb = new CodingBat();
    @Test
    public void lastChars() {
        assertEquals("sb",cb.lastChars("sigma","bbbb"));
        assertEquals("sc",cb.lastChars("sigma","koc"));
        assertEquals("oo",cb.lastChars("osigma","cialo"));
    }

    @Test
    public void intMax() {
        assertEquals(10,cb.intMax(3,2,10));
        assertEquals(67,cb.intMax(67,6,7));
        assertEquals(41,cb.intMax(41,4,3));
    }

    @Test
    public void diff21() {
        assertEquals(0,cb.diff21(21));
        assertEquals(74,cb.diff21(58));
        assertEquals(67,cb.diff21(-46));
    }

    @Test
    public void sum28() {
        assertEquals(false,cb.sum28(new int[]{2,2,2,2,2,2,5,6,7}));
        assertEquals(true,cb.sum28(new int[]{2,2,2,2,5,6,7}));
        assertEquals(true,cb.sum28(new int[]{2,2,3,4,5,6,7,2,2,432,31,67}));
    }
}