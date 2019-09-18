/*
 * Name:
 * Student ID:
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
    }

    public SinglyLinkedListNode add(char data, int index)
    {
        /*
         * Adds and returns a new node with the provided data at the specified index
         * Returns null if the index is not possible
         */
    }

    public SinglyLinkedListNode delete(int index)
    {
        /*
         * Deletes and returns the node at the index specified
         * Returns null if the index does not exist
         */
    }

    public SinglyLinkedListNode deleteItem(char data)
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

    public SinglyLinkedListNode getHead()
    {
        /*
         * Returns the head of the linked list
         */
    }

    public int getIndex(char data)
    {
        /*
         * Returns the index of the first node with the specified data
         * Returns -1 if the index does not exist
         */        
    }

    public SinglyLinkedListNode getNode(int index)
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
}

class SinglyLinkedListNode
{
    /*
     * Our instance variables
     * data will hold a char
     * next is the reference to the next element after this node (null if there is none)
     */

    private char data;
    private SinglyLinkedListNode next;

    public SinglyLinkedListNode(char data)
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

    public SinglyLinkedListNode getNext()
    {
        /*
         * Returns the SinglyLinkedListNode referenced by next
         */
    }

    public void setData(char data)
    {
        /*
         * Allows us to change the data stored in our SinglyLinkedListNode
         */
    }

    public void setNext(SinglyLinkedListNode node)
    {
        /*
         * Allows us to change the next SinglyLinkedListNode
         */
    }

    public void clearNext()
    {
        /*
         * Removes the reference to the next SinglyLinkedListNode, replacing it with null
         */
    }

    public String toString()
    {
        /*
         * Returns our data in string form
         */
    }
}
