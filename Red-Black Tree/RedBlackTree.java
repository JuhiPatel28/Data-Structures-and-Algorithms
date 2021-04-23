import java.util.*;


public class RedBlackTree<E extends Comparable<E>>
{
	//variables
	private static boolean RED = false;
	private static boolean BLACK = true;
	public String tf = "";
	
	//nodes
	public Node<E> nullnode = new Node<E>(null, BLACK);
	public Node<E> root = nullnode;
	
	public class Node<E extends Comparable<E>> //node class
	{
		E element;
		Node<E> leftChild;
		Node<E> rightChild;
		Node<E> Parent;
		boolean color; 
		
		public Node(E e, boolean c) //node constructor
		{
			element = e;
			color = c;
			Parent = null;
			rightChild = null;
			leftChild = null;					
		}
		
		public String toString() //node tostring
		{
			if(color == BLACK)
			{
				return "" + this.element;
			}
			else
			{
				return "*" + this.element;
			}
		}
	}	
	
	public RedBlackTree() //redblacktree root constructor
	{
		root.Parent = nullnode;
		root.leftChild = nullnode;
		root.rightChild = nullnode;
	}
	
	public boolean insert(E element) //insert function
	{		
		if(element == null) //if the element is null
		{			
			throw new NullPointerException("Given null element");
		}
				
		//variables
		Node<E> temproot = root;
		Node<E> temp = nullnode;
		Node<E> tobeadded = new Node<E>(element, RED);
		
		while(temproot != nullnode) //loop until the end of the redblacktree
		{
			temp = temproot; //set temp to the parent of temproot
			
			if(tobeadded.element.compareTo(temproot.element) < 0) //if the element is less than temproot
			{
				temproot = temproot.leftChild; //temproot = left of temproot
			}
			else if(tobeadded.element.compareTo(temproot.element) > 0) //if the element is greater than temproot
			{
				temproot = temproot.rightChild; //temproot = right of temproot
			}
			else //if the elements are equal
			{
				tf += "False \n"; //do not add
				return false;
			}
		}
		
		tobeadded.Parent = temp; //set the parent of new node to temp
		if(temp == nullnode) //if temp is the parent of root
		{
			root = tobeadded; //set root to new node
		}
		else if(tobeadded.element.compareTo(temp.element) < 0) //if temp is less than new node
		{
			temp.leftChild = tobeadded; //set new node to temp left
		}
		else if(tobeadded.element.compareTo(temp.element) > 0) //if temp is greater than new node
		{
			temp.rightChild = tobeadded; //set new node to temp right
		}
		else //if they are the same
		{
			tf += "False \n"; //do not add
			return false;
		}
		
		//set leaf nodes
		tobeadded.leftChild = nullnode; 
		tobeadded.rightChild = nullnode;
		
		colorFixer(tobeadded); //call the colorfixer function
		tf += "True \n"; //add to list
		return true;
	}
	
	private void colorFixer(Node<E> n) //balance and recoloring function
	{
		Node<E> uncle = nullnode; //create uncle node
		
		while(n.Parent.color == RED) //loop until the parent is black
		{
			if(n.Parent == n.Parent.Parent.leftChild) //if the uncle is the right child
			{
				uncle = n.Parent.Parent.rightChild;
				
				if(uncle.color == RED) //if both parent and uncle are red
				{
					//set parent and uncle to black
					n.Parent.color = BLACK;
					uncle.color = BLACK;
					//set grandpa to red
					n.Parent.Parent.color = RED;
					//set new node to be grandpa
					n = n.Parent.Parent;
				}
				else if(n == n.Parent.rightChild) //if new node is the right child
				{
					n = n.Parent; //set new node to parent
					RotateL(n); //rotate left
				}
				else //if new node is left child
				{
					n.Parent.color = BLACK;
					n.Parent.Parent.color = RED;
					RotateR(n.Parent.Parent); //double rotation
				}
			}
			else //if uncle is left child
			{
				uncle = n.Parent.Parent.leftChild;
				
				if(uncle.color == RED) //if parent and uncle are both red
				{
					//set uncle and parent to black
					n.Parent.color = BLACK;
					uncle.color = BLACK;
					//set grandpa to red
					n.Parent.Parent.color = RED;
					n = n.Parent.Parent; //set new node to grandpa
				}
				else if(n == n.Parent.leftChild) //if new node is left child
				{
					n = n.Parent; //set new node to parent
					RotateR(n); //rotate right
				}
				else //if new node is right child
				{
					n.Parent.color = BLACK;
					n.Parent.Parent.color = RED;
					RotateL(n.Parent.Parent); //double rotation
				}
			}
		}
		root.color = BLACK; //set 
	} 
	
