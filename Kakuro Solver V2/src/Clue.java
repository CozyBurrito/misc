import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
		ArrayList<Character> list = new ArrayList<Character>();
		calcPosVals(cayleyTable, dataSet, clueSpacesCor.size(), list);

		for (ArrayList<Character> i : posClueVals) {
			System.out.println(Arrays.toString(i.toArray()));
		}
		
		for (ArrayList<Character> i: posClueVals) {
			for (int j = 0; j < i.size(); j++) {
				ArrayList<Integer> spaces = clueSpacesCor.get(j);
				grid.getCell(spaces.get(0), spaces.get(1)).addPosVals(i.get(j));
			}
		}

	}

	private void calcPosVals(char[][] cayleyTable, char[] dataSet, int size, ArrayList<Character> list) {
		if (list.size() == size) {
			if (isListValid(list, cayleyTable)) {	
				ArrayList<Character> vals = new ArrayList<Character>();
				for(char ch: list)
					vals.add(ch);
				
				posClueVals.add(vals);
				
				
			}
		}
		else {
			for (char ch: dataSet) {
				if (!list.contains(ch)) {
					list.add(ch);
					calcPosVals(cayleyTable, dataSet, clueSpacesCor.size(), list);
					list.remove(list.size()-1);
				}
				
			}
		}

	}

	private boolean isListValid(ArrayList<Character> list, char[][] cayleyTable) {
		boolean isValid = false;

		char h;
		int j = 0, k = 0;

		while(list.get(0) != cayleyTable[0][j]) j++;
		while(list.get(1) != cayleyTable[k][0]) k++;
		h = cayleyTable[k][j];

		for (int i = 2; i < list.size(); i++) {
			j = 0;
			k = 0;
			while(h != cayleyTable[0][j]) j++;
			while(list.get(i) != cayleyTable[k][0]) k++;

			h = cayleyTable[k][j];
		}

		if (h == clueVal)
			isValid = true;

		return isValid;
	}


	public void addSpaceCor(int row, int col) {
		ArrayList<Integer> cor = new ArrayList<Integer>();
		cor.add(row);
		cor.add(col);

		clueSpacesCor.add(cor);
	}

	public void printClueSpaces() {
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