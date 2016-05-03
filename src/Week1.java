import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//This is a challenge I'm setting up for myself where each week I will find the most optimal way to sort a different set of data.
//It is based off of the University of Minnesota, Morris' Sorting Competition that takes place in Algorithms & Computing.

//						The schedule may change based on how short/fast it takes for each one.

//The timing system is done only in the actual sorting, so extra loads in pre-processing do not count towards the time.

//Week 1: Sorting ints ranked on 3 things, sorted in the given order:
//  1 - Variance of digits (some kind of ranking system put in pre-processing)
//  2 - Sum of digits
//  3 - Numeric Value in each section.

//NOTES TO SELF
//  TODO figure out the "variance ranking" and how it works. LENGTH IS IMPORTANT CAUSE A 99 ISN'T VARIED BUT ONLY HAS ONE 2 AND 0'S
//		some kind of average???

public class Week1 {
	public static void main(String args[]) {

		int[] goodVar1 = countDigits(1234);
		int[] goodVar2 = countDigits(1234560789);
		int[] badVar1 = countDigits(9999);
		int[] badVar2 = countDigits(1);
		int[] badVar3 = countDigits(444667722);
		int[] badVar4 = countDigits(11);
		
		System.out.println("Good: " + Arrays.toString(goodVar1) + " avg: " + avrg(goodVar1));
		System.out.println("Good: " + Arrays.toString(goodVar2) + " avg: " + avrg(goodVar2));
		System.out.println("Bad: " + Arrays.toString(badVar1) + " avg: " + avrg(badVar1));
		System.out.println("Bad: " + Arrays.toString(badVar2) + " avg: " + avrg(badVar2));
		System.out.println("Bad: " + Arrays.toString(badVar4) + " avg: " + avrg(badVar4));
		System.out.println("Bad: " + Arrays.toString(badVar3) + " avg: " + avrg(badVar3));

	}
	
	public static int avrg(int[] arr) {
		//This is being used for testing purposes to decide how the "variance ranking" works.
		double sum = 0;
		int countZero = 1;
		for (int d : arr) {
			sum += d;
			if (d == 0) {
				countZero++;
			}
		}
		double avg = sum / arr.length / countZero;
		int p = (int) (avg * 100);
		return p;
	}
	
	public static void generateFile(int amt, int min, int max, int percentRpts) {
		//generates a file with /amt/ integers, ranging from /min/ to /max/ with a /percentRpts/ chance for repeats.

	}
	
	public static int[] countDigits(int x) {
		//countDigits takes in an int /x/ and returns an array sort of like the counting array in count sort.
		//the loop takes the remainder of /x/ divided by 10, returning the first digit. It increments that index of counter by one.
		// then it divides by 10 and starts over, going and going until x becomes a decimal (all the digits have been counted).
		int[] counter = new int[10];
		if (x == 0) counter[0]++;
		while(x >= 1) {
			int digit = x%10;
			counter[digit]++;
			x = x/10;
		}
		return counter;
	}
	
	public static void readData(String fileName) {
		//Reads each line in a file as a string, adds it to the ArrayList.
		//Returns as an array to be sorted.
		ArrayList<Integer> input = new ArrayList<Integer>();
		Scanner scan;
		try {
			scan = new Scanner(new File(fileName));
			while(scan.hasNext()) {
				Integer next = scan.nextInt();
				input.add(next);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
