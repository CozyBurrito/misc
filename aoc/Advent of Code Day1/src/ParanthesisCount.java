import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParanthesisCount {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner input = new Scanner(new File("input.txt"));
		String in = input.next();
		int floor = 0;
		
		for (int i = 0; i < in.length(); i++) {
			if (in.charAt(i) == '(')
				floor++;
			else if (in.charAt(i) == ')')
				floor--;
		}	
		
		System.out.println("Floors: " + floor);
	}

}
