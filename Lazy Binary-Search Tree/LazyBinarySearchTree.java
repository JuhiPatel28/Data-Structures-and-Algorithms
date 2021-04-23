import java.util.Stack;
import java.util.StringJoiner;

/*
 * Juhi Patel
 * Class: 3345
 * Section: 003
 * Semester Spring 2019
 * Project 3 - LazyBinarySearchTree
 */

public class LazyBinarySearchTree 
{
	//variables
	private TreeNode root;
	public String tf = "";
	
	private class TreeNode //node class
	{
		//node variables
		int key;
		TreeNode leftChild;
		TreeNode rightChild;
		boolean deleted;
		
		public TreeNode(int k) //node constructor
		{
			key = k;
			leftChild = null;
			rightChild = null;
			deleted = false;
		}
		
		public String toString() //node tostring
		{
			if(deleted == false)
			{
				return "" + this.key;
			}
			else
			{
				return "*" + this.key;
			}
		}
	}
	
	public LazyBinarySearchTree() //class constructor
	{
		root = null;
	}
	
	public boolean insert(int key) //insert function
	{
		TreeNode add = new TreeNode(key);
		
		if(key < 1 || key > 99) //if key is out of bounds
		{
			throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
		}
		
		if(root == null) //if empty tree
		{
			root = add;
			tf += "True \n";
			return true;
		}
		
		//new node variables
		TreeNode temp = root;
		TreeNode tempparent = null;
		
		while(temp != null) //loop until at the end of the tree
		{
			tempparent = temp;
			if(key > temp.key) //if the key is bigger
			{
				temp = temp.rightChild; //go right
			}
			else if(key < temp.key) //otherwise
			{
				temp = temp.leftChild; //go left
			}
			else
			{
				if(temp.deleted) //if the node is flagged as deleted
				{
					temp.deleted = false; //return false
					tf += "True \n";
					return true;
				}
				else
				{
					tf += "False \n";
					return false;
				}
			}
		}
		
		if(key > tempparent.key) //if the key is bigger
		{
			tempparent.rightChild = add; //set as right child
		}
		else //otherwise
		{
			tempparent.leftChild = add; //set as left child
		}
		tf += "True \n";
		return true;
	}
	
	public boolean delete(int key) //delete function
	{
		if(key < 1 || key > 99) //out of bounds
		{
			throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
		}
		
		if(root == null) //if empty list
		{
			tf += "False \n";
			return false;
		}
		
		TreeNode temproot = root;
		
		while(temproot != null) //while not at the end of the tree
		{
			if(temproot.key == key) //if the key is equal
			{
				if(temproot.deleted) //if the node is deleted
				{
					tf += "False \n"; //return false
					return false;
				}
				else //otherwise
				{
					temproot.deleted = true; //return true
					tf += "True \n";
					return true;
				}
			}
			else if(key > temproot.key) //if the key is bigger
			{
				temproot = temproot.rightChild; //go right
			}
			else //otherwise
			{
				temproot = temproot.leftChild; //go left
			}
		}
		tf += "False \n"; //return false
		return false;
	}
	
	public int findMin() //find min
	{		
		if(root == null) //if the list is empty
		{
			return -1;
		}
		
		//variables
		TreeNode temproot = root;
		Stack<TreeNode> s = new Stack<TreeNode>();
		
		while(temproot != null) //push all left children onto stack
		{
			s.push(temproot);
			temproot = temproot.leftChild;
		}
		
		while(temproot != null || s.size() > 0)
		{			
			temproot = s.pop();
			if(temproot.deleted == false) //pop until you find the first non-deleted
			{
				return temproot.key;
			}
		}
		return -1;
	}
	
	public int findMax()
	{
		if(root == null) //if empty list
		{
			return -1;
		}
		
		//variables
		TreeNode temproot = root;
		Stack<TreeNode> s = new Stack<TreeNode>();
		
		while(temproot != null) //push all right children onto stack
		{
			s.push(temproot);
			temproot = temproot.rightChild;
		}
		
		while(temproot != null || s.size() > 0)
		{			
			temproot = s.pop();
			if(temproot.deleted == false) //pop until you find the first non-deleted one
			{
				return temproot.key;
			}
		}
		return -1;	
	}
	
	public boolean contains(int key) //contains function
	{
		if(key < 1 || key > 99) //bounds
		{
			throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
		}
		
		if(root == null) //if empty list
		{
			tf += "False \n";
			return false;
		}
		
		TreeNode temproot = root;
		while(temproot != null) //loop until end of tree
		{
			if(key > temproot.key) //if key is bigger
			{
				temproot = temproot.rightChild; //go to right child
			}
			else if(key < temproot.key) //if key is smaller
			{
				temproot = temproot.leftChild; //go to left child
			}
			else //if they are equal
			{
				if(temproot.deleted == false) //if not deleted
				{
					tf += "True \n"; //return true
					return true;
				}
				else //if deleted
				{
					tf += "False \n"; //return false
					return false;
				}
			}
		}
		tf += "False \n"; //return false
		return false;		
	}
	
	public String toString() //to string
	{
		return this.toString(root); //call tostring helper function
	}
	
	public String toString(TreeNode n)
	{
		if(n == null)
		{
			return "";
		}
		
		StringJoiner j = new StringJoiner(" ");
		
		//print preorder
		j.add(n.toString());
		
		if(n.leftChild != null)
		{
			j.add(toString(n.leftChild));
		}
		
		if(n.rightChild != null)
		{
			j.add(toString(n.rightChild));
		}
		
		return j.toString();
	}
	
	public int height()//height function
	{
		return height(this.root); //call height helper function
	}
	
	public int height(TreeNode n) //height helper function
	{
		if(n == null) //if empty tree
		{
			return -1;
		}
		else //otherwise
		{
			//get height of right and left
			int left = height(n.leftChild); 
			int right = height(n.rightChild);			
			
			return Math.max(right, left) + 1; //return the max +1
		}
	}
	
	public int size() //size function
	{
		if(root == null)
		{
			return 0;
		}
		return size(root, 0); //call helper size function
	}
	
	public int size(TreeNode n, int s)
	{
		if(n != null) //while not at end of list
		{
			//get size
			s++;
			s = size(n.rightChild, s);
			s = size(n.leftChild, s);					
		}
		return s;
	}
}
