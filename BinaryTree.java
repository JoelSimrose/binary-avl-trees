//Joel Simrose
//October 25, 2018

import java.util.*;
import java.lang.*;
import javax.swing.*;


/** 
*This is a class which creates a binary tree and an AVL tree and outputs their heights based on the data points inserted.
*/
public class BinaryTree
{

	Node root;

	/**
	*This is the trees constructor
	*@param value inputs a random value between zero and the size of data to set as the root 
	*/
	public BinaryTree(int value)
	{	
		root = new Node(value);
	}

	/**
	*This is a method which adds a new node to the binary tree
	*@param current to recurse through the tree checking the nodes to find the right spot for the new node to insert
	*@param newInsert the random number to make a new node out of
	*@return returns the current node
	*/
	public Node binaryAdd(Node current, int newInsert)
	{

		if(current == null)
		{
			current = new Node(newInsert); 
			return current;
		}

		else
		{
			if(newInsert < current.getValue())
			{
				current.left = binaryAdd(current.left, newInsert);
			}

			else if(newInsert > current.getValue())
			{
				current.right = binaryAdd(current.right, newInsert);
			}

			else if(newInsert == current.getValue())
			{
				current.setValue(newInsert);
				return current;
			}

		}

		return current;
	}


	/**
	*This is a method which adds a new node to the AVL tree
	*@param current to recurse through the tree checking the nodes to find the right spot for the new node to insert
	*@param newInsert the random number to make a new node out of
	*@return returns the current node
	*/
	public Node avlAdd(Node current, int newInsert)
	{

		if(current == null)
		{
			current = new Node(newInsert); 
			return current;
		}

		else
		{
			if(newInsert < current.getValue())
			{
				current.setLeft(avlAdd(current.getLeft(), newInsert));
			}

			else if(newInsert > current.getValue())
			{
				current.setRight(avlAdd(current.getRight(), newInsert));
			}

			else if(newInsert == current.getValue())
			{
				current.setValue(newInsert);
				return current;
			}

		}


		current.height = max(height(current.left),height(current.right))+ 1;

		if(current == null)
		{
			return current;
		}

		int difference = getDifference(current);
		
		//LL Case
		if(difference > 1 && newInsert < current.getLeft().getValue())
		{		
	
			return rightRotate(current);

		}

		//RR Case
		if(difference < -1 && newInsert > current.getRight().getValue())
		{

			return leftRotate(current);
			
		}

		//LR Case
		if(difference > 1 && newInsert > current.getLeft().getValue())
		{

			current.setLeft(leftRotate(current.getLeft()));
			return rightRotate(current);
		
		}

		//RL Case
		if(difference < -1 && newInsert < current.getRight().getValue())
		{	

			current.setRight(rightRotate(current.getRight()));
			return leftRotate(current);
		
		}

		return current;	
	}

	/**
	*This is a method which takes in two integers and returns the largest
	*@param a first integer to compare
	*@param b second integer to compare
	*@return returns the larger integer
	*/ 
	private static int max(int a, int b)
	{
		if(a > b){return a;}

		else{return b;}
	}

	
	/**
	*This is a method which does a right rotation to balance the AVL tree 
	*@param c to pass in the node to do the rotation
	*@return returns the node back to the add method
	*/
	private Node rightRotate(Node c)
	{
		
		Node b = c.left;

		Node t3 = b.right;

		//Rotate
		b.right = c;
		c.left = t3;

		c.height = max(height(c.left), height(c.right)) + 1;
		b.height = max(height(b.left), height(b.right)) + 1;

		return b;

	}

	/**
	*This is a method which does a left rotation to balance the AVL tree 
	*@param c to pass in the node to do the rotation
	*@return returns the node back to the add method
	*/
	private Node leftRotate(Node c)
	{
		
		Node b = c.right;
		Node t2 =  b.left;

		//Rotate
		b.left = c;
		c.right = t2;
	

		//Update
		c.height = Math.max(height(c.left), height(c.right))+ 1;
		b.height = Math.max(height(b.left), height(b.right))+ 1;

		return b;
	}

	
	/**
	*This is a method which returns the height of the node passed in.
	*@param node to pass in the node that you want the height of
	*@return returns the height in an integer
	*/
	private int height(Node node)
	{
		if(node == null)
		{
			return 0;
		}

		return node.getHeight();
	}

	/**
	*This is a function which returns the difference in heights of adjacent nodes to know whether the tree is unbalanced or not
	*@param node pass in the parent node to get difference between heights of children
	*@return returns an integer which is the difference between the nodes
	*/
	private int getDifference(Node node)
	{
		if(node == null)
		{
			return 0;
		}

			return height(node.getLeft()) - height(node.getRight());
	}


	/**
	*This is a method which determines the total height of the tree to output
	*@param c to pass in the root node 
	*@return returns the node back to the add method
	*/
	private static int getTotalHeight(Node node)
	{
		if(node == null)
		{
			return 0;
		}

		else{
			return max(getTotalHeight(node.getLeft()),getTotalHeight(node.getRight()))+1;
		}
	}


	public static void main(String args[])
	{
		int dataSize = (600);

		System.out.println("Data Size is: " +dataSize);

		//Get Random Number for Root
		Random num = new Random();
		int root = num.nextInt(dataSize);
	
		//Make new Trees
		BinaryTree binaryTree = new BinaryTree(root);
		BinaryTree avlTree = new BinaryTree(root);

		
		//Create array of random numbers the size of dataSize
		int[] randomArray = RandomArrayGen(dataSize);
		

		//Binary Search Tree Insertion
		for(int i=0; i < dataSize; i++)
		{
			binaryTree.root = binaryTree.binaryAdd(binaryTree.root, randomArray[i]);
		} 
		

		//AVL Tree Insertion
		for(int j=0; j < dataSize; j++)
		{
			

			avlTree.root = avlTree.avlAdd(avlTree.root, randomArray[j]);
				
		}

		//Get tree heights
		int bstHeight = getTotalHeight(binaryTree.root);
		int avlHeight = getTotalHeight(avlTree.root);
		

		//Print out the heights of each tree after insertion has finished
		System.out.println("Binary Tree Height: "+bstHeight);
		System.out.println("AVL Tree Height: "+avlHeight);

	}

	/**
	*This is a function which does an in order traversal of the trees which was used for testing purposes
	*@param node to pass in the root node to recurse through tree
	*/
	private static void InOrderPrint(Node node, int depth)
	{

		if(node != null)
		{
			
			InOrderPrint(node.getLeft(),depth+1);

			System.out.println("Height is: "+depth+" Node is: " + node.getValue());

			InOrderPrint(node.getRight(),depth+1);
		}

		else
		{			
			return;
		}

	}

	/**
	*This is a function to create a random number array of the specified size of numbers between 0 twice the data size.
	*@param takes an integer to specify the size of array 
	*@return returns the random number array which was created 
	*/
	public static int[] RandomArrayGen(int dataSize)
	{
		int[] randomArray = new int[dataSize];

		for(int i=0; i<dataSize; i++)
		{
			
			Random number = new Random();
			randomArray[i] = number.nextInt(dataSize);

		}

		return randomArray;
			
	}
	
}



