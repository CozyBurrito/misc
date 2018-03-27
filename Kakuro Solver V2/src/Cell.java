import java.util.ArrayList;

public class Cell {
	private int row;
	private int col;
	private char val;
	private boolean canPutVal;
	private Clue horizontalClue = null;
	private Clue verticalClue = null;
	private ArrayList<Character> posVals = new ArrayList<Character>();

	public Cell(int row, int col, boolean canPutVal) {
		this.row = row;
		this.col = col;
		this.canPutVal = canPutVal;
	}

	public void setVal(char val) {
		this.val = val;
	}
	
	public boolean canPutVal() {
		return canPutVal;
	}

	public char getVal() {
		return val;
	}
	
	public void addClue(Clue clue, Grid grid, char[][] cayleyTable, char[] dataSet) {
		calcClueSpaces(clue, grid);
		clue.printClueSpaces();
		
		clue.calcClueSet(cayleyTable, dataSet, grid);
		
		if (clue.getClueDirection())
			horizontalClue = clue;
		else
			verticalClue = clue;
	}

	private void calcClueSpaces(Clue clue, Grid grid) {
		int row = this.row;
		int col = this.col;
		
		if (clue.getClueDirection()) {
			while (grid.getCell(row, ++col).canPutVal) {
				clue.addSpaceCor(row, col);
			}
		}
		else {
			while (grid.getCell(++row, col).canPutVal) {
				clue.addSpaceCor(row, col);
			}
		}
		
	}

	public Clue getClue(boolean direction) {
		return (direction) ? horizontalClue : verticalClue;
	}

	public void addPosVals(char val) {
		if (!posVals.contains(val))
			posVals.add(val);
	}
	
	public void removePosVals(char val) {
		if (posVals.contains(val))
			posVals.remove(val);
	}
	
	public void setPosVals(ArrayList<Character> list) {
		this.posVals = list;
	}
	
	public char getPosVals(int i) {
		return posVals.get(i);
	}
	
	public ArrayList<Character> getPosValsList() {
		return posVals;
	}
	
	public void printCell() {
		System.out.print(row + ", " + col + " -");
		
		for (char i : posVals)
			System.out.print(" " + i);
		
		System.out.println("");
	}

}
