package problems.p30;

import java.util.HashSet;
import java.util.Set;

import utils.MathUtils;

public class P30 {
	/*
	 * Surprisingly there are only three numbers that can be written as the sum
	 * of fourth powers of their digits:
	 * 
	 *    1634 = 14 + 64 + 34 + 44
	 *    8208 = 84 + 24 + 04 + 84
	 *    9474 = 94 + 44 + 74 + 44
	 * 
	 * As 1 = 14 is not a sum it is not included.
	 * 
	 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
	 * 
	 * Find the sum of all the numbers that can be written as the sum of fifth
	 * powers of their digits.
	 */
	public static void main(String[] args) {
		Set<Long> results = computeResults(5);
		
		long sum = 0;
		for(Long result : results) sum += result;
		
		System.out.println(results + " --> " + sum);
	}
	
	/*
	 * This is pretty straightforward, just iterate through, check if it's a
	 * match, and put it in a set if it is. The only tricky part is knowing when
	 * to stop.
	 */
	static Set<Long> computeResults(int exponent) {
		HashSet<Long> results = new HashSet<Long>();
		// First, build powers table for efficiency later
		int[] powTable = new int[10];
		for(int i=0; i<10; i++) {
			powTable[i] = (int) MathUtils.pow(i, exponent);
		}
		
		// Next, compute the cap for the iteration
		int digits = 2;
		while(MathUtils.pow(10, digits) <= powTable[9]*digits) {
			digits++;
		}
		long cap = MathUtils.pow(10, digits);
		
		// Finally, iterate and check
		for(long num=10; num<cap; num++) {
			long l = num;
			long sum = 0;
			while(l>0) {
				sum += powTable[(int)(l%10)];
				l = l/10;
			}
			if (sum != num) continue;
			results.add(num);
		}
		return results;
	}
}
