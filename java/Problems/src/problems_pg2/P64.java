package problems_pg2;

import utils.MathUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem description has lots of formulas that don't translate to text; see website instead:
 *     <a href="https://projecteuler.net/problem=64">...</a>
 * <p>
 * Also, it helps to know what a 'continued fraction', is; the problem doesn't explain it very well:
 *     <a href="https://en.wikipedia.org/wiki/Continued_fraction">...</a>
 * <p>
 * Finally, the equations in the problem assume familiarity with the concept of 'rationalizing the denominator', which
 * I had of course forgotten; good writeup here:
 *     <a href="https://www.chilimath.com/lessons/intermediate-algebra/rationalizing-the-denominator/">...</a>
 * <p>
 * Key notes from the problem:
 * -   All square roots are periodic when written as continued fractions
 * -   Example:  sqrt(23) = [4;(1,3,1,8)]
 * -   Problem statement has solutions for the first ten _irrational_ square roots (2, 3, 4, 5, 7, 8, 10, 11, 12, 13)
 * -   The periodic part can have an even or odd period (number of digits in the period)
 * -   The first ten solutions have exactly four continued fractions with an odd period
 * -   Question:  How many solutions for N <= 10000 have an odd period?
 * <p>
 * STRATEGY - The problem statement doesn't clearly state the process to follow, but I figured it out:
 *  1. Split the square root into whole and fractional parts (the whole part is the largest whole number smaller than
 *     the root).  For example, the whole part of root(23) is 4, since 4 < root(23) < 5.  So the whole part is 4 and
 *     the fractional part is root(23) - 4.
 *  2. Take the reciprocal of the fractional part and find its whole and fractional parts.  For example, 1/(root(23)-4)
 *     has a whole part of 1 and a fractional part of (root(23)-3)/7.
 *  3. Repeat step 2 until the fractional part is a repeat of a previous fractional part.  The first whole part
 *     represents the whole portion of the root, and the remaining whole parts form a repeating sequence.
 * <p>
 *  Strategy for splitting the reciprocal into whole and fractional parts:  the whole part is most easily determined by
 *  using floating-point math.  For the fractional part, I did some algebra to derive formulas.  Fractional parts are in
 *  the form (root(r) - a) / b.  Given an original fractional part of (r,a0,b0), its reciprocal will have a whole part
 *  w1, and a fractional part (r,a1,b1) which can be derived as follows:
 *    b1 = (r - a0^2) / b0
 *    a1 = w1(b1) - a0
 *
 */
public class P64 {
    public static void main(String[] args) {
        P64 p64 = new P64();
        int oddPeriodCount = 0;
        for(int i=1; i<=10_000; i++) {
            RepeatingResult result = p64.computeFor(i);
            if (!MathUtils.isEven(result.period())) oddPeriodCount++;
        }
        System.out.println(oddPeriodCount);
    }

    private RepeatingResult computeFor(int r) {
        SplitResult sr = initialSplit(r);
        int w0 = sr.whole;
        FractionalPart fp0 = sr.remainder;
        List<Integer> repeatingParts = new ArrayList<>();

        do {
            sr = reciprocateAndSplit(sr.remainder);
            if (sr == null) break;
            repeatingParts.add(sr.whole);
        } while (!sr.remainder.equals(fp0));

        return new RepeatingResult(w0, repeatingParts);
    }

    private SplitResult initialSplit(int r) {
        int w = (int) Math.sqrt(r);
        return new SplitResult(w, new FractionalPart(r, w, 1));
    }

    private SplitResult reciprocateAndSplit(FractionalPart fp) {
        int w1 = (int)(1 / fp.toDouble());
        int b1 = (fp.r - fp.a*fp.a) / fp.b;
        if (b1 == 0) return null;

        int a1 = w1*b1 - fp.a;
        return new SplitResult(w1, new FractionalPart(fp.r, a1, b1));
    }

    /**
     * Representation of a fractional remainder for the problem, in the form:
     *   (sqrt(r) - a) / b
     */
    private record FractionalPart(int r, int a, int b) {
        public double toDouble() {
            return (Math.sqrt(r) - a) / b;
        }

        @Override
        public String toString() {
            if (a == 0 && b == 0) {
                return MessageFormat.format("√{0}", r);
            }
            if (a == 0) {
                return MessageFormat.format("√{0}/{1}", r, b);
            }
            if (b == 0) {
                return MessageFormat.format("√{0} - {1}", r, a);
            }
            return MessageFormat.format("(√{0} - {1})/{2}", r, a, b);
        }
    }

    private record SplitResult(int whole, FractionalPart remainder) {
        @Override
        public String toString() {
            return whole + " + " + remainder;
        }
    }

    private record RepeatingResult(int whole, List<Integer> repeatingSequence) {

        public int period() {
            return repeatingSequence.size();
        }

        @Override
        public String toString() {
            return "[" + whole + ";("
                    + repeatingSequence.stream().map(Object::toString).collect(Collectors.joining(","))
                    + ")]";
        }
    }
}
