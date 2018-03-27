import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {	
	private Scanner input = null;
	private Integer[] dataInit = null;
	private char[] dataIn = null;
	private char[][] table = null;
	private char[][] gridIn = null;
	private Grid grid = null;
	
	
	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void run() throws FileNotFoundException {
		input = new Scanner(new File("test.txt"));
		
		int numData = input.nextInt();
		initData(numData);	
		dataIn = new char[numData];	
		getData();
		readTable(numData);
		
		List<Integer> data = Arrays.asList(dataInit);
		
		numData = input.nextInt();
		initData(numData);
		dataIn = new char[numData];	
		getData();
		data = Arrays.asList(dataInit);
		readGrid();
	
		grid = new Grid(numData, numData);
		grid.setGrid(gridIn);
		grid.printGrid();
		
	}
	
	private void readGrid() {
		int numData = input.nextInt();
		gridIn = new char[numData][numData];
		String[] line;
		input.nextLine();
		
		for (int i = 0; i < gridIn.length; i++) {
			line = input.nextLine().split(" ");
			for (int j = 0; j < gridIn.length; j++) {
				gridIn[i][j] = line[j].charAt(0);
			}
		}
	}
	
	private void readTable(int numData) {
		table = new char[numData][numData];
		String[] line;
		input.nextLine();
		
		for (int i = 0; i < table.length; i++) {
			line = input.nextLine().split(" ");
			for (int j = 0; j < table.length; j++) {
				table[i][j] = line[j].charAt(0);
			}
		}
	}
	
	private void initData(int numData) {
		dataInit = new Integer[numData];
		for (int i = 0; i < numData; i++) {
			dataInit[i] = i+1;
		}
	}

	private void getData() {
		input.nextLine();
		String[] line = input.nextLine().split(" ");
		
		for (int i = 0; i < dataIn.length; i++) {
			dataIn[i] = line[i].charAt(0);
		}
	}
}
