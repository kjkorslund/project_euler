package problems.p6;

import java.util.Arrays;

import utils.MathUtils;

public class P6 {
	/*
	 * The sum of the squares of the first ten natural numbers is,
	 * 
	 *     1^2 + 2^2 + ... + 10^2 = 385
	 *     
	 * The square of the sum of the first ten natural numbers is,
	 * 
	 *     (1 + 2 + ... + 10)^2 = 55^2 = 3025
	 * 
	 * Hence the difference between the sum of the squares of the first ten
	 * natural numbers and the square of the sum is 3025 - 385 = 2640.
	 * 
	 * Find the difference between the sum of the squares of the first one
	 * hundred natural numbers and the square of the sum.
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(diffSumSquare(10));
		System.out.println(diffSumSquare(100));
	}
	
	public static long diffSumSquare(int num) {
		int sum = MathUtils.sumUpTo(num);
		return MathUtils.pow(sum, 2) - sumSquares(num);
	}
	
	public static long sumSquares(int num) {
		long result = 0;
		for(int i=0; i<=num; i++) {
			result = result + MathUtils.pow(i, 2);
		}
		return result;
	}
	
}
