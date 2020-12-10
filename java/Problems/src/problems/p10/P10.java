package problems.p10;

import utils.PrimeUtils;

public class P10 {
	/*
	 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
	 * 
	 * Find the sum of all the primes below two million.
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(sumPrimes(2000000));
	}
	
	static long sumPrimes(long cap) {
		long sum = 0;
		for(long l = 0; l<cap; l++) {
			if(PrimeUtils.isPrime(l)) {
				sum += l;
			}
		}
		return sum;
	}

}
