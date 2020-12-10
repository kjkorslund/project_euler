package problems.p40;

import java.util.Arrays;

import utils.DigitUtils;

public class P40 {
	/*
	 * An irrational decimal fraction is created by concatenating the positive
	 * integers:
	 * 
	 * 0.12345678910[1]112131415161718192021...
	 * 
	 * It can be seen that the 12th digit of the fractional part is 1.
	 * 
	 * If d_n represents the nth digit of the fractional part, find the value of
	 * the following expression.
	 * 
	 * d_1 × d_10 × d_100 × d_1000 × d_10000 × d_100000 × d_1000000
	 */
	public static void main(String[] args) {
//		for(int i=0; i<20; i++) {
//			System.out.println(i + ": " + nthFractionalDigit(i));
//		}
//		System.out.println("0.");
//		for(int i=0; i<50; i++) {
//			System.out.print(nthFractionalDigit(i));
//		}
//		System.out.println();
		int[] digits = {
			nthFractionalDigit(1),
			nthFractionalDigit(10),
			nthFractionalDigit(100),
			nthFractionalDigit(1000),
			nthFractionalDigit(10000),
			nthFractionalDigit(100000),
			nthFractionalDigit(1000000),
		};
		System.out.println(Arrays.toString(digits));
		int product = 1;
		for(int digit : digits) {
			product *= digit;
		}
		System.out.println(product);
	}
	
	static int nthFractionalDigit(int num) {
		int digitCount = 0;
		int i = 1;
		int iDigits = 1;
		int iDigitInc = 10;
		while(digitCount < num) {
			digitCount += iDigits;
			i++;
			if (i == iDigitInc) {
				iDigits++;
				iDigitInc *= 10;
			}
		}
		
		int offset = digitCount - num;
		//System.out.println(num + ", " + offset);
		return nthDigit(i-1,offset);
	}
	
	static int nthDigit(int num, int n) {
		int digit = num%10;
		for(int i=0; i<n; i++) {
			num /=10;
			digit = num%10;
		}
		return digit;
	}
}
