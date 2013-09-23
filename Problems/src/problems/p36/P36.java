package problems.p36;

import utils.DigitUtils;

public class P36 {
	/*
	 * The decimal number, 585 = 1001001001b (binary), is palindromic in both
	 * bases.
	 * 
	 * Find the sum of all numbers, less than one million, which are palindromic
	 * in base 10 and base 2.
	 * 
	 * (Please note that the palindromic number, in either base, may not include
	 * leading zeros.)
	 */
	public static void main(String[] args) {
		long sum = 0;
		for(int i=0; i<1000000; i++) {
			boolean isPal2 = isPalindromic(2, i);
			boolean isPal10 = isPalindromic(10, i);
			if (isPal2 && isPal10) {
				//System.out.println(i + " = " + Integer.toString(i, 2));
				sum += i;
			}
		}
		System.out.println(sum);
	}
	
	static boolean isPalindromic(int base, int num) {
		int[] digits = DigitUtils.getDigits(base, num);
		for(int i=0,j=digits.length-1; i<j; i++, j--) {
			if (digits[i] == digits[j]) continue;
			return false;
		}
		return true;
	}
}
