import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
	
	public class Cor {
		public int x = 0;
		public int y = 0;
		
		public Cor(int in_x, int in_y) {
			x = in_x;
			y = in_y;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new Day3().run();
	}
	
	public void run() throws FileNotFoundException {
		Scanner input = new Scanner(new File("input.txt"));
		String in = input.next();
		
		boolean found = false;
		ArrayList<Cor> list = new ArrayList<>();
		int x = 0, y = 0;
		
		Cor temp = null;
		Cor santa = new Cor(x, y);
		list.add(new Cor(x, y));

		for (int i = 0; i < in.length(); i++) {			
			if (in.charAt(i) == '^')
				y++;
			else if (in.charAt(i) == 'v')
				y--;
			else if (in.charAt(i) == '>')
				x++;
			else
				x--;
			
			for (int j = 0; j < list.size() && !found; j++) {
				temp = list.get(j);
				if(x == temp.x && y == temp.y)
					found = true;
			}
			
			if (!found)
				list.add(new Cor(x, y));
			else
				found = false;
			
		}
		
		System.out.println("Count: " + list.size());
	}

}
