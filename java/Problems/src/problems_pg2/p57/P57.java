package problems_pg2.p57;

import java.math.BigInteger;

import utils.BigMathUtils;

public class P57 {

	/*
	 * It is possible to show that the square root of two can be expressed as
	 * an infinite continued fraction.
	 * 
	 *    √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
	 *    
	 * By expanding this for the first four iterations, we get:
	 * 
	 *    1 + 1/2 = 3/2 = 1.5
	 *    1 + 1/(2 + 1/2) = 7/5 = 1.4
	 *    1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
	 *    1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
	 *    
	 * The next three expansions are 99/70, 239/169, and 577/408, but the
	 * eighth expansion, 1393/985, is the first example where the number of
	 * digits in the numerator exceeds the number of digits in the denominator.
	 * 
	 * In the first one-thousand expansions, how many fractions contain a
	 * numerator with more digits than denominator?
	 */
	public static void main(String[] args) {
		int count = 0;
		for(int i=1; i<=1000; i++) {
			BigFraction f = sqrt2Expansion(i);
//			System.out.println(f);
			
			long nCount = BigMathUtils.countDigits(f.numerator);
			long dCount = BigMathUtils.countDigits(f.denominator);
			if (nCount > dCount) {
				count++;
			}
		}
		System.out.println("Matches: " + count);
		
//		sqrt2Expansion(1000);
	}

	private static BigFraction sqrt2Expansion(int iterations) {
		BigInteger two = BigInteger.valueOf(2);
		BigFraction result = new BigFraction(BigInteger.ONE, two);
		
		int iters = 1;
		while(iters++ < iterations) {
			result = result.add(two).invert();
		}
		
		return result.add(BigInteger.ONE);
	}
	
	/**
	 * This is a simplified version of the Fraction class designed to work with
	 * BigInteger. It turns out that the way the problem works, the 'reduce'
	 * operation will never have any impact, so it can be skipped. Which is
	 * good, because the existing 'reduce' operation requires computing the GCD,
	 * which I can't currently do for BigInteger.
	 * 
	 * @author Kevin
	 * 
	 */
	private static class BigFraction {
		final BigInteger numerator;
		final BigInteger denominator;
		
		public BigFraction(BigInteger numerator, BigInteger denominator) {
			this.numerator = numerator;
			this.denominator = denominator;
		}
		
		BigFraction add(BigInteger num) {
			BigInteger n = numerator.add(num.multiply(denominator));
			return new BigFraction(n, denominator);
		}
		
		BigFraction invert() {
			return new BigFraction(denominator, numerator);
		}
		
		@Override
		public String toString() {
			return numerator.toString() + "/" + denominator.toString();
		}
	}
}
