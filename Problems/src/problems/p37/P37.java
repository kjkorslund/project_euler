package problems.p37;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import utils.PrimeUtils;

public class P37 {
	/*
	 * The number 3797 has an interesting property. Being prime itself, it is
	 * possible to continuously remove digits from left to right, and remain
	 * prime at each stage: 3797, 797, 97, and 7. Similarly we can work from
	 * right to left: 3797, 379, 37, and 3.
	 * 
	 * Find the sum of the only eleven primes that are both truncatable from
	 * left to right and right to left.
	 * 
	 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
	 */
	public static void main(String[] args) {
		List<Integer> truncatablePrimes = computeTruncatablePrimes();
		
		long sum = 0;
		for(int num : truncatablePrimes) {
			sum += num;
		}
		System.out.println(truncatablePrimes);
		System.out.println(sum);
	}
	
	static List<Integer> computeTruncatablePrimes() {
		// [kjk] I cheat a little here, because I take advantage of being told
		// there are only eleven results
		ArrayList<Integer> results = new ArrayList<Integer>();
		LinkedList<Integer> lastPrimes = new LinkedList<Integer>(Arrays.asList(2,3,5,7));
		int digitMultiplier = 10;
		while(results.size() < 11) {
			LinkedList<Integer> newPrimes = new LinkedList<Integer>();
			for(Integer lastPrime : lastPrimes) {
				for(int i=1; i<10; i++) {
					int candidate = i*digitMultiplier + lastPrime;
					if (PrimeUtils.isPrime(candidate)) {
						newPrimes.add(candidate);
						if (isRTLTruncatable(candidate)) {
							results.add(candidate);
						}
					}
				}
			}
			lastPrimes = newPrimes;
			digitMultiplier *= 10;
		}
		return results;
	}
	
	static boolean isRTLTruncatable(int num) {
		for(; num > 0; num /= 10) {
			if (PrimeUtils.isPrime(num)) continue;
			return false;
		}
		return true;
	}
}
