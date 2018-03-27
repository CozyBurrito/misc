package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day13 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("input.txt"));
		int[] guests = new int[8];
		int index;
		String person = input.next();

		switch (person){
		case "Alice":	index = 0;
		break;
		case "Bob":		index = 1;
		break;
		case "Carol":	index = 2;
		break;
		case "David":	index = 3;
		break;
		case "Eric":	index = 4;
		break;
		case "Frank":	index = 5;
		break;
		case "George":	index = 6;
		break;
		case "Mallory":	index = 7;
		break;
		}


		
	}

}
