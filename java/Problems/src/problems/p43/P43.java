package problems.p43;

import java.util.Arrays;
import java.util.List;

import utils.ArrayUtils;
import utils.DigitUtils;
import utils.PermIter;

public class P43 {
	/*
	 * The number, 1406357289, is a 0 to 9 pandigital number because it is made
	 * up of each of the digits 0 to 9 in some order, but it also has a rather
	 * interesting sub-string divisibility property.
	 * 
	 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we
	 * note the following:
	 * 
	 *    d2d3d4=406 is divisible by 2
	 *    d3d4d5=063 is divisible by 3
	 *    d4d5d6=635 is divisible by 5
	 *    d5d6d7=357 is divisible by 7
	 *    d6d7d8=572 is divisible by 11
	 *    d7d8d9=728 is divisible by 13
	 *    d8d9d10=289 is divisible by 17
	 * 
	 * Find the sum of all 0 to 9 pandigital numbers with this property.
	 */
	public static void main(String[] args) {
		PermIter<Integer> iter = new PermIter<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		long sum = 0;
		int count = 0;
		while(iter.hasNext()) {
			List<Integer> perm = iter.next();
			int[] digits = ArrayUtils.toArray(perm);
			if (isSubstringDivisibile(digits)) {
				long num = DigitUtils.composeFromDigits(digits);
				System.out.println(num);
				sum += num;
				count++;
			}
		}
		System.out.println(count);
		System.out.println(sum);
	}
	
	static boolean isSubstringDivisibile(int... digits) {
		if (digits.length != 10) throw new RuntimeException("not 10 digits: " + Arrays.toString(digits));
		
		return (
			(DigitUtils.composeFromDigits(digits[1],digits[2],digits[3]) % 2 == 0)
			&& ((DigitUtils.composeFromDigits(digits[2],digits[3],digits[4]) % 3 == 0))
			&& ((DigitUtils.composeFromDigits(digits[3],digits[4],digits[5]) % 5 == 0))
			&& ((DigitUtils.composeFromDigits(digits[4],digits[5],digits[6]) % 7 == 0))
			&& ((DigitUtils.composeFromDigits(digits[5],digits[6],digits[7]) % 11 == 0))
			&& ((DigitUtils.composeFromDigits(digits[6],digits[7],digits[8]) % 13 == 0))
			&& ((DigitUtils.composeFromDigits(digits[7],digits[8],digits[9]) % 17 == 0))
		);
	}
}
