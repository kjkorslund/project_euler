package problems_pg2.p51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.DigitUtils;
import utils.PrimeUtils;

public class P51 {
	/*
	 * By replacing the 1st digit of the 2-digit number *3, it turns out that
	 * six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all
	 * prime.
	 * 
	 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this
	 * 5-digit number is the first example having seven primes among the ten
	 * generated numbers, yielding the family: 56003, 56113, 56333, 56443,
	 * 56663, 56773, and 56993. Consequently 56003, being the first member of
	 * this family, is the smallest prime with this property.
	 * 
	 * Find the smallest prime which, by replacing part of the number (not
	 * necessarily adjacent digits) with the same digit, is part of an eight
	 * prime value family.
	 */
	public static void main(String[] args) {
		// [General strategy]
		// - Iterate over primes (using nextPrime function from 50)
		// - Bucket digits from the prime
		// - Try replacing each bucket and see how many primes result
		// - First prime that matches 8 wins!
		
//		System.out.println(getLargestFamily(13));
//		System.out.println(getLargestFamily(56003));

		long prime = 1;
		int familySize = 1;
		while(prime < 200000) {	// Cap was discovered by guesswork
			PrimeFamily largestFamily = getLargestFamily(prime++);
			if (largestFamily == null) continue;
			int fs = largestFamily.members.size();
			if (fs > familySize) {
				System.out.println("Match: " + largestFamily);
				familySize = fs;
			}
		}
	}

	private static PrimeFamily getLargestFamily(long prime) {
		PrimeFamily largestFamily = null;
		for(PrimeFamily primeFamily : getPrimeFamilies(prime)) {
			if (largestFamily == null || largestFamily.members.size() < primeFamily.members.size()) {
				largestFamily = primeFamily;
			}
		}
		return largestFamily;
	}
	
	private static List<PrimeFamily> getPrimeFamilies(long prime) {
		ArrayList<PrimeFamily> results = new ArrayList<>();
		for(int i=0; i<10; i++) {
			PrimeFamily primeFamily = new PrimeFamily(prime, i);
			if (primeFamily.members.size() > 0) results.add(primeFamily);
		}
		return results;
	}
	
	private static class PrimeFamily {
		List<Long> members = new ArrayList<>();
		
		public PrimeFamily(long prime, int digit) {
			int[] digits = DigitUtils.getDigits((int) prime);
			ArrayList<Integer> indices = new ArrayList<>();
			for(int i=0; i<digits.length; i++) {
				if (digits[i] == digit) {
					indices.add(i);
				}
			}
			
			if (indices.size() > 0) {
				int[] memberDigits = Arrays.copyOf(digits, digits.length);
				for(int i=0; i<10; i++) {
					for(int index : indices) {
						memberDigits[index] = i;
					}
					if (memberDigits[0] == 0) {
						// Don't count members that have a leading zero
						continue;
					}
					long member = DigitUtils.composeFromDigits(memberDigits);
					if (PrimeUtils.isPrime(member)) {
						members.add(member);
					}
				}
			}
		}
		
		@Override
		public String toString() {
			return "PrimeFamily(" + members.size() + ")" + members;
		}
	}

}
