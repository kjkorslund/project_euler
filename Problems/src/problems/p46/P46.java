package problems.p46;

import utils.PrimeUtils;

public class P46 {
	/*
	 * 
	 * 
	 * It was proposed by Christian Goldbach that every odd composite number can
	 * be written as the sum of a prime and twice a square.
	 * 
	 * 9 = 7 + 2×1^2
	 * 15 = 7 + 2×2^2
	 * 21 = 3 + 2×3^2
	 * 25 = 7 + 2×3^2
	 * 27 = 19 + 2×2^2
	 * 33 = 31 + 2×1^2
	 * 
	 * It turns out that the conjecture was false.
	 * 
	 * What is the smallest odd composite that cannot be written as the sum of a
	 * prime and twice a square?
	 */
	public static void main(String[] args) {
		int cap = 10000;
		for(int i=9; i<cap; i+=2) {
			if (PrimeUtils.isPrime(i)) continue;
			if (isGoldbach(i)) continue;
			
			System.out.println("Match:  " + i);
			break;
		}
	}

	private static boolean isGoldbach(long n) {
		long base = 1;
		long p = n - 2*base*base;
		while(p > 0) {
			if (PrimeUtils.isPrime(p)) return true;
			
			base++;
			p = n - 2*base*base;
		}
		return false;
	}
}
