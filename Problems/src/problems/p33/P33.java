package problems.p33;

import java.util.HashSet;
import java.util.Set;

import utils.Fraction;

public class P33 {
	/*
	 * The fraction 49/98 is a curious fraction, as an inexperienced
	 * mathematician in attempting to simplify it may incorrectly believe that
	 * 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
	 * 
	 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
	 * 
	 * There are exactly four non-trivial examples of this type of fraction,
	 * less than one in value, and containing two digits in the numerator and
	 * denominator.
	 * 
	 * If the product of these four fractions is given in its lowest common
	 * terms, find the value of the denominator.
	 */
	public static void main(String[] args) {
//		Fraction f = new Fraction(49,98);
//		System.out.println(f + " --> " + f.reduce());
		
		HashSet<Fraction> results = new HashSet<Fraction>();
		for(int d=11; d<100; d++) {
			for(int n=10; n<d; n++) {
				Fraction f = new Fraction(n,d);
				Fraction fReduce = f.reduce();
				Set<Fraction> candidates = computeCandidates(f);
				for(Fraction candidate : candidates) {
					//System.out.println(f + " ==? " + candidate);
					if (fReduce.equals(candidate.reduce())) {
						System.out.println(f + " == " + candidate + " --> " + fReduce);
						results.add(f);
					}
				}
			}
		}
		
		int n = 1;
		int d = 1;
		for(Fraction result : results) {
			n = n*result.numerator;
			d = d*result.denominator;
		}
		Fraction f = new Fraction(n,d);
		System.out.println(f + " --> " + f.reduce());
	}
	
	static Set<Fraction> computeCandidates(Fraction f) {
		HashSet<Fraction> results = new HashSet<Fraction>();
		int n1 = f.numerator % 10;
		int n10 = f.numerator / 10;
		int d1 = f.denominator % 10;
		int d10 = f.denominator / 10;
		if (n1 != 0 && n1 == d1) results.add(new Fraction(n10,d10));
		if (n10 == d1) results.add(new Fraction(n1,d10));
		if (n1 == d10) results.add(new Fraction(n10,d1));
		if (n10 == d10) results.add(new Fraction(n1,d1));
		return results;
	}
}
