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
/* Algorithms Used:
 * 
 * Merge Sort: http://www.vogella.com/tutorials/JavaAlgorithmsMergesort
 * 					/article.html
 * 
 * Bubble Sort: http://www.programmingsimplified.com/java/source-code
 * 					 /java-program-to-bubble-sort
 * 
 */
public class Sort {

	private Star[] starsMerge;
	private Star[] helper;
	public int moves = 0, compares = 0;

	//Constructor
	public Sort() {
		this.starsMerge = null;
		this.helper = null;
	}
	
	//Bubble Sort
	public Star[] sortBubble(Star[] values) {
		Star swap;
		for (int i = 0; i < values.length - 1; i++) {
			for (int j = 0; j < values.length - i - 1; j++) {
				if (values[j].distance > values[j+1].distance) 
				{
					swap = values[j];
					values[j] = values[j+1];
					values[j+1] = swap;
					moves++;
				}
				compares++;
			}
		}

		return values;  
	}

	//Merge Sort
	public Star[] sortMerge(Star[] values) {
		this.starsMerge = values;
		this.helper = new Star[values.length];
		mergesort(0, values.length - 1);
		return starsMerge;
	}

	private void mergesort(int low, int high) {
		// check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			mergesort(low, middle);
			// Sort the right side of the array
			mergesort(middle + 1, high);
			// Combine them both
			merge(low, middle, high);
		}
	}

	private void merge(int low, int middle, int high) {

		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			helper[i] = starsMerge[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (helper[i].distance <= helper[j].distance) {
				starsMerge[k] = helper[i];
				i++;
			} else {
				starsMerge[k] = helper[j];
				j++;
			}
			k++;
			compares++;
			moves++;
		}
		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			starsMerge[k] = helper[i];
			k++;
			i++;
			moves++;
		}
		
	}


} 