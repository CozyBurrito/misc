import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
		input = new Scanner(new File("Big.txt"));

		int cayleyTableSize = input.nextInt();

		cayleySet = new char[cayleyTableSize];
		readCayleySet(cayleyTableSize);

		cayleyTable = new char[cayleyTableSize][cayleyTableSize];
		readCayleyTable(cayleyTableSize);

		int dataSetSize = input.nextInt();
		dataSet = new char[dataSetSize];
		readDataSet(dataSetSize);

		int gridSize = input.nextInt();
		grid = new Grid(gridSize, gridSize);
		grid.setGrid(readGrid(gridSize, gridSize));

		int numClues = input.nextInt();
		readClues(numClues);


		System.out.println("");

		grid.printPosCellVals();

		System.out.println("");


		long s = System.nanoTime();

		//solve(0,0);

		long e = System.nanoTime();

		double d = (e - s)/1e6;

		System.out.println(d);

	}

	private void solve(int i, int j) {	
		Cell cell = null;

		if (j == grid.size()) {
			i++;
			j = 0;
		}

		if (i < grid.size())
			cell = grid.getCell(i, j);

		if (i == grid.size()) {	
			if (isValid()) {
				grid.print();
				System.out.println("");
			}

		}
		else if (!cell.canPutVal()) {
			solve(i,j+1);
		}
		else {
			for (int k = 0; k < cell.getPosValsList().size(); k++) {
				cell.setVal(cell.getPosVals(k));
				solve(i,j+1);
			}
		}



	}
	
	private boolean isValid() {
		boolean isVal = true;

		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.size(); j++) {
				Clue h = grid.getCell(i, j).getClue(true);
				Clue v = grid.getCell(i, j).getClue(false);

				if (h != null) {
					ArrayList<ArrayList<Integer>> clueSpacesCor = h.getClueSpacesCor();
					ArrayList<Character> vals = new ArrayList<Character>();

					for (ArrayList<Integer> k: clueSpacesCor) {
						vals.add(grid.getCell(k.get(0), k.get(1)).getVal());
					}

					if(!clueIsValid(vals, cayleyTable, h.getClueVal()))
						isVal = false;
				}

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
		boolean isValid = false;
		char h;
		int j = 0, k = 0;

		while(vals.get(0) != cayleyTable[0][j]) j++;
		while(vals.get(1) != cayleyTable[k][0]) k++;
		h = cayleyTable[k][j];

		for (int i = 2; i < vals.size(); i++) {
			j = 0;
			k = 0;
			while(h != cayleyTable[0][j]) j++;
			while(vals.get(i) != cayleyTable[k][0]) k++;

			h = cayleyTable[k][j];
		}

		if (h == clueVal)
			isValid = true;

		return isValid;
	}

	private void readCayleySet(int cayleyTableSize) {
		String[] line = null;
		input.nextLine();

		line = input.nextLine().split(" ");

		for (int i = 0; i < line.length; i++) {
			cayleySet[i] = line[i].charAt(0);
		}

		input.nextLine();
	}

	private void readCayleyTable(int cayleyTableSize) {
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
		input.nextLine();
		String[] line = null;
		line = input.nextLine().split(" ");

		for (int i = 0; i < line.length; i++) {
			dataSet[i] = line[i].charAt(0);
		}
		input.nextLine();
	}

	private char[][] readGrid(int rows, int cols) {
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
		int row, col;
		char val;
		boolean direction;
		Clue clue = null;
		String[] line = null;
		input.nextLine();

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

			grid.getCell(row, col).addClue(clue, grid, cayleyTable, dataSet);
		}
		input.nextLine();
	}



}

