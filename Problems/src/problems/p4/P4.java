package problems.p4;

import utils.MathUtils;

public class P4 {
	/*
	 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
	 * 
	 * Find the largest palindrome made from the product of two 3-digit numbers.
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(largestPalindrome(2));
		System.out.println(largestPalindrome(3));
		
//		System.out.println(flip(9009));
//		System.out.println(flip(90909));
//		System.out.println(flip(12321));
//		System.out.println(flip(12433421));
	}
	
	static long largestPalindrome(int nDigits) {
//		int start = MathUtils.pow(10, nDigits) - 1;
//		
//		for(int level = start; level > 0; level--) {
//			for(int i = start; i >= level; i--) {
//				long candidate = i*level;
//				System.out.println(candidate);
//				if (isPalindrome(candidate)) {
//					return candidate;
//				}
//			}
//		}
//		throw new AssertionError();
		
		long cap = MathUtils.pow(10, nDigits);
		int result = 1;
		for(int i=1; i<cap; i++) {
			for (int j=1; j<cap; j++) {
				int candidate = i*j;
				if (isPalindrome(candidate)) {
					result = Math.max(result, candidate);
				}
			}
		}
		return result;
	}
	
	static boolean isPalindrome(long l) {
		return l == flip(l);
	}
	
	static long flip(long l) {
		long result = 0;
		while(l > 0) {
			result = result*10 + l%10;
			l = l/10;
		}
		return result;
	}

}
