import java.util.Scanner;

public class Replace {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		String stringToReplace = "";
		StringBuilder outPutString;
		char replacement = '\0';
		
		System.out.print("String: ");
		stringToReplace = input.nextLine();
		outPutString = new StringBuilder(stringToReplace);
		
		System.out.print("Replace with: ");
		replacement = input.nextLine().charAt(0);
		
		for (int i = 0; i < stringToReplace.length(); i++) {
			if(stringToReplace.charAt(i) != ' ') {
				outPutString.setCharAt(i, replacement);
			}
		}
		
		System.out.println("\n" + outPutString);
		
	}

}
