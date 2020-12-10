package problems_pg2;

import utils.BigMathUtils;
import utils.DigitUtils;
import utils.MathUtils;

import java.math.BigInteger;
import java.util.HashSet;

/**
 * The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
 * <p>
 * How many n-digit positive integers exist which are also an nth power?
 */
public class P63 {
    public static void main(String[] args) {
        // Note:  the base has to be smaller than 10, because 10^n will always have n+1 digits (10^1 = 10(2),
        //        10^2 = 100(3), 10^3 == 1000(4), etc.)
        //
        // I can get all bases but 9 to converge within the confines of Long.  But base 9 seems to be tricky.
        // Maybe I could do something with BigInteger...pow() wouldn't be a problem, but digit counting would need
        // to be reimplemented.

        HashSet<BigInteger> uniqueResults = new HashSet<>();
        int matchCount = 0;
        for(long base = 1; base < 10; base++) {
            int exp = 1;
            BigInteger bigBase = BigInteger.valueOf(base);
            while(exp < 50) {
                BigInteger result = bigBase.pow(exp);
                long digitCount = BigMathUtils.countDigits(result);
                if (exp == digitCount) {
                    System.out.println("Match: " + base + "^" + exp + " = " + result + " (" + digitCount + ")");
                    uniqueResults.add(result);
                    matchCount++;
                }
                exp++;
            }
        }
        System.out.println("Match count: " + matchCount);
        System.out.println("Unique result count: " + uniqueResults.size());
    }
}
