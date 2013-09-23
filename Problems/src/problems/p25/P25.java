package problems.p25;

import java.math.BigInteger;

import utils.BigMathUtils;

public class P25 {
	/*
	 * The Fibonacci sequence is defined by the recurrence relation:
	 * 
	 * Fn = Fn-1 + Fn-2, where F1 = 1 and F2 = 1.
	 * 
	 * Hence the first 12 terms will be:
	 * 
	 *     F1 = 1
	 *     F2 = 1
	 *     F3 = 2
	 *     F4 = 3
	 *     F5 = 5
	 *     F6 = 8
	 *     F7 = 13
	 *     F8 = 21
	 *     F9 = 34
	 *     F10 = 55
	 *     F11 = 89
	 *     F12 = 144
	 * 
	 * The 12th term, F12, is the first term to contain three digits.
	 * 
	 * What is the first term in the Fibonacci sequence to contain 1000 digits?
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger prev = BigInteger.valueOf(1);
		BigInteger prevprev = BigInteger.valueOf(1);
		BigInteger current = BigInteger.valueOf(2);;
		int index = 3;
		while(BigMathUtils.countDigits(current) < 1000) {
			prevprev = prev;
			prev = current;
			current = prevprev.add(prev);
			index++;
		}
		System.out.println(index);
//		System.out.println(BigMathUtils.countDigits(BigInteger.valueOf(1034)));
	}
	
	

}
