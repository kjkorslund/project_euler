package problems.p07;

import utils.PrimeUtils;

public class P7 {
	/*
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
	 * 
	 * What is the 10 001st prime number?
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(nthPrime(10001,1000000));
	}
	
	static long nthPrime(long n, long cap) {
		long primeCount = 0;
		for(long l=2; l<cap; l++) {
			if (PrimeUtils.isPrime(l)) primeCount++;
			if (primeCount == n) return l;			
		}
		throw new RuntimeException("cap reached. Count: " + primeCount);
	}

}
