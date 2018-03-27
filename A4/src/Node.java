
public class Node {

	public char data;
	public Node	right;
	public Node left;

	public int rightSpc;
	public int leftSpc;
	public int col;
	
	public Node(char ch) {
		this.data = ch;
		
		this.right = null;
		this.left = null;
		
		this.rightSpc = 0;
		this.leftSpc = 0;	
		this.col = 0;
	}
	
	
}
