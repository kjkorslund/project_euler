package utils;

import java.util.LinkedList;

public class DigitUtils {
	public static int[] getDigitCounts(int num) {
		return getDigitCounts(10,num);
	}
	
	public static int[] getDigitCounts(int base, int num) {
		int[] result = new int[base];
		while(num > 0) {
			result[num % base]++;
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
	
	public static int getDigitCount(int num) {
		return getDigitCount(10,num);
	}
	
	public static int getDigitCount(int base, int num) {
		int result = 0;
		while(num > 0) {
			result++;
			num = num / base;
		}
		return result;
	}
	
	public static int[] getDigits(int num) {
		return getDigits(10,num);
	}
	
	public static int[] getDigits(int base, int num) {
		LinkedList<Integer> digitList = new LinkedList<Integer>();
		
		while(num > 0) {
			digitList.addFirst(num%base);
			num = num / base;
		}
		return ArrayUtils.toArray(digitList);
	}
	
	public static long composeFromDigits(int... digits) {
		return composeFromDigitsBase(10,digits);
	}
	
	public static long composeFromDigitsBase(int base, int... digits) {
		int m = 1;
		long result = 0;
		for(int i=0; i<digits.length; i++) {
			result = result*base + digits[i];
		}
		return result;
	}
}
