import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.AfterClass;

public class SolverTest
{
    Solver s;

    @Test(timeout=100)
    public void testOne()
    {
        int[] sequence = {2,4,3,1,0};
        s = new Solver(5, 5, sequence);
        assertEquals("YES", s.solve());
    }

    @Test(timeout=100)
    public void testTwo()
    {
        int[] sequence = {2,4,3,1,0};
        s = new Solver(5, 1, sequence);
        assertEquals("OVERFLOW", s.solve());
    }

    @Test(timeout=100)
    public void testThree()
    {
        int[] sequence = {2,0,1,4,3};
        s = new Solver(5, 1, sequence);
        assertEquals("NO", s.solve());
    }
}