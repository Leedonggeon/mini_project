public class CircularBufferTest
{
    public static void main(String[] args)
    {
        try
        {
            CircularBuffer ll = new CircularBuffer(3);
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }

        try
        {
            CircularBuffer ll = new CircularBuffer(3);
            ll.add('a');
            ll.add('b');
            ll.add('c');
            System.out.println("Check that add is working: " + (ll.toString().equals("abc")));
            ll.add('d');
            System.out.println("Check that add is working when the capacity is reached: " + (ll.toString().equals("bcd")));
            System.out.println("Check that head is updated correctly when adding: " + (ll.getHead().getData() == 'b'));
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }
        
        try
        {
            CircularBuffer ll = new CircularBuffer(3);
            ll.add('a');
            ll.add('b');
            ll.add('c');
            System.out.println("Check that contains works: " + ((ll.contains('f') == false) && (ll.contains('a') == true)));
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }

        try
        {
            CircularBuffer ll = new CircularBuffer(3);
            ll.add('a');
            ll.add('b');
            ll.add('c');            
            System.out.println("Check that getSize works: " + (ll.getSize() == ll.getCapacity()));
            ll.clear();
            System.out.println("Check that clear and isEmpty works: " + (ll.isEmpty() == true));
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }
        
        try
        {
            System.out.println("Checking the example from the handout.")
            CircularBuffer ll = new CircularBuffer(3);
            ll.add('a');
            ll.add('b');
            ll.add('c');
            System.out.println("Check that everything is added correctly: " (ll.toString().equals("abc")));
            ll.add('d');
            System.out.println("Check adding when capacity is reached: " + (ll.toString().equals("bcd")));
            ll.delete();
            System.out.println("Check deletion: " + (ll.toString().equals("cd")));
            ll.add('e');
            System.out.println("Check add again: " + (ll.toString().equals("cde")));
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }
    }
}