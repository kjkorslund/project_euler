package problems.p28;

public class P28 {
	/*
	 * Starting with the number 1 and moving to the right in a clockwise
	 * direction a 5 by 5 spiral is formed as follows:
	 * 
	 *     21 22 23 24 25
	 *     20  7  8  9 10
	 *     19  6  1  2 11
	 *     18  5  4  3 12
	 *     17 16 15 14 13
	 * 
	 * It can be verified that the sum of the numbers on the diagonals is 101.
	 * 
	 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral
	 * formed in the same way?
	 */
	
	public static void main(String[] args) {
		System.out.println(computeSpiralGridDiagonalSum(1001));
	}
	
	/*
	 * [kjk] The numbers to sum start with one, then go up by two for four
	 * values, then by four for four values, then by six for four values, etc.
	 */
	static long computeSpiralGridDiagonalSum(long size) {
		long i = 1;
		long increment = 2;
		long levelCount = 0;
		long sum = 0;
		while(i <= size*size) {
			//System.out.println(i);
			sum += i;
			i += increment;
			levelCount++;
			if (levelCount == 4) {
				levelCount = 0;
				increment += 2;
			}
		}
		return sum;
	}
}
