package problems.p27;

import utils.PrimeUtils;

public class P27 {
	/*
	 * Euler published the remarkable quadratic formula:
	 * 
	 *    n^2 + n + 41
	 * 
	 * It turns out that the formula will produce 40 primes for the consecutive
	 * values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41
	 * is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly
	 * divisible by 41.
	 * 
	 * Using computers, the incredible formula n² - 79n + 1601 was discovered,
	 * which produces 80 primes for the consecutive values n = 0 to 79. The
	 * product of the coefficients, -79 and 1601, is -126479.
	 * 
	 * Considering quadratics of the form:
	 * 
	 * n^2 + an + b, where |a| < 1000 and |b| < 1000
	 * 
	 * where |n| is the modulus/absolute value of n, e.g. |11| = 11 and |-4| = 4
	 * 
	 * Find the product of the coefficients, a and b, for the quadratic
	 * expression that produces the maximum number of primes for consecutive
	 * values of n, starting with n = 0.
	 */
	public static void main(String[] args) {
		System.out.println(numConsecutiveQuadPrimes(1, 41));
		System.out.println(numConsecutiveQuadPrimes(-79, 1601));
		
		long maxCount = 0;
		int maxA = 0;
		int maxB = 0;
		for(int i=-999; i<1000; i++) {
			for(int j=-999; j<1000; j++) {
				long curMax = numConsecutiveQuadPrimes(i, j);
				if (curMax <= maxCount) continue;
				maxCount = curMax;
				maxA = i;
				maxB = j;
				System.out.println(i + "," + j + " --> " + curMax);
			}
		}
		System.out.println("Max: " + maxA + "," + maxB + " --> " + maxCount);
	}
	
	static int numConsecutiveQuadPrimes(int a, int b) {
		int count = 0;
		long result = quadResult(count,a,b);
		while(PrimeUtils.isPrime(result)) {
			count++;
			result = quadResult(count,a,b);
		}
		return count;
	}
	
	static long quadResult(long n, int a, int b) {
		return n*n + a*n + b;
	}
}
