package problems.p09;

import java.util.Arrays;

public class P9 {
	/*
	 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
	 * 
	 *    a^2 + b^2 = c^2
	 * 
	 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
	 * 
	 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
	 * Find the product abc.
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] triplets = pyTriplets(1000);
		if (triplets == null) System.out.println("no answer");
		else {
			System.out.println(Arrays.toString(triplets));
			System.out.println(triplets[0]*triplets[1]*triplets[2]);
		}
	}
	
	

	static int[] pyTriplets(int targetSum) {
		for (int c = 0; c < targetSum; c++) {
			for(int b = 0; b < c; b++) {
				for (int a = 0; a < b; a++) {
					if ((a+b+c == targetSum) && (a*a + b*b == c*c)) {
						return new int[]{a,b,c};
					}
				}
			}
		}
		return null;
	}
}
