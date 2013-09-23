package problems.p41;

import java.util.Arrays;
import java.util.List;

import utils.ArrayUtils;
import utils.DigitUtils;
import utils.PermIter;
import utils.PrimeUtils;

public class P41 {
	/*
	 * We shall say that an n-digit number is pandigital if it makes use of all
	 * the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital
	 * and is also prime.
	 * 
	 * What is the largest n-digit pandigital prime that exists?
	 */
	public static void main(String[] args) {
//		for(int i=123456789; i<987654321; i++) {
//			PrimeUtils.isPrime(i);
//			//DigitUtils.isPandigital(i);
//		}
//		System.out.println("done");
		
		PermIter<Integer> iter = new PermIter<Integer>(Arrays.asList(1,2,3,4,5,6,7));
		while(iter.hasNext()) {
			List<Integer> digits = iter.next();
			int num = (int)DigitUtils.composeFromDigits(ArrayUtils.toArray(digits));
			//if (!DigitUtils.isPandigital(num)) System.out.println(num);
			if (PrimeUtils.isPrime(num)) System.out.println(num);
		}
	}
	
	
}
