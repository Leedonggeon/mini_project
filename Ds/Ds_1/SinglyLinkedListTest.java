public class SinglyLinkedListTest
{
    public static void main(String[] args)
    {
        try
        {
            SinglyLinkedList ll = new SinglyLinkedList();
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }

        try
        {
            SinglyLinkedList ll = new SinglyLinkedList();
            ll.add('a',0);
            ll.add('b',1);
            ll.add('c',0);
            System.out.println(ll);
            System.out.println("Check that add is working: " + (ll.toString().equals("cab")));
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }
        
        try
        {
            SinglyLinkedList ll = new SinglyLinkedList();
            ll.add('a',0);
            ll.add('b',1);
            ll.add('c',0);
            System.out.println("Check that contains works: " + ((ll.contains('f') == false) && (ll.contains('a') == true)));
            ll.delete(2);
            System.out.println("Check that deletion works: " + (ll.toString().equals("ca")));
            System.out.println("Check that item deletion of a nonexistant item works: " + (ll.deleteItem('f') == null));
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }

        try
        {
            SinglyLinkedList ll = new SinglyLinkedList();
            ll.add('a',0);
            ll.add('b',1);
            ll.add('c',0);            
            System.out.println("Check that getSize works: " + (ll.getSize() == 3));
            ll.clear();
            System.out.println("Check that clear works: " + (ll.isEmpty() == true));
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }
        
        try
        {
            SinglyLinkedList ll = new SinglyLinkedList();
            ll.add('a',0);
            ll.add('b',1);
            ll.add('c',0);
            ll.delete(2);
            System.out.println("Check that contains works: " + (ll.contains('a')));
            System.out.println("Checks that getIndex works: " + (ll.getIndex('a') == 1));   
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }

        try
        {
            SinglyLinkedList ll = new SinglyLinkedList();
            ll.add('a',0);
            ll.add('b',1);
            ll.add('c',0);
            System.out.println("Check that getHead works: " + (ll.getHead().getData() == 'c'));
        }

        catch(Exception e)
        {
            System.out.println("failed");
        }
    }
}