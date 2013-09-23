package utils;

import java.math.BigInteger;

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
}
