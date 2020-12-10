package utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Note that two {@link LongFraction}s are only considered equals if they represent
 * the same fraction. For example, 3/6 does not equal 1/2 for the purposes of
 * the hashCode() and equals() methods.
 * 
 * @author Kevin
 * 
 */
public class LongFraction {
	public final long numerator;
	public final long denominator;

	public LongFraction(long numerator, long denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public DecimalRepresentation toDecimalRepresentation() {
		DecimalRepresentation result = new DecimalRepresentation();
		long n = numerator;
		result.integerPortion = n/denominator;
		long r = n%denominator;
		n = r*10;
		
		HashMap<Long,Integer> prevR = new HashMap<Long,Integer>();
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
	
	public LongFraction reduce() {
		int gcd = (int) MathUtils.gcd(numerator, denominator);
		return new LongFraction(numerator/gcd,denominator/gcd);
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (denominator ^ (denominator >>> 32));
		result = prime * result + (int) (numerator ^ (numerator >>> 32));
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
		LongFraction other = (LongFraction) obj;
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
		public long integerPortion = 0;
		public ArrayList<Long> fixedPortion = new ArrayList<Long>();
		public ArrayList<Long> repeatingPortion = new ArrayList<Long>();
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(integerPortion);
			sb.append('.');
			if (fixedPortion.size() + repeatingPortion.size() > 0) {
				for(Long l : fixedPortion) {
					sb.append(l);
				}
				if(repeatingPortion.size() > 0) {
					sb.append('(');
					for(Long l : repeatingPortion) {
						sb.append(l);
					}
					sb.append(')');
				}
			}
			return sb.toString();
		}
	}
}