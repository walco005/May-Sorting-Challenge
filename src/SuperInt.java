//This class 'SuperInt' contains an array storing the amount of each digit and the sum of the digits as well as the normal int itself.
public class SuperInt {
	int norm;
	int[] count;
	int sum;
	int variance;
	
	public SuperInt(int norm, int[] count, int sum, int variance) {
		this.norm = norm;
		this.count = count;
		this.sum = sum;
		this.variance = variance; 
	}
	
	public int getNorm() {
		return this.norm;
	}
	public int[] getCount() {
		return this.count;
	}
	public int getSum() {
		return this.sum;
	}
}
