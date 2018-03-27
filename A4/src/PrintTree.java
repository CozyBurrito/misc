import java.util.LinkedList;
import java.util.Queue;

public class PrintTree {
	private char[][] printArr;
	private char[][] printArr2;
	private int index = 0;
	private boolean isRoot = true;
	private int numNodes;

	public PrintTree(int numNodes) {
		this.numNodes = numNodes;
		printArr = new char[numNodes*3][numNodes];

		for (int i = 0; i < printArr.length; i++) 
			for (int j = 0; j < printArr[i].length; j++) 
				printArr[i][j] = ' ';	
	}

	public void print(Node root) {
		boolean isEmpty, stop = false;
		int rows = 0;
		
		breadthPrint(root);
		
		
		for (int i = 0; i < printArr.length && !stop; i++) {
			isEmpty = true;
			
			for (int j = 0; j < printArr[i].length && isEmpty; j++) {
				if(printArr[i][j] != ' ')
					isEmpty = false;
			}
			
			if(!isEmpty)
				rows++;
			else
				stop = true;
		}
		
		printArr2 = new char[rows][numNodes];
		
		for (int i = 0; i < printArr2.length; i++) {
			for (int j = 0; j < printArr2[i].length; j++) {
				printArr2[i][j] = printArr[i][j];
			}
		}
		
		
		for (int i = 0; i < printArr2.length; i++) {
			for (int j = 0; j < printArr2[i].length; j++) {
				System.out.print(printArr2[i][j]);
			}
			System.out.println("");
		}
	}

	private void setCol(Node node) {
		if(node != null) {
			setCol(node.left);
			node.col = index;
			index++;
			setCol(node.right);
		}
	}
	
	private void breadthPrint(Node node) {
		setCol(node);
		
		Node v = null;
		Queue<Node> q = new LinkedList<Node>();

		if (node != null) {
			q.add(node);

			while (!q.isEmpty()) {	
				v = q.remove();

				breadthPrintHelper(v);
				
				for (int i = 0; i < printArr.length; i++) { 
					for (int j = 0; j < printArr[i].length; j++) 
						System.out.print(printArr[i][j]);
					System.out.println("");
			}
				
				
				if (v.left != null)
					q.add(v.left);

				if (v.right != null)
					q.add(v.right);	
			}		
		}
	}

	private void breadthPrintHelper(Node n) {
		int i = 0, 
			j = n.col,
			m, k, p;

		if (!isRoot) {
			while (i < printArr.length && printArr[i][j] != ',' ) {
				i++;
			}
		}
		else {
			isRoot = false;
		}
		
		printArr[i][j] = n.data;

		if (n.left != null) {
			for(m = 0, k = i, p = j; m < n.left.rightSpc; m++, k++, p--) {
				printArr[k+1][p-1] = '/';
			}
			printArr[k+1][p-1] = '/';
			printArr[k+2][p-1] = ',';
		}

		if (n.right != null) {
			for(m = 0, k = i, p = j; m < n.right.leftSpc; m++, k++, p++) {
				printArr[k+1][p+1] = '\\';
			}
			printArr[k+1][p+1] = '\\';
			printArr[k+2][p+1] = ',';
		}

	}


}
