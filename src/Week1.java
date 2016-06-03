import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

//This is based off of the University of Minnesota, Morris' Sorting Competition that takes place in Algorithms & Computing.

//I will be sorting ints in three different ways, one after the other.
// 1 - Variance of the digits in the int
// 2 - Sum of the digits
// 3 - Numeric value

//I generate a file of ints with a controllable ratio of repeats and convert them into an object I created myself, SuperInts.
//I will time how long it takes to sort them using different sorting algorithms and analyze the times.
// - Predictions will also be made before I actually test the sorting and will be returned to afterwards.
//The sorted data will be then put into a file specified.


public class Week1 {
	public static Random rand = new Random();
	public static String f = "randomgen.txt";
	//min and max are set unrealistically high/low so they are most likely immediately set to a value in the data set.
	public static int min = 2147483647;
	public static int max = 0;
	
	public static void main(String args[]) throws IOException {
		generateFile(100, 0, 1000000000, 50, f);
		SuperInt[] data = readData(f);
//		insertionSort(data, "var");
		quickSort(data, 0, data.length - 1, "norm");
		for(SuperInt d : data) System.out.println("Norm: " + d.getNorm());
	}
	
//SORTING ALGORITHMS--------------------------------------------------------------------------------------------------------
	
	public static void quickSort(SuperInt[] arr, int start, int end, String sort) {
		if(start < end) {
			int k = partition(arr, start, end, sort);
			quickSort(arr, start, k - 1, sort);
			quickSort(arr, k + 1, end, sort);
		}
	}
	public static void quickSort3(SuperInt[] arr, int start, int end, String sort) {
		int piv = rand.nextInt((end - start) + 1) + start;
		swap(arr, end, piv);
		int i = 1;
		int k = 1;
		int p = piv;
		while(i < p) {
			switch(arr[i].compareNorm(arr[end])) {
			case -1:
				swap(arr, i++, k++);
				break;
			case 0:
				swap(arr, i, --p);
				break;
			default:
				i++;
				break;
			}
		}
		int m = Math.min(p-k, end-p+1);
	}
	public static void countSort(SuperInt[] arr) {
		//This'll be a bit persnickety to implement
	}
	public static void insertionSort(SuperInt[] arr, String sort) {
		for(int i = 1; i < arr.length; i++) {
			switch(sort) {
				case "norm":
					for(int k = i; k > 0 && arr[k].getNorm() < arr[k-1].getNorm(); k--) {
						swap(arr, k, k-1);
					}
					break;
				case "var":
					for(int k = i; k > 0 && arr[k].getVar() < arr[k-1].getVar(); k--) {
						swap(arr, k, k-1);
					}
					break;
				case "sum":
					for(int k = i; k > 0 && arr[k].getSum() < arr[k-1].getSum(); k--) {
						swap(arr, k, k-1);
					}
					break;
				default:
					System.out.println("That is not a valid input, please use either 'norm', 'var', or 'sum'.");
					//used to break out of the upper for loop.
					i = arr.length;
					break;
			}
		}
	}
	public static void mergeSort(SuperInt[] arr, String sort) {
		
	}
	
	public static int partition(SuperInt[] arr, int start, int end, String sort) {
		int i = start - 1;
		SuperInt ending = arr[end];
		switch(sort) {
		case "norm":
			for(int j = start; j < end; j++) {
				if(arr[j].compareNorm(ending) == -1) {
					i++;
					swap(arr, i, j);
				}
			}
			break;
		case "var":
			for(int j = start; j < end; j++) {
				if(arr[j].compareVar(ending) == -1) {
					i++;
					swap(arr, i, j);
				}
			}
			break;
		case "sum":
			for(int j = start; j < end; j++) {
				if(arr[j].compareSum(ending) == -1) {
					i++;
					swap(arr, i, j);
				}
			}
			break;
		default:
			System.out.println("That is not a valid input, please use either 'norm', 'var', or 'sum'.");
			//used to break out of the upper for loop.
			break;
		}
		swap(arr, i + 1, end);
		return i + 1;
	}
	
