package problems.p38;

import java.util.ArrayList;

import utils.ArrayUtils;
import utils.DigitUtils;

public class P38 {
	/*
	 * Take the number 192 and multiply it by each of 1, 2, and 3:
	 * 
	 *    192 × 1 = 192
	 *    192 × 2 = 384
	 *    192 × 3 = 576
	 * 
	 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We
	 * will call 192384576 the concatenated product of 192 and (1,2,3)
	 * 
	 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3,
	 * 4, and 5, giving the pandigital, 918273645, which is the concatenated
	 * product of 9 and (1,2,3,4,5).
	 * 
	 * What is the largest 1 to 9 pandigital 9-digit number that can be formed
	 * as the concatenated product of an integer with (1,2, ... , n) where n >
	 * 1?
	 */
	public static void main(String[] args) {
		System.out.println(pandigitalConcatProduct(9));
		System.out.println(pandigitalConcatProduct(192));
		System.out.println(pandigitalConcatProduct(123456789));
		System.out.println(pandigitalConcatProduct(9327));
		int max = 0;
		//for(int i=1; i<329218107; i++) {
		for(int i=1; i<999999; i++) {
			Integer pcp = pandigitalConcatProduct(i);
			if (pcp == null) continue;
			max = Math.max(max, pcp);
		}
		System.out.println(max);
	}
	
	static Integer pandigitalConcatProduct(int n) {
		boolean[] usedDigits = new boolean[10];
		ArrayList<Integer> concatDigits = new ArrayList<Integer>(10);
		int digitCount = 0;
		int mult=1;
		while(digitCount < 9) {
			int[] digits = DigitUtils.getDigits(n*mult);
			for(int digit : digits) {
				if (digit != 0 && !usedDigits[digit]) {
					usedDigits[digit] = true;
					concatDigits.add(digit);
					digitCount++;
					continue;
				}
				return null;
			}
			mult++;
		}
		if (mult > 2 && concatDigits.size() == 9) {
			return (int) DigitUtils.composeFromDigits(ArrayUtils.toArray(concatDigits));
		}
		return null;
	}
	
	static boolean isPandigitalConcatProduct(int n) {
		boolean[] concatDigits = new boolean[10];
		int digitCount = 0;
		int mult=1;
		while(digitCount < 9) {
			int[] digits = DigitUtils.getDigits(n*mult);
			for(int digit : digits) {
				if (digit != 0 && !concatDigits[digit]) {
					concatDigits[digit] = true;
					digitCount++;
					continue;
				}
				return false;
			}
			mult++;
		}
		return (mult > 2);
	}
}
