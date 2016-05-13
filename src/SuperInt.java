//This class 'SuperInt' contains an array storing the amount of each digit and the sum of the digits as well as the normal int itself.
public class SuperInt {
	private int norm;
	private int sum;
	private int variance;
	
	public SuperInt() {
	}
	
	public int getNorm() {
		return this.norm;
	}
	public int getSum() {
		return this.sum;
	}
	public int getVar() {
		return this.variance;
	}
	public void setNorm(int norm) {
		this.norm = norm;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public void setVar(int var) {
		this.variance = var;
	}
	
	public String toString() {
		String str = this.norm + " with a sum of " + this.sum + "and a variance ranking of " + this.variance;
		return str;
	}
}
