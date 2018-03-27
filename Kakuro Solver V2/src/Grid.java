public class Grid {
	private Cell[][] grid = null;
	private int size;

	public Grid(int rows, int cols) {
		this.grid = new Cell[rows][cols];
		this.size = rows;
	}

	public int size() {
		return size;
	}
	
	public void setGrid(char[][] gridIn) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (gridIn[i][j] == 'X')
					grid[i][j] = new Cell(i,j,false);
				else
					grid[i][j] = new Cell(i,j,true);
			}
		}
	}
	
	public void print() {
		System.out.print(" ");
		for (int i = 0; i < grid.length; i++) {
			System.out.print(" " + i);
		}
		System.out.println("");
		
		int k = 0;
		for (Cell[] i : grid) {
			System.out.print(k + " ");
			k++;
			for (Cell j : i) {
				if (j.canPutVal())
					System.out.print(j.getVal());
				else
					System.out.print('X');
				
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
	public void printPosCellVals() {
		for (Cell[] i : grid) {
			for (Cell j : i) {
				if (j.canPutVal()) {
					j.printCell();
				}
			}
		}
	}
	
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}
	
}
