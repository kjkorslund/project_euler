package problems_pg2.p60;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import utils.DigitUtils;
import utils.PrimeUtils;

public class P60 {

	/*
	 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two
	 * primes and concatenating them in any order the result will always be
	 * prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The
	 * sum of these four primes, 792, represents the lowest sum for a set of
	 * four primes with this property.
	 * 
	 * Find the lowest sum for a set of five primes for which any two primes
	 * concatenate to produce another prime.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isRemarkable(Arrays.asList(3, 7, 109, 673)));
	}

	/**
	 * NOTE - for efficiency's sake, it is assumed that the caller has already
	 * verified that all inputs are prime!
	 * 
	 * @param primes
	 * @return
	 */
	private static boolean isRemarkable(Collection<Integer> primes) {
		LinkedList<Integer> primesList = new LinkedList<Integer>(primes);
		
		while(!primesList.isEmpty()) {
			int p1 = primesList.removeFirst();
			for(int p2 : primesList) {
				long pConcat = DigitUtils.concatenateDigits(p1, p2);
				if (!PrimeUtils.isPrime(pConcat)) {
					return false;
				}
			}
		}
		return true;
	}
	
//	private static boolean 
}
