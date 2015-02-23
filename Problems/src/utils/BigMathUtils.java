package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class BigMathUtils {
	public static BigInteger factorial(int n) {
		if (n > 2) {
			return BigInteger.valueOf(n).multiply(factorial(n-1));
		}
		return BigInteger.valueOf(n);
	}
	
	public static long countDigits(BigInteger big) {
		BigInteger ten = BigInteger.valueOf(10);
		long result = 0;
		while(big.compareTo(BigInteger.ZERO) != 0) {
			BigInteger[] dr = big.divideAndRemainder(ten);
			result++;
			big = dr[0];
		}
		return result;
	}
	
	public static long sumDigits(BigInteger big) {
		BigInteger ten = BigInteger.valueOf(10);
		long result = 0;
		while(big.compareTo(BigInteger.ZERO) != 0) {
			BigInteger[] dr = big.divideAndRemainder(ten);
			result = result + dr[1].longValue();
			big = dr[0];
		}
		return result;
	}
	
	public static BigInteger reverseDigits(BigInteger big) {
		ArrayList<BigInteger> digits = getDigits(big);
		
		BigInteger ten = BigInteger.valueOf(10);
		BigInteger result = BigInteger.ZERO;
		for(BigInteger digit : digits) {
			result = result.multiply(ten).add(digit);
		}
		
		return result;
	}
	
	public static boolean isPalindromic(BigInteger big) {
		ArrayList<BigInteger> digits = getDigits(big);
		for(int i=0, j=digits.size()-1; i<j; i++, j--) {
			if (digits.get(i).equals(digits.get(j))) continue;
			return false;
		}
		
		return true;
	}
	
	/**
	 * @param big
	 * @return digits, in ascending order (i.e. [ones, tens, hundreds, ...])
	 */
	public static ArrayList<BigInteger> getDigits(BigInteger big) {
		ArrayList<BigInteger> digits = new ArrayList<>();
		
		BigInteger ten = BigInteger.valueOf(10);
		while(big.compareTo(BigInteger.ZERO) != 0) {
			BigInteger[] dr = big.divideAndRemainder(ten);
			digits.add(dr[1]);
			big = dr[0];
		}
		
		return digits;
	}
}
