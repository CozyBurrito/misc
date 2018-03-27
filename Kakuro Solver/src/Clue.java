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
import java.util.Arrays;

public class Clue {
	private boolean clueDirection;
	private char clueVal;
	private ArrayList<ArrayList<Integer>> clueSpacesCor = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Character>> posClueVals = new ArrayList<ArrayList<Character>>();
	
	public Clue(boolean clueDirection, char clueVal) {
		this.clueDirection = clueDirection;
		this.clueVal = clueVal;
	}

	public char getClueVal() {
		return clueVal;
	}

	public boolean getClueDirection() {
		return clueDirection;
	}

	public ArrayList<ArrayList<Integer>> getClueSpacesCor() {
		return clueSpacesCor;
	}

	public void calcClueSet(char[][] cayleyTable, char[] dataSet, Grid grid) {
		//calculate the set of possible sets for this clue to be satisfied
		ArrayList<Character> list = new ArrayList<Character>();
		calcPosVals(cayleyTable, dataSet, clueSpacesCor.size(), list);

		for (ArrayList<Character> i : posClueVals) {
			System.out.println(Arrays.toString(i.toArray()));
		}
		
		//add each of the values to the list of possible values for each cell in this clues spaces
		for (ArrayList<Character> i: posClueVals) {
			for (int j = 0; j < i.size(); j++) {
				ArrayList<Integer> spaces = clueSpacesCor.get(j);
				//add to possible values for that cell
				grid.getCell(spaces.get(0), spaces.get(1)).addPosVals(i.get(j));
			}
		}

	}

	private void calcPosVals(char[][] cayleyTable, char[] dataSet, int size, ArrayList<Character> list) {
		//recursively find each list that can satisfy the clue
		if (list.size() == size) {
			//if the list is size of spaces for clue
			if (isListValid(list, cayleyTable)) {	
				//if it is then add it to the list of possible lists for this clue
				ArrayList<Character> vals = new ArrayList<Character>();
				for(char ch: list)
					vals.add(ch);
				
				posClueVals.add(vals);
			}
		}
		else {
			//try each element in the data set
			for (char ch: dataSet) {
				//try adding to avoid duplicates
				if (!list.contains(ch)) {
					//if can add try adding another value to the list
					list.add(ch);
					calcPosVals(cayleyTable, dataSet, clueSpacesCor.size(), list);
					//try another value
					list.remove(list.size()-1);
				}
				
			}
		}

	}

	private boolean isListValid(ArrayList<Character> list, char[][] cayleyTable) {
		//check if the given list satisfies the clue
		boolean isValid = false;

		char h;
		int j = 0, k = 0;

		//get the first two 
		while(list.get(0) != cayleyTable[0][j]) j++;
		while(list.get(1) != cayleyTable[k][0]) k++;
		h = cayleyTable[k][j];

		//try the rest of the list
		for (int i = 2; i < list.size(); i++) {
			j = 0;
			k = 0;
			while(h != cayleyTable[0][j]) j++;
			while(list.get(i) != cayleyTable[k][0]) k++;

			h = cayleyTable[k][j];
		}

		//if the final element is the same as this clues value
		if (h == clueVal)
			isValid = true;

		return isValid;
	}


	public void addSpaceCor(int row, int col) {
		//add a coordinate for a blank space into this clues blank spaces list
		ArrayList<Integer> cor = new ArrayList<Integer>();
		cor.add(row);
		cor.add(col);

		clueSpacesCor.add(cor);
	}

	public void printClueSpaces() {
		//print the list of blank spaces for this clue  
		System.out.print(clueVal + " : ");

		if (clueDirection) 
			System.out.print("> ");
		else
			System.out.print("V ");

		for (ArrayList<Integer> i : clueSpacesCor) {
			System.out.print(Arrays.toString(i.toArray()));
		}

		System.out.println("");

	}

}