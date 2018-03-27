/**
 * Name: Mohammad Hameed
 * Email: mhame382@mtroyal.ca
 * Instructor: Charlie Hepler
 * Assignment: 01
 * Files: Kakuro.java Grid.java Cell.java Clue.java
 * Due Date: 1/2/2016
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kakuro {

	private char[] cayleySet = null;
	private char[][] cayleyTable = null;
	private char[] dataSet = null;

	private Grid grid = null;
	private Scanner input = null;

	public static void main(String[] args) {
		try {
			new Kakuro().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void run() throws FileNotFoundException {
		//Get and process the input
		input = new Scanner(new File("Small.txt"));

		int cayleyTableSize = input.nextInt();

		cayleySet = new char[cayleyTableSize];
		readCayleySet(cayleyTableSize);

		cayleyTable = new char[cayleyTableSize][cayleyTableSize];
		readCayleyTable(cayleyTableSize);

		int dataSetSize = input.nextInt();
		dataSet = new char[dataSetSize];
		readDataSet(dataSetSize);

		//read the grid and process it
		int gridSize = input.nextInt();
		grid = new Grid(gridSize, gridSize);
		grid.setGrid(readGrid(gridSize, gridSize));

		int numClues = input.nextInt();
		readClues(numClues);

		//print possible cell values
		System.out.println("");

		grid.printPosCellVals();

		System.out.println("");

		//Solve the puzzle
		solve(0,0);

	}

	private void solve(int i, int j) {	
		//For each cell set its value to one of its possible values and then do the same for the next cell
		Cell cell = null;

		//For out of bounds exception/traversing the grid
		if (j == grid.size()) {
			i++;
			j = 0;
		}

		if (i < grid.size())
			cell = grid.getCell(i, j);

		//3 main cases
		if (i == grid.size()) {	
			//if each blank cell has been set a value 
			if (isValid()) {
				grid.print();
				System.out.println("");
			}

		}
		else if (!cell.canPutVal()) {
			//if cannot put a value in the cell
			solve(i,j+1);
		}
		else {
			//try one of the possible cell values and move onto the next one
			for (int k = 0; k < cell.getPosValsList().size(); k++) {
				cell.setVal(cell.getPosVals(k));
				solve(i,j+1);
			}
		}

	}
	
	private boolean isValid() {
		//Check if each clue is value 
		
		boolean isVal = true;

		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.size(); j++) {
				//Check for both clues
				Clue h = grid.getCell(i, j).getClue(true);
				Clue v = grid.getCell(i, j).getClue(false);

				if (h != null) {
					//check the horizontal clue
					//get each of the clues spaces
					ArrayList<ArrayList<Integer>> clueSpacesCor = h.getClueSpacesCor();
					ArrayList<Character> vals = new ArrayList<Character>();
					
					//add all the values of those space to a list and check if it the clue is valid
					for (ArrayList<Integer> k: clueSpacesCor) {
						vals.add(grid.getCell(k.get(0), k.get(1)).getVal());
					}
					
					//if it is valid or not
					if(!clueIsValid(vals, cayleyTable, h.getClueVal()))
						isVal = false;
				}

				//same as previous 'if' but for vertical instead
				if (v != null) {
					ArrayList<ArrayList<Integer>> clueSpacesCor = v.getClueSpacesCor();
					ArrayList<Character> vals = new ArrayList<Character>();

					for (ArrayList<Integer> k: clueSpacesCor) {
						vals.add(grid.getCell(k.get(0), k.get(1)).getVal());
					}

					if(!clueIsValid(vals, cayleyTable, v.getClueVal()))
						isVal = false;
				}

			}
		}

		return isVal;
	}

	private boolean clueIsValid(ArrayList<Character> vals, char[][] cayleyTable, char clueVal) {
		//using the cayley table check if the clue is satisfied
		boolean isValid = false;
		char h;
		int j = 0, k = 0;

		//get the first two 
		while(vals.get(0) != cayleyTable[0][j]) j++;
		while(vals.get(1) != cayleyTable[k][0]) k++;
		h = cayleyTable[k][j];

		//check the rest of the list
		for (int i = 2; i < vals.size(); i++) {
			j = 0;
			k = 0;
			while(h != cayleyTable[0][j]) j++;
			while(vals.get(i) != cayleyTable[k][0]) k++;

			h = cayleyTable[k][j];
		}
		
		//check if satisfied
		if (h == clueVal)
			isValid = true;

		return isValid;
	}

	private void readCayleySet(int cayleyTableSize) {
		//read the input for the cayley table set
		String[] line = null;
		input.nextLine();

		line = input.nextLine().split(" ");

		for (int i = 0; i < line.length; i++) {
			cayleySet[i] = line[i].charAt(0);
		}

		input.nextLine();
	}

	private void readCayleyTable(int cayleyTableSize) {
		//read the input for the cayley table 
		String[] line = null;

		for (int i = 0; i < cayleyTableSize; i++) {
			line = input.nextLine().split(" ");
			for (int j = 0; j < cayleyTableSize; j++) {
				cayleyTable[i][j] = line[j].charAt(0);
			}
		}
		input.nextLine();
	}

	private void readDataSet(int dataSetSize) {
		//read the input for the set of possible cell values
		input.nextLine();
		String[] line = null;
		line = input.nextLine().split(" ");

		for (int i = 0; i < line.length; i++) {
			dataSet[i] = line[i].charAt(0);
		}
		input.nextLine();
	}

	private char[][] readGrid(int rows, int cols) {
		//read the input for the grid
		input.nextLine();
		String[] line;
		char[][] grid = new char[rows][cols];

		for (int i = 0; i < grid.length; i++) {
			line = input.nextLine().split(" ");
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = line[j].charAt(0);
			}
		}
		input.nextLine();
		return grid;
	}

	private void readClues(int numClues) {
		//read the input for the clues
		int row, col;
		char val;
		boolean direction;
		Clue clue = null;
		String[] line = null;
		input.nextLine();

		//for each clue add it to its respective cell
		for (int i = 0; i < numClues; i++) {
			line = input.nextLine().split(" ");

			col = Integer.parseInt(line[0]);
			row = Integer.parseInt(line[1]);
			direction = (line[2].charAt(0) == '>') ? true : false;
			val = line[3].charAt(0);

			if (direction) 
				col--; 
			else 
				row--;

			clue = new Clue(direction, val);

			//also solves the possible values for the clue
			grid.getCell(row, col).addClue(clue, grid, cayleyTable, dataSet);
		}
		
		input.nextLine();
	}



}

