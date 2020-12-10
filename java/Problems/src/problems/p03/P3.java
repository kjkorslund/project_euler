package problems.p03;

public class P3 {
	/*
	 * The prime factors of 13195 are 5, 7, 13 and 29.
	 * 
	 * What is the largest prime factor of the number 600851475143 ?
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(largestPrimeFactor(13195L));
		System.out.println(largestPrimeFactor(600851475143L));
	}
	
	public static long largestPrimeFactor(long input) {
		long remaining = input;
		long max = 1;
		while(remaining > 1) {
			long primeFactor = firstPrimeFactorAboveOne(remaining);
			max = Math.max(max, primeFactor);
			remaining = remaining / primeFactor;
		}
		return max;
	}
	
	static long firstPrimeFactorAboveOne(long input) {
		for(long l = 2; l < input/2; l++) {
			if (input%l == 0) {
				return l;
			}
		}
		return input;
	}

}
