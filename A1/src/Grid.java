import java.util.Arrays;

public class Grid {
	private Cell[][] grid = null;

	public Grid(int rows, int cols) {
		this.grid = new Cell[rows][cols];
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
	
	public void printGrid() {
		for (Cell[] i : grid) {
			
		}
	}
	
	
}
