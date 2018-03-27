/**
 * Name: Mohammad Hameed
 * Email: mhame382@mtroyal.ca
 * Instructor: Charlie Hepler
 * Assignment: 01
 * Files: Kakuro.java Grid.java Cell.java Clue.java
 * Due Date: 1/2/2016
 * 
 */

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
		//add a clue by first calculating its spaces
		calcClueSpaces(clue, grid);
		clue.printClueSpaces();
		
		//calculate that clues possible sets
		clue.calcClueSet(cayleyTable, dataSet, grid);
		
		if (clue.getClueDirection())
			horizontalClue = clue;
		else
			verticalClue = clue;
	}

	private void calcClueSpaces(Clue clue, Grid grid) {
		//go through the grid and find it horizontal or vertical spaces
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
		//add a value to the possible values list for this cell
		if (!posVals.contains(val))
			posVals.add(val);
	}
	
	public void removePosVals(char val) {
		//remove a value from the possible values list for this cell
		if (posVals.contains(val))
			posVals.remove(val);
	}
	
	public void setPosVals(ArrayList<Character> list) {
		//set this cells possible values list to another list
		//not fully used in this version
		this.posVals = list;
	}
	
	public char getPosVals(int i) {
		//get a certain value in the possible values list
		return posVals.get(i);
	}
	
	public ArrayList<Character> getPosValsList() {
		//return the whole possible values list
		return posVals;
	}
	
	public void printCell() {
		//print this cells values
		System.out.print(row + ", " + col + " -");
		
		for (char i : posVals)
			System.out.print(" " + i);
		
		System.out.println("");
	}

}
