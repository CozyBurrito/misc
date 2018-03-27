import java.io.File;
import java.util.Scanner;

public class BST {

	private Node root;

	public static void main(String[] args) {
		try {
			new BST().run();
		} catch (Exception e) {
			System.out.println("An Error Occurred");
			e.printStackTrace();
		}
	}

	public void run() throws Exception{
		Scanner inFile = new Scanner(new File("11.txt")); //TODO: FILE INPUT 
		char ch;
		String line = null;
		root = null;
		int numNodes = 0;
		PrintTree tree = null;

		while(inFile.hasNext()) {
			line = inFile.next();
			for (int i = 0; i < line.length(); i++) {
				ch = line.charAt(i);
				insert(ch);
			}	
		}

		numNodes = postWidth(root) + 1;
	
		tree = new PrintTree(numNodes);
		
		tree.print(root);
		
		inFile.close();
	}

	private void insert(char ch){
		if(root == null) {
			root = new Node(ch);
		} else {
			insertRec(root, ch);
		}
	}

	private void insertRec(Node node, char ch){
		if((int) ch < node.data) {
			if(node.left == null) {
				node.left = new Node(ch);
			} else {
				insertRec(node.left, ch);
			}
		} else {
			if(node.right == null) {
				node.right = new Node(ch);
			} else {
				insertRec(node.right, ch);
			}
		}
	}

	private int postWidth(Node node){
		int width = 0;

		if(node == null)
			width = 0;
		else {
			node.leftSpc = (node.left == null) ? 0 : postWidth(node.left) + 1;
			node.rightSpc = (node.right == null) ? 0 : postWidth(node.right) + 1;
			width = node.leftSpc + node.rightSpc;
		}

		return width;
	}

}
