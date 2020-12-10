package problems.p16;

import java.math.BigInteger;

public class P16 {
	/*
	 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
	 * 
	 * What is the sum of the digits of the number 2^1000?
	 */
	
	public static void main(String[] args) {
		BigInteger big = big2Pow(1000);
		System.out.println(big);
		System.out.println(sumDigits(big));
	}
	
	static long sumDigits(BigInteger big) {
		BigInteger ten = BigInteger.valueOf(10);
		long result = 0;
		while(big.compareTo(BigInteger.ZERO) != 0) {
			BigInteger[] dr = big.divideAndRemainder(ten);
			result = result + dr[1].longValue();
			big = dr[0];
		}
		return result;
	}
	
	static BigInteger big2Pow(int pow) {
		BigInteger result = BigInteger.valueOf(2L);
		result = result.pow(pow);
		return result;
	}
}
