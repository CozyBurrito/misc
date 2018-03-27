/**
 * Name: Mohammad Hameed
 * ID: 201597382
 * E-Mail: mhame382@mtroyal.ca
 * Assignment #: 3
 * Due Date: October 20th, 2015
 * Instructor: Charlie Hepler
 * Files: Main.java, Sort.java, Star.java
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	private Scanner inFile = null;
	private Star star = null;
	private Star stars[] = null;
	private Star starsMerge[] = null;
	private Sort sort = new Sort();
	private long startTime, endTime;
	private final static double EARTH_X = 0;
	private final static double EARTH_Y = 0;
	private final static double EARTH_Z = 0;

	public static void main(String[] args) {
		try {
			new Main().run();
		}
		catch (Exception e) {
			System.out.println("An Error Occured, Please Try Again");
			//e.printStackTrace();
		}
	}

	public void run() throws FileNotFoundException {
//Get input from user
		String csvFile = "stars.csv";
		int	numStars, m;
		Scanner userInput = new Scanner(System.in);

		System.out.print(".csv filename: ");
		csvFile = userInput.nextLine();	
		inFile = new Scanner(new File(csvFile));
	
		System.out.print("Number of Stars: ");
		numStars = userInput.nextInt();	

		System.out.print("M: ");
		m = userInput.nextInt();	
		
//Create the arrays
		starsMerge = new Star[m];
		stars = new Star[numStars];
		
//Get the stars form the file
		//Create 2 arrays: 1 for all the stars to be merge sorted
		//and 1 for the rest of the stars
		inFile.nextLine();
		//For the stars to be merge sorted
		for (int i = 0; i < m && inFile.hasNextLine(); i++)
			starsMerge[i] = getStarFromFile();
		
		//For the rest of the stars, with space for the merge sort stars 
		//to be copied into the beginning of the array
		for (int i = m; i < numStars && inFile.hasNextLine(); i++)
			stars[i] = getStarFromFile();
		
//Start sorting
		startTime = System.currentTimeMillis();
		
		//Merge sort the number of stars needed 
		starsMerge = sort.sortMerge(starsMerge);
		
		//Copy over the merge sorted stars
		for (int i = 0; i < starsMerge.length; i++)
			stars[i]  = starsMerge[i];
		
		//Check if bubble sort is needed
		if (m != numStars)
			stars = sort.sortBubble(stars);
		
//End Sorting
		endTime = System.currentTimeMillis();
		
		//Output details
		System.out.println("\nTime: " + (endTime - startTime) / 1000.00 + " second(s)");
		System.out.println("Comparisons " + sort.compares);
		System.out.println("Moves: " + sort.moves);
		System.out.println("Compares + Moves: " + (sort.moves + sort.compares));

//Close file streams
		inFile.close();
		userInput.close();
	}
	
	private Star getStarFromFile() {
		//Read in a line then split it 
		String line = inFile.nextLine();
		String[] starData = line.split(",");
	
		//Read all values in
		double ra  =  Double.parseDouble(starData[0]);
		double dec =  Double.parseDouble(starData[1]);
		double x   =  Double.parseDouble(starData[7]);
		double y   =  Double.parseDouble(starData[8]);
		double z   =  Double.parseDouble(starData[9]);
		double vx  =  Double.parseDouble(starData[10]);
		double vy  =  Double.parseDouble(starData[11]);
		double vz  =  Double.parseDouble(starData[12]);
		
		String proper = starData[2];	
		String spect  = starData[5];
		
		float ci;
		if (starData[6].isEmpty())
			ci = 0;
		else
			ci = Float.parseFloat(starData[6]);

		float mag    =  Float.parseFloat(starData[3]);
		float absmag =  Float.parseFloat(starData[4]);
		
		//Calculate distance from earth
		double distance = Math.sqrt(Math.pow(x - EARTH_X, 2) + 
				                    Math.pow(y - EARTH_Y, 2) + 
				                    Math.pow(z - EARTH_Z, 2));
		
		star = new Star(ra, dec, proper, mag, absmag, spect, ci, 
				x, y, z, vx, vy, vz, distance);

		return star;
	}

}

