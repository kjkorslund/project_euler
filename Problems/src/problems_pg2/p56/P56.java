package problems_pg2.p56;

import java.math.BigInteger;

import utils.BigMathUtils;

public class P56 {

	/*
	 * A googol (10^100) is a massive number: one followed by one-hundred
	 * zeros; 100^100 is almost unimaginably large: one followed by two-hundred
	 * zeros. Despite their size, the sum of the digits in each number is only
	 * 1.
	 * 
	 * Considering natural numbers of the form, a^b, where a, b < 100, what is
	 * the maximum digital sum?
	 */
	public static void main(String[] args) {
		long result = 1;
		int cap = 100;
		for(int i=0; i<cap; i++) {
			for (int j=0; j<cap; j++) {
				long digitalSum = digitalSum(i, j);
				result = Math.max(result, digitalSum);
			}
		}
		System.out.println(result);
	}

	private static long digitalSum(int a, int b) {
		BigInteger big = BigInteger.valueOf(a);
		big = big.pow(b);
		return BigMathUtils.sumDigits(big);
	}
}
