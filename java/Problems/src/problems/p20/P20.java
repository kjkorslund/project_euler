package problems.p20;

import java.math.BigInteger;

import utils.BigMathUtils;

public class P20 {
	/*
	 * n! means n x (n - 1) x ... x 3 x 2 x 1
	 * 
	 * For example, 10! = 10 x 9 x ... x 3 x 2 x 1 = 3628800, and the sum of the
	 * digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
	 * 
	 * Find the sum of the digits in the number 100!
	 */
	
	public static void main(String[] args) {
		BigInteger big = BigMathUtils.factorial(100);
		System.out.println(big);
		System.out.println(BigMathUtils.sumDigits(big));
	}

}
