/*
 * Author: Marco Cognetta
 */
 
public class SinglyLinkedList
{
    /*
     * Our instance variables
     * size will hold the number of elements in the linked list
     * head references the head node in the linked list
     */

    private int size;
    private SinglyLinkedListNode head;

    public SinglyLinkedList()
    {
        /*
         * Our constructor
         * Should initalize the instance variables to their default value
         * Since it is empty at the start, head should be null
         */

        this.size = 0;
        this.head = null;
    }

    public SinglyLinkedListNode add(int data, int index)
    {
        /*
         * Adds and returns a new node with the provided data at the specified index
         * Returns null if the index is not possible
         */

        if(index < 0 || index > size)
        {
            return null;
        }

        if(head == null)
        {
            head = new SinglyLinkedListNode(data);
            size++;
            return head;
        }

        if(index == 0)
        {
            SinglyLinkedListNode temp = new SinglyLinkedListNode(data);
            temp.setNext(head);
            head = temp;
            size++;
            return head;
        }

        int i = 0;
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode temp = new SinglyLinkedListNode(data);
        while(i<index-1)
        {
            current = current.getNext();
            i++;
        }

        temp.setNext(current.getNext());
        current.setNext(temp);
        size++;
        return temp;

    }

    public SinglyLinkedListNode delete(int index)
    {
        /*
         * Deletes and returns the node at the index specified
         * Returns null if the index does not exist
         */

        if(index < 0 ||  index >= size)
        {
            return null;
        }

        if(index == 0)
        {
            SinglyLinkedListNode output = head;
            head = head.getNext();
            size--;
            return output;
        }

        if(index == size-1)
        {
            SinglyLinkedListNode current = head;
            while(current.getNext().getNext() != null)
            {
                current = current.getNext();
            }
            SinglyLinkedListNode output = current.getNext();
            current.clearNext();
            size--;
            return output;
        }

        int i = 0;
        SinglyLinkedListNode current = head;
        while(i<index)
        {
            current = current.getNext();
            i++;
        }

        SinglyLinkedListNode output = current.getNext();
        current.setNext(output.getNext());
        size--;
        return output;
    }

    public SinglyLinkedListNode deleteItem(int data)
    {
        /*
         * Deletes and returns the first node with the specified data in the linked list
         * Returns null if the item doesn't exist
         */

        if(head == null)
        {
            return null;
        }

        if(head.getData() == data)
        {
            SinglyLinkedListNode output = head;
            head = head.getNext();
            size--;
            return output;
        }

        SinglyLinkedListNode current = head;
        while(current != null && current.getNext() != null)
        {
            if(current.getNext().getData() == data)
            {
                SinglyLinkedListNode output = current.getNext();
                current.setNext(current.getNext().getNext());
                size--;
                return output;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean contains(int data)
    {
        /*
         * Checks if the linked list contains a node with the specified data
         */

        SinglyLinkedListNode current = head;
        while(current != null)
        {
            if(current.getData() == data)
            {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int getSize()
    {
        /*
         * Returns the number of elements in the linked list
         */

        return size;
    }

    public SinglyLinkedListNode getHead()
    {
        /*
         * Returns the head of the linked list
         */

        return head;
    }

    public int getIndex(int data)
    {
        /*
         * Returns the index of the first node with the specified data
         * Returns -1 if the index does not exist
         */

        int i = 0;
        SinglyLinkedListNode current = head;
        while(current != null)
        {
            if(current.getData() == data)
            {
                return i;
            }
            i++;
            current = current.getNext();
        }
        return -1;
    }

    public SinglyLinkedListNode getNode(int index)
    {
        /*
         * Returns the index of the first node with the specified data
         * Returns -1 if the index does not exist
         */

        if(index < 0 || index >= size)
        {
            return null;
        }
        int i = 0;
        SinglyLinkedListNode current = head;
        while(i<index)
        {
            current = current.getNext();
            i++;
        }
        return current;
    }

    public boolean isEmpty()
    {
        /*
         * Returns whether or not the linked list is empty
         */

        return head == null;
    }

    public void clear()
    {
        /*
         * Clears the linked list
         */

        head = null;
        size = 0;
    }

    public String toString()
    {
        /*
         * Returns the linked list in string form
         * The format is just the data from each node concatenated together
         * See the tests for an example
         * There should be no trailing whitespace
         */

        String output = "";
        SinglyLinkedListNode current = head;
        while(current != null)
        {
            output += current.toString();
            current = current.getNext();
        }
        return output;
    }
}

class SinglyLinkedListNode
{
    /*
     * Our instance variables
     * data will hold an int
     * next is the reference to the next element after this node (null if there is none)
     */

    private int data;
    private SinglyLinkedListNode next;

    public SinglyLinkedListNode(int data)
    {
        /*
         * The constructor
         * Should initalize the instance variables to their default value
         */
        this.data = data;
        this.next = null;
    }

    public int getData()
    {
        /*
         * Returns our data
         */
        return data;
    }

    public SinglyLinkedListNode getNext()
    {
        /*
         * Returns the SinglyLinkedListNode referenced by next
         */
        return next;
    }

    public void setData(int data)
    {
        /*
         * Allows us to change the data stored in our SinglyLinkedListNode
         */
        this.data = data;
    }

    public void setNext(SinglyLinkedListNode node)
    {
        /*
         * Allows us to change the next SinglyLinkedListNode
         */
        next = node;
    }

    public void clearNext()
    {
        /*
         * Removes the reference to the next SinglyLinkedListNode, replacing it with null
         */
        next = null;
    }

    public String toString()
    {
        /*
         * Returns our data in string form
         */
        return String.valueOf(data);
    }
}