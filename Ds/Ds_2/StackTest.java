import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.AfterClass;

public class StackTest
{
    Stack s;

    @Before
    public void setup() 
    {
        s = new Stack(3);
    }

    @Test(timeout=100)
    public void testPush()
    {
        s.push(0);
        assertEquals("0", s.toString());
        assertEquals(1, s.getSize());
    }

    @Test(timeout=100)
    public void testPeek()
    {
        s.push(0);
        assertEquals(0, s.peek());
        assertEquals(1, s.getSize());
    }

    @Test(timeout=100)
    public void testPop()
    {
        s.push(0);
        s.push(1);
        s.pop();
        assertEquals("0", s.toString());
        assertEquals(1, s.getSize());
    }

    @Test(timeout=100)
    public void testToString()
    {
        s.push(0);
        s.push(1);
        assertEquals("1,0", s.toString());
    }
}