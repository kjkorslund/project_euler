package problems.p47;

import java.util.Arrays;
import java.util.HashSet;

import utils.PrimeUtils;

public class P47 {
	/*
	 * The first two consecutive numbers to have two distinct prime factors are:
	 * 
	 *   14 = 2 × 7
	 *   15 = 3 × 5
	 * 
	 * The first three consecutive numbers to have three distinct prime factors
	 * are:
	 * 
	 *   644 = 2^2 × 7 × 23
	 *   645 = 3 × 5 × 43
	 *   646 = 2 × 17 × 19
	 * 
	 * Find the first four consecutive integers to have four distinct prime
	 * factors. What is the first of these numbers?
	 */
	
	public static void main(String[] args) {
//		System.out.println(distinctFactorCount(14));
//		System.out.println(distinctFactorCount(15));
//		
//		System.out.println(distinctFactorCount(644));
//		System.out.println(distinctFactorCount(645));
//		System.out.println(distinctFactorCount(646));
		
		long cap = 1000000;
		long count = 4;
		for(long n=1; n<cap; n+=count) {
			if (distinctFactorCount(n) != count) continue;
			
			long l = n-1;
			while(distinctFactorCount(l) == count) l--;
			long start = l+1;
			
			l = n+1;
			while(distinctFactorCount(l) == count) l++;
			long end = l-1;
			if ((end + 1 - start) >= count) {
				System.out.println("Match: [" + start + ", " + end + "]");
			}
		}
	}
	
	private static int distinctFactorCount(long n) {
		long[] primeFactors = PrimeUtils.computePrimeFactors(n);
		HashSet<Long> distinctFactors = new HashSet<>();
		for(long primeFactor : primeFactors) {
			distinctFactors.add(primeFactor);
		}
		
		return distinctFactors.size();
	}

}
