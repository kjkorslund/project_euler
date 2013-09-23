package problems.p34;

import java.util.ArrayList;

import utils.ArrayUtils;
import utils.MathUtils;

public class P34 {
	/*
	 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
	 * 
	 * Find the sum of all numbers which are equal to the sum of the factorial
	 * of their digits.
	 * 
	 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
	 */
	public static void main(String[] args) {
		// [kjk] I need to bound the range to check. The question is, what is
		// the smallest value of k for which the largest k-digit number is
		// larger than k*9! ? The answer is k=8, with 8*9! = 2903040. That is
		// the upper bound on the sum.
		
		int sum = 0;
		int count = 0;
		for(int i=10; i<=2903040; i++) {
			if (computeDigitFactorialSum(i) == i) {
				System.out.println(i);
				sum += i;
				count++;
			}
		}
		System.out.println(count + ": " + sum);
		
//		System.out.println(composeNumberFromDigits(0,1,4,5));
//		System.out.println(computeFactorialSum(1,4,5));
		
		int[] digits = new int[8];
		
		
	}
	
	static int composeNumberFromDigits(int... digits) {
		int m = 1;
		int result = 0;
		for(int i=0; i<digits.length; i++) {
			result = result*10 + digits[i];
		}
		return result;
	}
	
	static long computeFactorialSum(int... digits) {
		long sum = 0;
		for(int digit : digits) {
			sum += MathUtils.factorial(digit);
		}
		return sum;
	}
	
	static long computeDigitFactorialSum(int num) {
		long sum = 0;
		for(int digit : getDigits(num)) {
			sum += MathUtils.factorial(digit);
		}
		return sum;
	}
	
	static int[] getDigits(int num) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		while(num > 0) {
			results.add(num%10);
			num = num / 10;
		}
		return ArrayUtils.toArray(results);
	}
}
