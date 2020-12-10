package problems.p32;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class P32 {
	/*
	 * We shall say that an n-digit number is pandigital if it makes use of all
	 * the digits 1 to n exactly once; for example, the 5-digit number, 15234,
	 * is 1 through 5 pandigital.
	 * 
	 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing
	 * multiplicand, multiplier, and product is 1 through 9 pandigital.
	 * 
	 * Find the sum of all products whose multiplicand/multiplier/product
	 * identity can be written as a 1 through 9 pandigital.
	 * 
	 * HINT: Some products can be obtained in more than one way so be sure to
	 * only include it once in your sum.
	 */
	public static void main(String[] args) {
		// [kjk] If pandigital digits could include the digit zero, there would
		// be an infinite number. For example, 390 x 186 = 72540, etc. So skip
		// any number with a zero in it.
		// [kjk] A pandigital product requires 10 digits. If the product is six
		// digits or more, then the multiplicand and multiplier have four digits
		// or less between them. But 99x99, the max possible, has only
		// four digits. Thus, all pandigital numbers must be five digits or
		// less. Likewise, the m&m can only have up to four digits each, for a
		// 4-1-4 combination. If one of them had five, the product would have to
		// have five, which is impossible.
//		System.out.println(isPandigital(1,2,3));
//		System.out.println(isPandigital(1,2,3,4,5,6,7,8,9));
//		System.out.println(isPandigital(39,186,7254));
//		System.out.println(isPandigital(390,186,72540));
//		System.out.println(isPandigital(399,186,7254));
		
		HashSet<Integer> products = new HashSet<Integer>();
		for(int i=1; i<10000; i++) {
			for (int j=1; j<10000; j++) {
				int product = i*j;
				if (product > 99999) break;
				if (isPandigital(i,j,product)) {
//					System.out.println(i + " x " + j + " = " + product);
					products.add(product);
				}
			}
		}
		System.out.println(products.size());
		long sum = 0;
		for(Integer product : products) {
			sum += product;
		}
		System.out.println(sum);
	}
	
	static boolean isPandigital(int... nums) {
		int[] digitCounts = new int[10];
		HashSet<Integer> digits = new HashSet<Integer>();
		for(int num : nums) {
			int[] numCounts = getDigitCounts(num);
			if (numCounts[0] > 0) return false;
			for(int i=1; i<10; i++) {
				digitCounts[i] += numCounts[i];
				if (digitCounts[i] > 1) return false; 
			}
		}
		for(int i=1; i<=9; i++) {
			if (digitCounts[i] == 1) continue;
			return false;
		}
		return true;
	}
	
	static int[] getDigitCounts(int num) {
		int[] result = new int[10];
		while(num > 0) {
			result[num % 10]++;
			num = num / 10;
		}
		return result;
	}
}