	private void RotateL(Node<E> start) //rotate right function
	{
		Node<E> right = start.rightChild; //right child
		start.rightChild = right.leftChild; //set right child of new node to the left child of its right child
		
		if(right.leftChild != nullnode) //if the left child is not a leaf
		{
			right.leftChild.Parent = start; //set its parent to the new node
		}
		right.Parent = start.Parent; //set the parent to the parent of the new node
		
		if(start.Parent == nullnode) //if the node is the root
		{
			root = right; //set the new root to the right child
		}
		else if(start.Parent.leftChild == start) //if the node is the left child
		{
			start.Parent.leftChild = right; //set the left child to right
		}
		else //if the node is the right child
		{
			start.Parent.rightChild = right; //set the right child to right
		}
		
		right.leftChild = start; //set the left child to the new node
		start.Parent = right; //set the parent of the new node to the right child
	}
	
	private void RotateR(Node<E> start) //rotate left function
	{
		Node<E> left = start.leftChild; //left child
		start.leftChild = left.rightChild; //set the left child of the original to the right child of the left child
		
		if(left.rightChild != nullnode) //if there is a right child of the left child
		{
			left.rightChild.Parent = start; //set the parent of it to the original node
		}
		left.Parent = start.Parent; //set the left parent to the original parent
		
		if(start.Parent == nullnode) //if the original node was the root
		{
			root = left; //set the new root
		}
		else if(start.Parent.rightChild == start) //if the original node was the right child
		{
			start.Parent.rightChild = left; //set the right child to the left node
		}
		else //if the original node was the left child
		{
			start.Parent.leftChild = left; //set the left child to the left node
		}
		
		left.rightChild = start; //set the right child of the left node to the original node
		start.Parent = left; //set the original parent to left
	}
	
	public boolean contains(Comparable<E> object) //compare 
	{
		if(object != null) //if not given null object
		{
			return contains(object, root); //call helper function
		}
		else
		{
			return false;
		}
	}
	
	private boolean contains(Comparable<E> object, Node<E> n) //compare helper function
	{
		if(n != nullnode) //if node is not null
		{
			if(object.compareTo(n.element) == 0) //see if they match
			{
				tf += "True \n";
				return true;
			}
			else if(object.compareTo(n.element) > 0) //otherwise
			{
				return contains(object, n.rightChild); //recursively loop through whole list
			}
			else
			{
				return contains(object, n.leftChild);
			}
		}
		else //if end of list is reached without finding it
		{
			tf += "False \n"; //return not found
			return false;
		}
	}
	
	public String toString() //tostring function
	{
		return this.toString(root); //call tostring helper function
	}
	
	
	private String toString(Node<E> n) //tostring helper function
	{
		if(n == null) //if the list is empty
		{
			return ""; //return nothing
		}
		
		StringJoiner j = new StringJoiner(" "); //string joiner
		
		j.add(n.toString()); //add parent node
		
		if(n.leftChild != nullnode)
		{
			j.add(toString(n.leftChild)); //add left node
		}
		
		if(n.rightChild != nullnode)
		{
			j.add(toString(n.rightChild)); //add right node
		}
		
		return j.toString(); //return string
	}
	
}
