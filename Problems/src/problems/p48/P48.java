package problems.p48;

import java.math.BigInteger;

import utils.MathUtils;

public class P48 {
	/*
	 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
	 * 
	 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
	 */
	public static void main(String[] args) {
		/*
		 * abc * def...if we're only interested in the last 2 digits, we just need to add
		 * bc*f + c*e.  The rest of the calculation doesn't matter because it only
		 * contributes to the higher-order digits.
		 */
		
//		long cap = 10000;
//		long b = 1;
//		long e = 1;
//		while(b++ < cap) {
//			try {
//				while(e++ < cap) {
//					
//					long expected = BigInteger.valueOf(b).modPow(BigInteger.valueOf(e),BigInteger.valueOf(MOD)).longValue();
//					long actual = powTenDigits(b, e);
//					if (actual == expected) continue;
//					
//					System.out.println("Mismatch (" + b + "^" + e + "): " + expected + " != " + actual);
//					break;
//				}
//			}
//			catch (IllegalArgumentException ex) {
//				System.out.println("Out of bounds: " + b + "^" + e);
//			}
//			e = 1;
//		}
		
		long sum = 0;
		for(long l = 1; l<=1000; l++) {
			sum += powTenDigits(l, l);
		}
		System.out.println(sum%MOD);
	}

	// Bit filter that is just large enough to guarantee 10 digits are preserved
	private static final long MOD = 10000000000l; // 10^10

	private static long powTenDigits(long base, long exponent) {
		if (exponent == 0) return 1;
		
		long l = 1;
		while(exponent-- > 0) {
			l = l * base;
			l = l % MOD;
		}
		
		return l;
	}
}