	public static void swap(SuperInt[] arr, int i, int j) {
		SuperInt a = arr[i];
		arr[i] = arr[j];
		arr[j] = a;
	}
//SUPERINT CREATION/FILE GENERATING/READING --------------------------------------------------------------------------------
	public static SuperInt[] intsToSupers(int[] in) {
		SuperInt[] ret = new SuperInt[in.length];
		SuperInt temp = new SuperInt();
		for(int i = 0; i < ret.length; i++) {
			temp.setNorm(in[i]);
			temp.setVar(avrg(in[i]));
			temp.setSum(digitSum(in[i]));
			ret[i] = temp;
			temp = new SuperInt();
		}
		return ret;
	}
	public static int digitSum(int n) {
		//Returns the sum of digits in a number /n/
		int s = 0;
		int digit;
		while(n >= 1) {
			digit = n%10;
			s += digit;
			n = n/10;
		}
		return s;
	}
	public static int avrg(int n) {
		//Produces an accurate ranking system for the variance of digits that takes into account length and amount of non-existent digits.
		//  i.e. if a 7 doesn't show up in a number.
		int[] count = countDigits(n);
		double sum = 0;
		int countZero = 1;
		for (int d : count) {
			sum += d;
			if (d == 0) {
				countZero++;
			}
		}
		double avg = sum / 10 / countZero;
		int p = (int) (avg * 100);
		return p;
	}
	public static int[] countDigits(int x) {
		//countDigits takes in an int /x/ and returns an array that tracks the amount of occurrences of each number in it's index.
		// i.e. if a 1 shows up, index 1 is incremented.
		int[] counter = new int[10];
		if (x == 0) counter[0]++;
		int digit;
		while(x >= 1) {
			digit = x%10;
			counter[digit]++;
			x = x/10;
		}
		return counter;
	}
	public static SuperInt[] arrGen(int size, int min, int max) {
		//Generate an array of /size/ SuperInts, norms ranging from /min/ to /max/
		//TESTING PURPOSES ONLY, NOT ACTUALLY USED IN SORTING
		SuperInt[] ret = new SuperInt[size];
		SuperInt tmp = new SuperInt();
		int newRand;
		for(int i = 0; i < size; i++) {
			newRand = rand.nextInt((max - min) + 1) + min;
			tmp.setNorm(newRand);
			tmp.setVar(avrg(newRand));
			ret[i] = tmp;
			tmp = new SuperInt();
		}
		return ret;
	}
	
	public static void generateFile(int amt, int min, int max, int chanceRepeats, String fileName) throws IOException {
		//generates a file with /amt/ integers, ranging from /min/ to /max/ with a /chanceRepeats/ chance for repeats.
		//NOTE TO SELF: Could maybe let the user make every x number a repeat.
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
		int temp;
		for(int i = 0; i < amt; i++) {
			temp = rand.nextInt((max - min) + 1) + min;
			out.write("" + temp);
			if(i != amt - 1) out.newLine();
			while(roll(chanceRepeats) && i < amt - 1) {
				out.write("" + temp);
				if(i != amt - 1) out.newLine();
				i++;
			}
		}
		out.flush();
		out.close();
	}
	public static boolean roll(int percent) {
		//Awful rolling mechanic, assumes percent is between 0 and 100, inclusively.
		if(rand.nextInt(100) < percent) {
			return true; 
		} else { 
			return false; 
		}
	}
	public static SuperInt[] readData(String fileName) {
		//Reads each line in a file as a string, adds it to the ArrayList.
		//Returns as an array to be sorted.
		ArrayList<Integer> input = new ArrayList<Integer>();
		Scanner scan;
		try {
			scan = new Scanner(new File(fileName));
			while(scan.hasNext()) {
				Integer next = scan.nextInt();
				input.add(next);
				if(next < min) min = next;
				if(next > max) max = next;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int[] ints = convertArr(input);
		return intsToSupers(ints);
	}
	public static int[] convertArr(ArrayList<Integer> ints) {
		//converts an ArrayList<Integer> to int[]
		int[] ret = new int[ints.size()];
		Iterator<Integer> iterator = ints.iterator();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}
}
