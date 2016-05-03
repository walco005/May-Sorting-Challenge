import static org.junit.Assert.*;

import org.junit.Test;

public class Week1Test {

	@Test
	public void testCountDigits() {
		//Really ugly way of setting up the int arrays
		//TODO attempt to find a better way to set up these arrays.
		int test1 = 0;
		int test2 = 1;
		int test3 = 14906712;
		int[] arr1 = new int[10];
		arr1[0]++;
		int[] arr2 = new int[10];
		arr2[1]++;
		int[] arr3 = new int[10];
		arr3[0] = 1;
		arr3[1] = 2;
		arr3[2] = 1;
		arr3[4] = 1;
		arr3[6] = 1;
		arr3[7] = 1;
		arr3[9] = 1;
		assertArrayEquals(Week1.countDigits(test1), arr1);
		assertArrayEquals(Week1.countDigits(test2), arr2);
		assertArrayEquals(Week1.countDigits(test3), arr3);
	}

}
