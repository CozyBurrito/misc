import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int n = 1;
		
		while(System.in.available() == 0) {
			System.out.printf("  ");
			System.out.printf("\r%d", n);
			n = (n % 10) + 1;
			
		}		
		
		Scanner in = new Scanner(System.in);
		in.nextLine();
		
		in.close();
		
	}

}
