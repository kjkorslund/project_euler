package problems_pg2.p53;

import java.math.BigInteger;

import utils.BigMathUtils;

public class P53 {
	/*
	 * There are exactly ten ways of selecting three from five, 12345:
	 * 
	 *    123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
	 * 
	 * In combinatorics, we use the notation, ^5C_3 = 10.
	 * 
	 * In general, ^nC_r = n! / (r!(n−r)!) , where r ≤ n, n! = n×(n−1)×...×3×2×1, and
	 * 0! = 1.
	 * 
	 * It is not until n = 23, that a value exceeds one-million: ^23C_10 =
	 * 1144066.
	 * 
	 * How many, not necessarily distinct, values of ^nC_r, for 1 ≤ n ≤ 100, are
	 * greater than one-million?
	 */
	public static void main(String[] args) {
		BigInteger threshold = BigInteger.valueOf(1000000);
		
		int count = 0;
		for(int n=1; n<=100; n++) {
			for(int r=1; r<n; r++) {
				if (calculateNCR(n, r).compareTo(threshold) > 0) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
	
	private static BigInteger calculateNCR(int n, int r) {
		BigInteger nFactorial = BigMathUtils.factorial(n);
		BigInteger rFactorial = BigMathUtils.factorial(r);
		BigInteger nrFactorial = BigMathUtils.factorial(n-r);
		return nFactorial.divide(rFactorial.multiply(nrFactorial));
	}
}
