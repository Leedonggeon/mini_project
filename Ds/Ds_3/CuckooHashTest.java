import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.AfterClass;

public class CuckooHashTest
{
    CuckooHash ch;
    //CuckooHash(int a1, int b1, int a2, int b2, int n, int chainLength, float threshold)
    @Test(timeout=100)
    public void testOne()
    {
        ch = new CuckooHash(1, 0, 1, 0, 3, 5, 0.75);
        ch.insert(1, 0, 1, 0, 3);
        assertEquals("0,1,0|0,0,0", ch.toString());
    }

    @Test(timeout=100)
    public void testTwo()
    {
        ch = new CuckooHash(1, 0, 1, 0, 3, 5, 0.75);
        assertEquals("0,0,0|0,0,0", ch.toString());
        ch.insert(1, 0, 1, 0, 3);
        assertEquals("0,1,0|0,0,0", ch.toString());
        ch.delete(1);
        assertEquals("0,0,0|0,0,0", ch.toString());
    }

    @Test(timeout=100)
    public void testThree()
    {
        ch = new CuckooHash(1, 0, 1, 0, 3, 5, 0.75);
        ch.insert(1, 0, 1, 0, 3);
        ch.insert(7, 0, 1, 0, 3);
        assertEquals("0,7,0|0,1,0", ch.toString());
        ch.delete(1);
        assertEquals("0,7,0|0,0,0", ch.toString());
    }

    @Test(timeout=1000)
    public void testFour()
    {
        ch = new CuckooHash(1, 0, 1, 0, 2, 5, 0.5);
        assertEquals("0,0|0,0", ch.toString());
        assertEquals(1, ch.getA1());
        assertEquals(0, ch.getB1());
        assertEquals(1, ch.getA2());
        assertEquals(0, ch.getB2());
        ch.insert(1, 2, 1, 3, 1); //this will not cause a resize
        assertEquals(1, ch.getA1());
        assertEquals(0, ch.getB1());
        assertEquals(1, ch.getA2());
        assertEquals(0, ch.getB2());
        assertEquals("0,1|0,0", ch.toString());
        ch.insert(2, 2, 1, 3, 1); //this will cause a resize
        assertEquals(2, ch.getA1());
        assertEquals(1, ch.getB1());
        assertEquals(3, ch.getA2());
        assertEquals(1, ch.getB2());
        assertEquals("0,2,0,1|0,0,0,0", ch.toString());
    }
}