package problems_pg2.p52;

import java.util.Arrays;

import utils.DigitUtils;

public class P52 {
	/*
	 * It can be seen that the number, 125874, and its double, 251748, contain
	 * exactly the same digits, but in a different order.
	 * 
	 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
	 * contain the same digits.
	 */
	public static void main(String[] args) {
		for(long l = 1; l < 1000000; l++) {
			if (test(l)) {
				System.out.println("Match: " + l);
				for(int i=2; i<=6; i++) {
					System.out.println("  x" + i + " = " + l*i);
				}
				System.out.println();
			}
		}
	}
	
	static boolean test(long n) {
		int[] nDigits = DigitUtils.getDigits(n);
		Arrays.sort(nDigits);
		
		for(int i=2; i<=6; i++) {
			int[] digits = DigitUtils.getDigits(n*i);
			Arrays.sort(digits);
			if (Arrays.equals(nDigits, digits)) continue;
			return false;
		}
		return true;
	}
}
