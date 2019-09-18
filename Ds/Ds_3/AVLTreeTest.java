import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.AfterClass;

public class AVLTreeTest
{
    AVLTree t;

    @Before
    public void setup()
    {
        t = new AVLTree();
    }

    @Test(timeout=1000)
    public void testOne()
    {
        t.insert(3);
        t.insert(5);
        assertEquals(true, t.contains(3));
        assertEquals(true, t.contains(5));
        t.delete(3);
        assertEquals(false, t.contains(3));
        assertEquals(true, t.contains(5));
    }

    @Test(timeout=1000)
    public void testTwo()
    {
        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        int bf = t.getRoot().getBalanceFactor();
        assertTrue("Balance factor of root is wrong.", (bf == -1) || (bf == 0) || (bf == 1));
    }
    
    @Test(timeout=1000)
    public void testTree()
    {
        AVLTreeNode a = t.insert(1);
        AVLTreeNode b = t.insert(2);
        AVLTreeNode c = t.insert(3);
        boolean flag = true;

        if(a.getLeft() != null)
        {
            if(a.getLeft().getData() > a.getData())
            {
                flag = false;
            }
        }

        if(a.getRight() != null)
        {
            if(a.getRight().getData() < a.getData())
            {
                flag = false;
            }
        }

        if(b.getLeft() != null)
        {
            if(b.getLeft().getData() > b.getData())
            {
                flag = false;
            }
        }

        if(b.getRight() != null)
        {
            if(b.getRight().getData() < b.getData())
            {
                flag = false;
            }
        }

        if(c.getLeft() != null)
        {
            if(c.getLeft().getData() > c.getData())
            {
                flag = false;
            }
        }

        if(c.getRight() != null)
        {
            if(c.getRight().getData() < c.getData())
            {
                flag = false;
            }
        }

        assertTrue("BST property violated.", flag);
    }
}