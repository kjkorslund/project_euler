package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DigitUtils {
	public static int[] getDigitCounts(long num) {
		return getDigitCounts(10,num);
	}
	
	public static int[] getDigitCounts(int base, long num) {
		int[] result = new int[base];
		while(num > 0) {
			result[(int)(num % base)]++;
			num = num / base;
		}
		return result;
	}
	
	public static boolean isPandigital(int num) {
		boolean[] digitFound = new boolean[10];
		int digitCount = 0;
		
		// 1) collect digits found
		while(num > 0) {
			int digit = num%10;
			digitCount++;
			if (digit > 0 && !digitFound[digit]) {
				digitFound[digit] = true;
				num /= 10;
				continue;
			}
			return false;
		}

		// 2) verify digits found are in range
		for(int i=1; i<=digitCount; i++) {
			if (digitFound[i]) continue;
			return false;
		}
		return true;
	}
	
	public static boolean isPalindromic(long num) {
		return isPalindromic(10, num);
	}
	
	public static boolean isPalindromic(int base, long num) {
		int[] digits = getDigits(base, num);
		for(int i=0,j=digits.length-1; i<j; i++, j--) {
			if (digits[i] == digits[j]) continue;
			return false;
		}
		return true;
	}
	
	public static int getDigitCount(long num) {
		return getDigitCount(10,num);
	}
	
	public static int getDigitCount(long base, long num) {
		int result = 0;
		while(num > 0) {
			result++;
			num = num / base;
		}
		return result;
	}
	
	public static int[] getDigits(long num) {
		return getDigits(10,num);
	}
	
	public static int[] getDigits(int base, long num) {
		LinkedList<Integer> digitList = new LinkedList<Integer>();
		
		while(num > 0) {
			digitList.addFirst((int)(num%base));
			num = num / base;
		}
		return ArrayUtils.toArray(digitList);
	}
	
	public static long composeFromDigits(int... digits) {
		return composeFromDigitsBase(10,digits);
	}
	
	public static long composeFromDigitsBase(int base, int... digits) {
		long result = 0;
		for(int i=0; i<digits.length; i++) {
			result = result*base + digits[i];
		}
		return result;
	}
	
	public static long reverse(long num) {
		return reverseDigits(10, num);
	}
	
	public static long reverseDigits(int base, long num) {
		int[] digits = getDigits(base);
		
		// Reverse digits
		for(int i=0,j=digits.length-1; i<j; i++, j--) {
			int d = digits[i];
			digits[i] = digits[j];
			digits[j] = d;
		}
		
		return composeFromDigitsBase(base, digits);
	}
	
	public static long concatenateDigits(long... nums) {
		ArrayList<Integer> digitsList = new ArrayList<Integer>();
		for(long num : nums) {
			for(int digit : getDigits(num)) {
				digitsList.add(digit);
			}
		}
		
		int[] digitsArr = new int[digitsList.size()];
		for(int i = 0; i < digitsList.size(); i++) {
			digitsArr[i] = digitsList.get(i);
		}
		return composeFromDigits(digitsArr);
	}

	public static boolean isDigitalPermutation(long l1, long l2) {
		return Arrays.equals(getDigitCounts(l1), getDigitCounts(l2));
	}
}
