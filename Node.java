class Node
{
	public int value;
	public Node left;
	public Node right;
	public int height;

	Node (int value)
	{
		this.value = value;
		this.right = null;
		this.left = null;
		this.height = 1;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

	public void setLeft(Node left)
	{
		this.left = left;
	}

	public Node getLeft()
	{
		return left;
	}

	public void setRight(Node right)
	{
		this.right = right;
	}

	public Node getRight()
	{
		return right;
	}

	public int getHeight()
	{
		return height;
	}
}