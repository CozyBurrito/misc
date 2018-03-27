import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

	public static void main(String[] args) {
		try {
			new Day2().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws FileNotFoundException {
		Scanner input = new Scanner(new File("input.txt"));
		String[] in;
		int total = 0, slack = 0,
			l = 0, w = 0, h = 0,
			lw = 0, wh = 0, hl = 0;
		
		while (input.hasNextLine()) {
			in = input.nextLine().split("x");	
			
			l = Integer.parseInt(in[0]);
			w = Integer.parseInt(in[1]); 
			h = Integer.parseInt(in[2]);
			
			lw = l * w;
			wh = w * h;
			hl = h * l;
			
			if (lw <= wh && lw <= hl)
				slack = lw;
			else if (wh <= lw && wh <= hl)
				slack = wh;
			else
				slack = hl;
			
			total += ((2 * lw) + (2 * wh) + (2 * hl)) + slack;
		}
		
		System.out.println("Total: " + total);
		
	}

}
