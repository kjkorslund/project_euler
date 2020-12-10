package problems.p35;

import java.util.HashSet;
import java.util.Set;

import utils.DigitUtils;
import utils.PrimeUtils;

public class P35 {

	/*
	 * The number, 197, is called a circular prime because all rotations of the
	 * digits: 197, 971, and 719, are themselves prime.
	 * 
	 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37,
	 * 71, 73, 79, and 97.
	 * 
	 * How many circular primes are there below one million?
	 */
	public static void main(String[] args) {
		HashSet<Integer> checked = new HashSet<Integer>();
		HashSet<Integer> matches = new HashSet<Integer>();
		
		//System.out.println(PrimeUtils.isPrime(103));
		
		for(int num=1; num<1000000; num++) {
			//System.out.println(num);
			if (checked.contains(num)) continue;
			
			Set<Integer> rotations = rotations(num);
			boolean isMatch = true;
			for(int rotation : rotations) {
				if (PrimeUtils.isPrime(rotation)) continue;
				isMatch = false;
				break;
			}
			
			if (isMatch) matches.addAll(rotations);
			checked.addAll(rotations);
		}
		System.out.println(matches.size());
	}
	
	static Set<Integer> rotations(int num) {
		HashSet<Integer> results = new HashSet<Integer>();
		
		// [kjk] Have to inline the rotateDigits() function because of zero digits
		int[] digits = DigitUtils.getDigits(num);
		for(int j=0; j<digits.length; j++) {
			int d0 = digits[0];
			for(int i=1; i<digits.length; i++) {
				digits[i-1] = digits[i];
			}
			digits[digits.length - 1] = d0;
			results.add((int)DigitUtils.composeFromDigits(digits));
		}
		
		return results;
	}
	
	static int rotateDigits(int num) {
		int[] digits = DigitUtils.getDigits(num);
		int d0 = digits[0];
		for(int i=1; i<digits.length; i++) {
			digits[i-1] = digits[i];
		}
		digits[digits.length - 1] = d0;
		return (int) DigitUtils.composeFromDigits(digits);
	}

}
