/*
 * Name:
 * Student ID:
 */

public class CircularBuffer
{
    /*
     * Our instance variables
     * size will hold the number of elements in the buffer
     * capacity will hold the max number of elements the buffer can have
     * head references the head node in the buffer
     */

    private int size;
    private int capacity;
    private CircularBufferNode head;
    private CircularBufferNode tail;
    private boolean overflow_flag;

    public CircularBuffer(int capacity)
    {
        /*
         * Our constructor
         * Should initalize the instance variables to their default value
         * Since it is empty at the start, head should be null, and overflow_flag should be false
         */
    }

    public CircularBufferNode add(char data)
    {
        /*
         * Adds and returns a new node with the provided data to the end of the buffer
         */
    }

    public CircularBufferNode delete()
    {
        /*
         * Deletes and returns the node at the front of the buffer
         */
    }

    public boolean contains(char data)
    {
        /*
         * Checks if the buffer contains a node with the specified data
         */
    }

    public int getSize()
    {
        /*
         * Returns the number of elements in the buffer
         */
    }

    public int getCapacity()
    {
        /*
         * Returns the capacity of the buffer
         */
    }

    public CircularBufferNode getHead()
    {
        /*
         * Returns the head of the buffer
         */
    }

    public CircularBufferNode getTail()
    {
        /*
         * Returns the tail of the buffer
         */
    }

    public int getIndex(char data)
    {
        /*
         * Returns the index of the first node with the specified data
         * Returns -1 if the index does not exist
         */        
    }

    public CircularBufferNode getNode(int index)
    {
        /*
         * Returns the node at the specified index
         * Returns null if the index does not exist
         */
    }

    public boolean isEmpty()
    {
        /*
         * Returns whether or not the buffer is empty
         */
    }

    public boolean overflow()
    {
        /*
         * Returns whether or not previous operation caused an overflow
         */
    }

    public void clear()
    {
        /*
         * Clears the buffer
         */
    }

    public String toString()
    {
        /*
         * Returns the buffer in string form
         * The format is just the data from each node concatenated together
         * See the tests for an example
         * There should be no trailing whitespace
         */
    }
}

class CircularBufferNode
{
    /*
     * Our instance variables
     * data will hold a char
     * next is the reference to the next element after this node (null if there is none)
     */

    private char data;
    private CircularBufferNode next;

    public CircularBufferNode(char data)
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

    public CircularBufferNode getNext()
    {
        /*
         * Returns the CircularBufferNode referenced by next
         */
    }

    public void setData(char data)
    {
        /*
         * Allows us to change the data stored in our CircularBufferNode
         */
    }

    public void setNext(CircularBufferNode node)
    {
        /*
         * Allows us to change the next CircularBufferNode
         */
    }

    public void clearNext()
    {
        /*
         * Removes the reference to the next CircularBufferNode, replacing it with null
         */
    }

    public String toString()
    {
        /*
         * Returns our data in string form
         */
    }
}
