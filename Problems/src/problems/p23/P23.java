package problems.p23;

import java.util.ArrayList;
import java.util.HashSet;

import utils.ArrayUtils;
import utils.PrimeUtils;

public class P23 {

	/*
	 * A perfect number is a number for which the sum of its proper divisors is
	 * exactly equal to the number. For example, the sum of the proper divisors
	 * of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect
	 * number.
	 * 
	 * A number n is called deficient if the sum of its proper divisors is less
	 * than n and it is called abundant if this sum exceeds n.
	 * 
	 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the
	 * smallest number that can be written as the sum of two abundant numbers is
	 * 24. By mathematical analysis, it can be shown that all integers greater
	 * than 28123 can be written as the sum of two abundant numbers. However,
	 * this upper limit cannot be reduced any further by analysis even though it
	 * is known that the greatest number that cannot be expressed as the sum of
	 * two abundant numbers is less than this limit.
	 * 
	 * Find the sum of all the positive integers which cannot be written as the
	 * sum of two abundant numbers.
	 */
	public static void main(String[] args) {
//		for(int i=2; i<20; i++) {
//			System.out.println("d(" + i + ") = " + sumOfProperDivisors(i) + " --> " + isAbundant(i));
//		}
		
		int count = 0;
		for(int i=2; i<28123; i++) {
			if (isAbundant(i)) count++;
		}
		System.out.println(count);
		long[] abundants = abundants(28123);
		System.out.println(abundants.length);
		HashSet<Long> abundantSums = abundantSums(28123);
		System.out.println(abundantSums.size());
		
		long sum = 0;
		for(long l=1; l<28123; l++) {
			if (abundantSums.contains(l)) continue;
			sum += l;
		}
		System.out.println(sum);
	}
	
	static HashSet<Long> abundantSums(long cap) {
		long[] abundants = abundants(cap);
		
		HashSet<Long> sums = new HashSet<Long>();
		for(int i=0; i<abundants.length; i++) {
			for(int j=i; j<abundants.length; j++) {
				long sum = abundants[i] + abundants[j];
				if (sum > cap) break;
				sums.add(sum);
			}
		}
		return sums;
	}
	
	static long[] abundants(long cap) {
		ArrayList<Long> list = new ArrayList<Long>();
		for(int i=12; i<cap; i++) {
			if (isAbundant(i)) {
				list.add((long)i);
			}
		}
		return ArrayUtils.toArrayL(list);
	}
	
	static boolean isAbundant(long n) {
		return sumOfProperDivisors(n) > n;
	}
	
	static long sumOfProperDivisors(long n) {
		long sum = 0;
		for(long divisor : PrimeUtils.divisors(n)) {
			if (divisor == n) continue;
			sum += divisor;
		}
		return sum;
	}
}
