/* 
 * Name: Lee Dong Geon
 * Student ID: 2011147079
 * Don't forget to remove the package line.
 */

public class AVLTree
{
    private AVLTreeNode root;
    private int size;
    private AVLTreeNode list[];
    private int index;
    private boolean check1;
    private AVLTreeNode find;

    /* 
     * Our instance variables.
     *
     * root - AVLTreeNode, root of our AVLTree
     * size - int, the number of elements in our AVLTree
     */

    public AVLTree()
    {
    	root = null;
    	this.size = 0;
        /*
         * Our constructor. 
         * Initialize the instance variables to their default values
         */
    }

    public AVLTreeNode insert(int data, AVLTreeNode v, AVLTreeNode p){
    	if(v == null){
    		if(data > v.getParent().getData()){
   				v = new AVLTreeNode(data);
   				v.setParent(p);
    		}
    		else
    			v = new AVLTreeNode(data);
    			v.setParent(p);
    	}
    	else{
    		if(data > v.getData()){
    			insert(data, v.getRight(), v);
    		}
    		else if(data < v.getData()){
    			insert(data, v.getLeft(), v);
    		}
    	}
    	return v;
    }
    
    public AVLTreeNode insert(int data)
    {
    	if(contains(data))
    	{
    		return null;
    	}
    	
    	AVLTreeNode result = new AVLTreeNode();
    	if(size ==0){
    		root.setData(data);
    		result = root;
    		size++;
    	}
    	else{
    		result = insert(data, result, root);
    		size++;
    	}
    	return result;
        /*
         * Constructs a new AVLTreeNode and inserts it into our AVLTree
         *
         * return the inserted AVLTreeNode or null if a node with the same data
         * already exists
         */
    }
    public void retrieve(int data, AVLTreeNode v){
    	if(v.equals(null)){
    		find = null;
    		return;
    	}
    	if(v.getData() > data){
    		check(data, v.getLeft());
    	}
    	else if(v.getData() < data){
    		check(data, v.getRight());
    	}
    	else{
    		find = v;
    		return;
    	}
    }
    public AVLTreeNode retrieve(int data)
    {
    	find = null;
    	if(!contains(data))
    		return null;
    	else{
    		retrieve(data, root);
    		return find;
    	}
    		
        /*
         * returns the node in the tree containing the desired data
         *
         * return null if it is not in the tree
         */	
    }
    public void check(int data, AVLTreeNode v){
    	if(v.equals(null)){
    		return;
    	}
    	if(v.getData() > data){
    		check(data, v.getLeft());
    	}
    	else if(v.getData() < data){
    		check(data, v.getRight());
    	}
    	else{
    		this.check1 = true;
    		return;
    	}
    }
    public boolean contains(int data)
    {
    	check1 = false;
    	check(data, root);
    	return check1;
    	
        /*
         * return whether or not the tree contains a node with the desired data
         */
    }

//    public AVLTreeNode delete(int data)
  //  {
        /*
         * remove and return the AVLTreeNode with the desired data
         *
         * return null if the data is not in the AVLTree
         */
 //   }

//    public AVLTreeNode leftRotate(AVLTreeNode node)
 //   {
        /*
         * Perform a left rotate on the subtree rooted at node
         *
         * return the new root of the subtree
         */
 //   }

//    public AVLTreeNode rightRotate(AVLTreeNode node)
//    {
        /*
         * Perform a right rotate on the subtree rooted at node
         *
         * return the new root of the subtree
         */
 //   }
    
    public void pre(AVLTreeNode v){
    	list[this.index] = v;
    	this.index++;
    	pre(v.getLeft());
    	pre(v.getRight());
    }
    public AVLTreeNode[] preorder()
    {
    	 list = new AVLTreeNode[size];
    	 index = 0;
    	 pre(root);
    	 return list;
    	 
        /*
         * return an array of AVLTreeNodes in preorder
         */
    }
    public void post(AVLTreeNode v){
    	post(v.getLeft());
    	post(v.getRight());
    	list[this.index] = v;
    	this.index++;
    }
    public AVLTreeNode[] postorder()
    {
    	list = new AVLTreeNode[size];
    	index = 0;
    	post(root);
    	return list;
    }
    /*inorder(v)
     * if left(v) != null
     * 	inorder(left(v))
     * visit(v)
     * if right(v) != null
     * 	inorder(right(v))
     */
    public void in1(AVLTreeNode v){
    	if(!v.getLeft().equals(null)){
    		in1(v.getLeft());
    	}
    	list[this.index] = v;
    	this.index++;
    	if(!v.getRight().equals(null)){
    		in1(v.getRight());
    	}
    }
    public AVLTreeNode[] inorder()
    {
    	list = new AVLTreeNode[size];
    	index = 0;
    	in1(root);
    	return list;
        /*
         * return an array of AVLTreeNodes in inorder
         */
    }

