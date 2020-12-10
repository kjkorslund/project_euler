package problems_pg2;

import utils.IntegerMath;

/**
 * Problem description has lots of formulas that don't translate to text; see website instead:
 *     https://projecteuler.net/problem=64
 *
 * Also, it helps to know what a 'continued fraction', is; the problem doesn't explain it very well:
 *     https://en.wikipedia.org/wiki/Continued_fraction
 *
 * Finally, the equations in the problem assume familiarity with the concept of 'rationalizing the denominator', which
 * I had of course forgotten; good writeup here:
 *     https://www.chilimath.com/lessons/intermediate-algebra/rationalizing-the-denominator/
 *
 * Key notes from the problem:
 * -   All square roots are periodic when written as continued fractions
 * -   Example:  sqrt(23) = [4;(1,3,1,8)]
 * -   Problem statement has solutions for the first ten _irrational_ square roots (2, 3, 4, 5, 7, 8, 10, 11, 12, 13)
 * -   The periodic part can have an even or odd period (number of digits in the period)
 * -   The first ten solutions have exactly four continued fractions with an odd period
 * -   Question:  How many solutions for N <= 10000 have an odd period?
 */
public class P64 {
    public static void main(String[] args) {
        for(int i=1; i<14;i++) {
            System.out.println(i + ": " + IntegerMath.isqrt(i));
        }
    }
}
