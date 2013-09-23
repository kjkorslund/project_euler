package problems.p26;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import utils.Fraction;
import utils.Fraction.DecimalRepresentation;
import utils.Range;

public class P26 {

	/*
	 * A unit fraction contains 1 in the numerator. The decimal representation
	 * of the unit fractions with denominators 2 to 10 are given:
	 * 
	 * 1/2 = 0.5
	 * 1/3 = 0.(3)
	 * 1/4 = 0.25
	 * 1/5 = 0.2
	 * 1/6 = 0.1(6)
	 * 1/7 = 0.(142857)
	 * 1/8 = 0.125
	 * 1/9 = 0.(1)
	 * 1/10 = 0.1
	 * 
	 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can
	 * be seen that 1/7 has a 6-digit recurring cycle.
	 * 
	 * Find the value of d < 1000 for which 1/d contains the longest recurring
	 * cycle in its decimal fraction part.
	 */
	/*
	 * From wikipedia: The period of 1/k for integer k is always <= k - 1.
	 * 
	 * Also, 
	 */
	public static void main(String[] args) {
		int maxRepeatingLength = 0;
		int mrlValue = 0;
		for(int i=2; i<1000; i++) {
			Fraction f = new Fraction(1,i);
			DecimalRepresentation dr = f.toDecimalRepresentation();
			//System.out.println(dr.repeatingPortion.size() + ": " + f + " --> " + dr);
			if (dr.repeatingPortion.size() > maxRepeatingLength) {
				maxRepeatingLength = dr.repeatingPortion.size();
				mrlValue = i;
			}
		}
		System.out.println(mrlValue + " (" + maxRepeatingLength + ")");
		
//		int maxLength = 0; 
//		int mlDigit = 2;
//		for(int i=2; i<1000; i++) {
//			//if (PrimeUtils.isPrime(i)) {
//				UnitFraction uf = new UnitFraction(i);
//				//System.out.println(i + " - " + uf.computeRecurringCycleLength() + " - " + uf.toString(30));
//				Range r = uf.computeRecurringCycleLength();
////				if (r != null) {
////					int len = r.end - r.start;
////					if (maxLength < len) {
////						maxLength = len;
////						mlDigit = i;
////						System.out.println(mlDigit + " - " + maxLength + r + " - " + uf.toString(100));
////					}
////				}
//				System.out.println(i + " - " + r + " - " + uf.toString(100));
//			//}
//		}
//		System.out.println(mlDigit + " - " + maxLength);
	}
	
	static void testReduce(int n, int d) {
		Fraction f = new Fraction(n,d);
		System.out.println(f + " --> " + f.reduce());
	}

	static class UnitFraction {
		public final int denominator;

		public UnitFraction(int denominator) {
			this.denominator = denominator;
		}
		
		public String toString(int maxDigits) {
			StringBuilder sb = new StringBuilder("0.");
			
			DigitIterator iter = new DigitIterator();
			for(int i=0; i<maxDigits; i++) {
				if (!iter.hasNext()) break;
				int digit = iter.next();
				sb.append(digit);
			}
			return sb.toString();
		}
		
		public Range computeRecurringCycleLength() {
			DigitIterator iter = new DigitIterator();
			ArrayList<Integer> digitList = new ArrayList<Integer>();

			while(iter.hasNext()) {
				int digit = iter.next();
				
				//for(int i=1; (i < denominator) && (i*2 < digitList.size()); i++) {
				for(int i=1; i*2 < digitList.size(); i++) {
					Range r = checkForRecurringCycle(digitList, i);
					if (r == null) continue;
					return r;
				}
				
				digitList.add(digit);
			}
			
			return null;
		}
		
		Range checkForRecurringCycle(List<Integer> digitList, int length) {
			int endIndex = digitList.size() - 1;
			int startIndex = endIndex - length;
			for(int i=0; endIndex-i > startIndex; i++) {
				if (digitList.get(endIndex-i) == digitList.get(startIndex-i)) {
					continue;
				}
				return null;
			}
			return new Range(startIndex+1-length,startIndex);
		}
		
		class DigitIterator implements Iterator<Integer> {
			BigInteger bigN = BigInteger.TEN;
			BigInteger bigD = BigInteger.valueOf(denominator);
			Integer nextDigit = nextDigit();

			@Override
			public boolean hasNext() {
				return nextDigit != null;
			}

			@Override
			public Integer next() {
				if (!hasNext()) throw new NoSuchElementException();
				
				int result = nextDigit;
				bigN = bigN.multiply(BigInteger.TEN);
				nextDigit = nextDigit();
				return result;
			}
			
			Integer nextDigit() {
				BigInteger[] dr = bigN.divideAndRemainder(bigD);
				int result = dr[0].remainder(BigInteger.TEN).intValue();
				if (result == 0 && BigInteger.ZERO.equals(dr[1])) return null;
				return result;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
	}
}
