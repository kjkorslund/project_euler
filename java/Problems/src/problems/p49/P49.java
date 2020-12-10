package problems.p49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import utils.ArrayUtils;
import utils.DigitUtils;
import utils.PermIter2;
import utils.PrimeUtils;

public class P49 {
	/*
	 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
	 * increases by 3330, is unusual in two ways:
	 *   i)  each of the three terms are prime, and
	 *   ii) each of the 4-digit numbers are permutations of one another.
	 * 
	 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit
	 * primes, exhibiting this property, but there is one other 4-digit
	 * increasing sequence.
	 * 
	 * What 12-digit number do you form by concatenating the three terms in this
	 * sequence?
	 */
	public static void main(String[] args) {
		// General strategy: since the numeric range is relatively small, we can
		// easily keep track of which numbers have already been visited. So, we
		// can iterate over the range of 4-digit numbers, and as long as it
		// hasn't already been visited, do the following:
		//   1) Iterate over all permutations of that number and collect them into a sorted set
		//   2) Iterate over the list to determine if any three permutations form an arithmetic sequence
		//   3) If they do, determine if they are all prime
		
		P49 p49 = new P49();
		for(int n=1001; n<10000; n++) {
			int[] result = p49.test(n);
			if (result == null) continue;
			
			System.out.println(Arrays.toString(result));
		}
		
	}

	private final HashSet<Integer> tested = new HashSet<>();
	
	private int[] test(int n) {
		if (tested.contains(n)) return null;
		
//		System.jout.println("Testing " + n);
		
		ArrayList<Integer> sortedPerms = getSortedPermutations(n);
		if (sortedPerms == null) return null;
		sortedPerms = new ArrayList<>(new LinkedHashSet<>(sortedPerms));
		
		for(int i=0; i<sortedPerms.size()-2; i++) {
			for(int j=i+1; j<sortedPerms.size()-1; j++) {
				int iVal = sortedPerms.get(i);
				int jVal = sortedPerms.get(j);
				int kVal = jVal*2 - iVal;
				if (sortedPerms.contains(kVal)) {
					// Found an arithmetic series
					if (PrimeUtils.isPrime(iVal) && PrimeUtils.isPrime(jVal)
							&& PrimeUtils.isPrime(kVal)) {
						System.out.println("  " + sortedPerms);
						return new int[] {iVal,jVal,kVal};
					}
				}
			}
		}
		
		return null;
	}
	
	private ArrayList<Integer> getSortedPermutations(int n) {
		int[] digits = DigitUtils.getDigits(n);
		HashSet<Integer> digitSet = new HashSet<>(ArrayUtils.asList(digits));
		if (digitSet.contains(0)) {
			return null;
		}
		
		PermIter2<Integer> permIter = new PermIter2<>(ArrayUtils.asList(digits));
		ArrayList<Integer> perms = new ArrayList<>();
		while(permIter.hasNext()) {
			List<Integer> permDigits = permIter.next();
			int perm = (int) DigitUtils.composeFromDigits(ArrayUtils.toArray(permDigits));
			perms.add(perm);
			tested.add(perm);
		}
		Collections.sort(perms);
		
		return perms;
	}
}
