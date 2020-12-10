package problems_pg2.p58;

import java.util.Iterator;

import utils.PrimeUtils;

public class P58 {

	/*
	 * Starting with 1 and spiralling anticlockwise in the following way, a
	 * square spiral with side length 7 is formed.
	 * 
	 *    37 36 35 34 33 32 31
	 *    38 17 16 15 14 13 30
	 *    39 18  5  4  3 12 29
	 *    40 19  6  1  2 11 28
	 *    41 20  7  8  9 10 27
	 *    42 21 22 23 24 25 26
	 *    43 44 45 46 47 48 49
	 * 
	 * It is interesting to note that the odd squares lie along the bottom
	 * right diagonal, but what is more interesting is that 8 out of the 13
	 * numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈
	 * 62%.
	 * 
	 * If one complete new layer is wrapped around the spiral above, a square
	 * spiral with side length 9 will be formed. If this process is continued,
	 * what is the side length of the square spiral for which the ratio of
	 * primes along both diagonals first falls below 10%?
	 */
	public static void main(String[] args) {
		int prime = 0;
		int total = 1;
		DiagonalIterator iter = new DiagonalIterator();
		while (iter.next < Integer.MAX_VALUE) {
			Integer num = iter.next();
			if (PrimeUtils.isPrime(num)) {
				prime++;
			}
			total++;
			
//			System.out.println("Ratio: " + prime + "/" + total + " (" + num + ")");
//			if (iter.level == 1) {
//				System.out.println("Completes square: " + num);
//			}
			
			
			if (iter.level == 1 && prime > 0 && prime*10 < total) {
				System.out.println("Match found!  Ratio: " + prime + "/" + total);
				System.out.println("Side length: " + (iter.increment - 1));
				return;
			}
		}
		System.out.println("No match found.  Final ratio: " + prime + "/" + total);
		System.out.println("Side length: " + (iter.increment + 1));
	}

	private static class DiagonalIterator implements Iterator<Integer> {
		int next = 1;
		int increment = 2;
		int level = 0;
		
		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Integer next() {
			int current = next;
			
			next += increment;
			level++;
			if (level == 4) {
				level = 0;
				increment += 2;
			}
			
			return current;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
