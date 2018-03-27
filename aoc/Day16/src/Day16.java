import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day16 {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File("input.txt"));
		int curr = 0;
		String in;
		int num;
		boolean v1 = false, v2 = false, v3 = false;
		
		for (int i = 0; i < 500; i++) {
			input.next();
			input.next();
			
			in = input.next();
			num = Integer.parseInt(input.next().replace(',', ' ').trim());
			System.out.println(in + " " + num);
			v1 = valid(in, num);
			
			in = input.next();
			num = Integer.parseInt(input.next().replace(',', ' ').trim());
			System.out.println(in + " " + num);
			v2 = valid(in, num);
			
			in = input.next();
			num = Integer.parseInt(input.next().replace(',', ' ').trim());
			System.out.println(in + " " + num);
			v3 = valid(in, num);
			
			if (v1 && v2 && v3)
				curr = i;
			
			
		}
		
		input.close();
		
	}
	
	public static boolean valid(String in, int num) {
		boolean isValid = false;
		int[] Sue = {3, 7, 2, 3, 0, 0, 5, 3, 2, 1};
		int i;
		
		if (in == "children:")
			i = 0;
		else if (in == "cats:")
			i = 1;
		else if (in == "samoyeds:")
			i = 2;
		else if (in == "pomeranians:")
			i = 3;
		else if (in == "akitas:")
			i = 4;
		else if (in == "vizslas:")
			i = 5;
		else if (in == "goldfish:")
			i = 6;
		else if (in == "trees:")
			i = 7;
		else if (in == "cars:")
			i = 8;
		else if (in == "perfumes:")
			i = 9;
		else
			i = -1;
			
		if (i != -1 && Sue[i] == num)
			isValid = true;
		
		return isValid;
	}

}
