/*
 * Name:
 * Student ID:
 */

public class DoublyLinkedList
{
    /*
     * Our instance variables
     * size will hold the number of elements in the linked list
     * head references the head node in the linked list
     * tail references the tail node in the linked list
     */

    private int size;
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;

    public DoublyLinkedList()
    {
        /*
         * Our constructor
         * Should initalize the instance variables to their default value
         * Since it is empty at the start, head and tail should be null
         */
    }

    public DoublyLinkedListNode add(char data, int index)
    {
        /*
         * Adds and returns a new node with the provided data at the specified index
         * Returns null if the index is not possible
         */
    }

    public DoublyLinkedListNode delete(int index)
    {
        /*
         * Deletes and returns the node at the index specified
         * Returns null if the index does not exist
         */
    }

    public DoublyLinkedListNode deleteItem(char data)
    {
        /*
         * Deletes and returns the first node with the specified data in the linked list
         * Returns null if the item doesn't exist
         */
    }

    public boolean contains(char data)
    {
        /*
         * Checks if the linked list contains a node with the specified data
         */
    }

    public int getSize()
    {
        /*
         * Returns the number of elements in the linked list
         */
    }

    public DoublyLinkedListNode getHead()
    {
        /*
         * Returns the head of the linked list
         */
    }

    public DoublyLinkedListNode getTail()
    {
        /*
         * Returns the tail of the linked list
         */        
    }

    public int getIndex(char data)
    {
        /*
         * Returns the index of the first node with the specified data
         * Returns -1 if the index does not exist
         */        
    }

    public DoublyLinkedListNode getNode(int index)
    {
        /*
         * Returns the node at the specified index
         * Returns null if the index does not exist
         */
    }

    public boolean isEmpty()
    {
        /*
         * Returns whether or not the linked list is empty
         */
    }

    public void clear()
    {
        /*
         * Clears the linked list
         */
    }

    public String toString()
    {
        /*
         * Returns the linked list in string form
         * The format is just the data from each node concatenated together
         * See the tests for an example
         * There should be no trailing whitespace
         */
    }

    public String toStringReverse()
    {
        /*
         * Returns the linked list in string form in reverse
         * The format is just the data from each node concatenated together
         * There should be no trailing whitespace
         * Do not just call toString and reverse it, this will receive no points
         */
    }
}

class DoublyLinkedListNode
{
    /*
     * Our instance variables
     * data will hold a char
     * next is the reference to the next element after this node (null if there is none)
     */

    private char data;
    private DoublyLinkedListNode next;
    private DoublyLinkedListNode prev;

    public DoublyLinkedListNode(char data)
    {
        /*
         * The constructor
         * Should initalize the instance variables to their default value
         */
    }

    public char getData()
    {
        /*
         * Returns our data
         */
    }

    public DoublyLinkedListNode getNext()
    {
        /*
         * Returns the DoublyLinkedListNode referenced by next
         */
    }

    public DoublyLinkedListNode getPrev()
    {
        /*
         * Returns the DoublyLinkedListNode referenced by prev
         */        
    }

    public void setData(char data)
    {
        /*
         * Allows us to change the data stored in our DoublyLinkedListNode
         */
    }

    public void setNext(DoublyLinkedListNode node)
    {
        /*
         * Allows us to change the next DoublyLinkedListNode
         */
    }

    public void setPrev(DoublyLinkedListNode node)
    {
        /*
         * Allows us to change the prev DoublyLinkedListNode
         */
    }

    public void clearNext()
    {
        /*
         * Removes the reference to the next DoublyLinkedListNode, replacing it with null
         */
    }

    public void clearPrev()
    {
        /*
         * Removes the reference to the prev DoublyLinkedListNode, replacing it with null
         */
    }

    public String toString()
    {
        /*
         * Returns our data in string form
         */
    }
}
