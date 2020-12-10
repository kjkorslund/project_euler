package utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Note that two {@link Fraction}s are only considered equals if they represent
 * the same fraction. For example, 3/6 does not equal 1/2 for the purposes of
 * the hashCode() and equals() methods.
 * 
 * @author Kevin
 * 
 */
public class Fraction {
	public final int numerator;
	public final int denominator;

	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public DecimalRepresentation toDecimalRepresentation() {
		DecimalRepresentation result = new DecimalRepresentation();
		int n = numerator;
		result.integerPortion = n/denominator;
		int r = n%denominator;
		n = r*10;
		
		HashMap<Integer,Integer> prevR = new HashMap<Integer,Integer>();
		while(n != 0) {
			if (prevR.containsKey(n)) {
				int startIndex = prevR.get(n);
				while(result.fixedPortion.size() > startIndex) {
					result.repeatingPortion.add(result.fixedPortion.remove(startIndex));
				}
				return result;
			}
			prevR.put(n, result.fixedPortion.size());
			result.fixedPortion.add(n/denominator);
			r = n%denominator;
			n = r*10;
		}
		return result;
	}
	
	public Fraction reduce() {
		int gcd = (int) MathUtils.gcd(numerator, denominator);
		return new Fraction(numerator/gcd,denominator/gcd);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denominator;
		result = prime * result + numerator;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		if (denominator != other.denominator)
			return false;
		if (numerator != other.numerator)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}
	
	public static class DecimalRepresentation {
		public int integerPortion = 0;
		public ArrayList<Integer> fixedPortion = new ArrayList<Integer>();
		public ArrayList<Integer> repeatingPortion = new ArrayList<Integer>();
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(integerPortion);
			sb.append('.');
			if (fixedPortion.size() + repeatingPortion.size() > 0) {
				for(Integer i : fixedPortion) {
					sb.append(i);
				}
				if(repeatingPortion.size() > 0) {
					sb.append('(');
					for(Integer i : repeatingPortion) {
						sb.append(i);
					}
					sb.append(')');
				}
			}
			return sb.toString();
		}
	}
}