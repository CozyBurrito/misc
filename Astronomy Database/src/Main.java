/**
 * Name: Mohammad Hameed
 * ID: 201597382
 * E-Mail: mhame382@mtroyal.ca
 * Assignment #: 2
 * Due Date: October 5th, 2015
 * Instructor: Charlie Hepler
 * Files: Main.java, BinaryHashTable.java, Star.java
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public final static int STAR_BYTE_SIZE = 124;
	public final static int MAX_STRING_SIZE = 20;
	private Scanner inFile = null;
	private Star star = null;
	private BinaryHashTable table = null;

	public static void main(String[] args) {
		try {
			new Main().run();
		}
		catch (Exception e) {
			System.out.println("An Error Occured, Please Try Again");
		}
	}

	public void run() throws IOException {
		 
		String csvFile, binFileName, line; //line = the data to be searched
		String[] findStar = null;
		int hashSize, hashType;
		boolean exit = false;
		double ra, dec;
		Scanner userInput = new Scanner(System.in);

		//Get inputs from user, no "proper" error handling implemented
		System.out.print(".csv filename: ");
		csvFile = userInput.nextLine(); 
		inFile = new Scanner(new File(csvFile));

		System.out.print(".bin filename: ");
		binFileName = userInput.nextLine();

		System.out.print("Hashing type (1 = Linear/2 = Quadratic): ");
		hashType = Integer.parseInt(userInput.nextLine());

		System.out.print("Hash table size: ");
		hashSize = Integer.parseInt(userInput.nextLine());

		table = new BinaryHashTable(binFileName, hashType, hashSize);

		inFile.nextLine();
		for (int i = 0; i < hashSize && inFile.hasNextLine(); i++) {
			star = getStarFromFile();
			table.addToTable(star);
		}

		System.out.println("Collisions: " + table.coll);

		while (!exit) {
			System.out.print("Find star <ra, dec>/exit: ");
			line = userInput.nextLine();
			
			if (line.equals("exit"))
				exit = true;
			else {
				findStar = line.split(",");
				ra = Double.parseDouble(findStar[0]);
				dec = Double.parseDouble(findStar[1]);
				
				star = getStarFromBin(ra, dec);
				if (star != null)
					star.printStar();
				else
					System.out.println("Star not found!");
			}
				
		}
		
		table.destruct();
		inFile.close();
		userInput.close();
	}

	private Star getStarFromBin(double ra, double dec) throws IOException {
		star = table.getStar(ra, dec);
		boolean found = false;
		int i = 1;
		while (star != null && !found && i < table.coll)
		{
			if (star.ra == ra && star.dec == dec)
				found = true;
			else {
				table.probe();
				table.probed = true;
				star = table.getStar(ra, dec);
				i++;
			}
		}
		
		table.q = 1;
		table.probed = false;
		
		return star;

	}

	private Star getStarFromFile() {
		String line = inFile.nextLine();
		String[] starData = line.split(",");
		float ci;

		double ra  =  Double.parseDouble(starData[0]);
		double dec =  Double.parseDouble(starData[1]);
		double x   =  Double.parseDouble(starData[7]);
		double y   =  Double.parseDouble(starData[8]);
		double z   =  Double.parseDouble(starData[9]);
		double vx  =  Double.parseDouble(starData[10]);
		double vy  =  Double.parseDouble(starData[11]);
		double vz  =  Double.parseDouble(starData[12]);

		String proper = setStringSize(starData[2]);	
		String spect  = setStringSize(starData[5]);
		
		if (starData[6].isEmpty())
			ci = 0;
		else
			ci = Float.parseFloat(starData[6]);

		float mag    =  Float.parseFloat(starData[3]);
		float absmag =  Float.parseFloat(starData[4]);

		star = new Star(ra, dec, proper, mag, absmag, spect, ci, 
				x, y, z, vx, vy, vz);

		return star;
	}

	private String setStringSize(String var) {
		if (var.length() > MAX_STRING_SIZE) {
			var = var.substring(0, MAX_STRING_SIZE);
		}

		var = String.format("%20s", var);

		return var;
	}

}






