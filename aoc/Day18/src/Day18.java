import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18 {
	private ArrayList<Boolean> list = new ArrayList<Boolean>();
	private char[][] lights = new char[100][100];
	private char[][] newLights = new char[100][100];

	public static void main(String[] args) throws FileNotFoundException {
		new Day18().run();
	}

	private void run() throws FileNotFoundException {
		Scanner input = new Scanner(new File("input.txt"));
		char[] in = input.nextLine().toCharArray();
		int count = 0;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++)
				lights[i][j] = in[j];

			if (input.hasNext())
				in = input.nextLine().toCharArray();		
		}

		lights[99][0] = '#';

		for (int i = 0; i < 100; i++) {
			proc();
		}


		for (int i = 0; i < lights.length; i++) {
			for (int j = 0; j < lights.length; j++) {
				if (lights[i][j] == '#')
					count++;
			}
		}

		System.out.println(count);



	}

	private void proc() {
		for (int i = 0; i < lights.length; i++) {
			for (int j = 0; j < lights.length; j++) {
				if ((i != 0 && j != 0) ||
					(i != 0 && j != 99) ||
					(i != 99 && j != 0) ||
					(i != 99 && j != 99))
					procHelper(i, j);
			}

		}

		for (int k = 0; k < lights.length; k++) {
			for (int k2 = 0; k2 < lights.length; k2++) {
				lights[k][k2] = newLights[k][k2];
			}
		}


	}

	private void procHelper(int i, int j) {
		int x, y;

		x = i - 1;
		y = j - 1;
		check(x, y);

		x = i - 1;
		y = j;
		check(x, y);

		x = i - 1;
		y = j + 1;
		check(x, y);

		x = i;
		y = j - 1;
		check(x, y);

		x = i;
		y = j + 1;
		check(x, y);

		x = i + 1;
		y = j - 1;
		check(x, y);

		x = i + 1;
		y = j;
		check(x, y);

		x = i + 1;
		y = j + 1;
		check(x, y);

		if (lights[i][j] == '#') {
			if (list.size() == 2 || list.size() == 3)
				newLights[i][j] = '#';
			else
				newLights[i][j] = '.';

		}
		else {
			if (list.size() == 3)
				newLights[i][j] = '#';
			else
				newLights[i][j] = '.';
		}

		list.clear();

	}

	private void check(int x, int y) {
		if (((x >= 0 && x < lights.length) && (y >= 0 && y < lights.length)) && lights[x][y] == '#')
			list.add(true);
	}


	private void print(char[][] in) {
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in.length; j++)
				System.out.print(in[i][j]);

			System.out.println("");
		}
	}

}