    public void clear()
    {
    	root.setData(0);
    	root.clearRight();
    	root.clearLeft();
    	
    	this.size = 0;
        /*
         * clear the AVLTree
         */
    }

    public boolean isEmpty()
    {
    	if(size ==0)
    		return true;
    	else
    		return false;
        /*
         * return whether or not the AVLTree is empty
         */
    }

    public AVLTreeNode getRoot()
    {
    	return root;
        /*
         * return the root of the AVLTree
         */
    }
    
    public int getHeight()
    {	
    	return root.getHeight();
        /*
         * return the height of the AVLTree
         */
    }

    public String toString()
    {
    	String a = "";
    	preorder();
    	a = Integer.toString(list[0].getData());
    	for(int i = 1; i < list.length; i++){
    		a = a + "," + Integer.toString(list[i].getData());
    	}
    	return a;
        /*
         * return a string representation of the AVLTree.
         *
         * The format is the elements of the tree in preorder, each separated
         * by a comma. There should be no spaces and no trailing commas.
         */
    }

}
class AVLTreeNode
{
    private int data, height, balanceFactor;
    private AVLTreeNode left, right, parent;

    /* 
     * Our instance variables.
     *
     * data - int, the data the AVLTreeNode will hold
     * height - int, the height of the AVLTreeNode
     * balanceFactor - int, the balance factor of the node
     * left - AVLTreeNode, the left child of the node
     * right - AVLTreeNode, the right child of the node
     */

    public AVLTreeNode(){
    	
    }
    public AVLTreeNode(int data)
    {
    	setData(data);
    	this.left = null;
    	this.right = null;
        /*
         * Our constructor. 
         * Initialize the instance variables to their default values or the
         * values passed as paramters
         */
    }
    public AVLTreeNode(int data, AVLTreeNode parent_v){
    	setData(data);
    	this.parent = parent_v;
    	this.left = null;
    	this.right = null;
    }
    public AVLTreeNode getParent(){
    	return parent;
    }
    public void setData(int data)
    {
    	this.data = data;
        /*
         * Set the value stored in data
         */
    }

    public void setHeight(int height)
    {
    	this.height = height;
        /*
         * Set the value stored in height
         */
    }

    public void setBalanceFactor(int balanceFactor)
    {
    	this.balanceFactor = balanceFactor;
        /*
         * Set the value stored in balanceFactor
         */
    }
    public void setParent(AVLTreeNode parent){
    	this.parent = parent;
    }

    public void setLeft(AVLTreeNode left)
    {
    	this.left = left;
        /*
         * Set the left child
         */
    }

    public void setRight(AVLTreeNode right)
    {
    	this.right = right;
        /*
         * Set the right child
         */
    }

    public void clearLeft()
    {
    	this.left = null;
        /*
         * clear the left child
         */
    }

    public void clearRight()
    {
    	this.right = null;
        /*
         * clear the right child
         */
    }

    public int getData()
    {
    	return this.data;
        /*
         * get the data stored in the AVLTreeNode
         */
    }

    public int getHeight()
    {
    	return this.height;
        /*
         * get the height of the AVLTreeNode
         */
    }

    public int getBalanceFactor()
    {
    	return this.balanceFactor;
        /*
         * get the balanceFactor of the AVLTreeNode
         */
    }

    public AVLTreeNode getLeft()
    {
    	return this.left;
        /*
         * get the left child
         */
    }

    public AVLTreeNode getRight()
    {
    	return this.right;
        /*
         * get the right child
         */
    }

    public String toString()
    {
    	return Integer.toString(this.data);
        /*
         * return the string value of the data stored in the node
         */
    }
    }