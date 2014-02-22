package problems.p05;

import java.util.Arrays;

import utils.PrimeUtils;

public class P5 {
	/*
	 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
	 * 
	 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
	 * 
	 * 10 - 2 5
	 *  9 - 3 3
	 *  8 - 2 2 2
	 *  7 - 7
	 *  6 - 2 3
	 *  5 - 5
	 *  4 - 2 2
	 *  3 - 3
	 *  2 - 2
	 *  
	 *  2^3, 3^2, 5, 7
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(dividesEvenlyUpTo(10));
		System.out.println(dividesEvenlyUpTo(20));
	}
	
	static long dividesEvenlyUpTo(int num) {
		long result = 1;
		for(int i=1; i<=num; i++) {
			long[] primeFactors = PrimeUtils.primeFactors(i);
			long r = result;
			for(long pf : primeFactors) {
				if (r % pf != 0) {
					result = result * pf;
				} else {
					r = r / pf;
				}
			}
			System.out.println(i + ", " + result + ", " + Arrays.toString(primeFactors));
		}
		return result;
	}

}
