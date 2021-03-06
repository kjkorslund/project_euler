package utils;

import java.util.Collections;
import java.util.List;

public class MathUtils {
	public static long pow(int base, int exponent) {
		long result = 1;
		for(int i=0; i<exponent; i++) {
			result *= base;
		}
		return result;
	}
	
	public static long pow(long base, long exponent) {
		long limit = Long.MAX_VALUE / base;
		long result = 1;
		for(int i=0; i<exponent; i++) {
			if (result > limit) {
				throw new IllegalArgumentException();
			}
			result *= base;
		}
		return result;
	}
	
	public static int sumUpTo(int num) {
		int result = 0;
		for(int i=0; i<=num; i++) result += i;
		return result;
	}
	
	public static long sumUpTo(long num) {
		int result = 0;
		for(int i=0; i<=num; i++) result += i;
		return result;
	}

	public static boolean isEven(long num) {
		return (num & 0x1) == 0;
	}
	
	public static long factorial(int n) {
		switch(n) {
		case 0:
		case 1:
			return 1;
		case 2:
			return 2;
		default:
			return n * factorial(n-1);
		}
	}
	
	public static long gcd(long a, long b) {
		List<Long> aDivisors = PrimeUtils.divisorsList(a);
		List<Long> bDivisors = PrimeUtils.divisorsList(b);
		Collections.sort(aDivisors,Collections.reverseOrder());
		Collections.sort(bDivisors,Collections.reverseOrder());
		for(int i=0,j=0; i<aDivisors.size() && j<bDivisors.size(); ) {
			long aVal = aDivisors.get(i);
			long bVal = bDivisors.get(j);
			if (aVal < bVal) {
				j++;
				continue;
			}
			if (aVal > bVal) {
				i++;
				continue;
			}
			return aVal;
		}
		// Should never reach this point, but just in case...
		return 1;
	}
	
	public static int sgn(long n) {
		if (n > 0) return 1;
		if (n < 0) return -1;
		return 0;
	}
	
	public static long min(long... ns) {
		switch(ns.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return ns[0];
		case 2:
			return Math.min(ns[0], ns[1]);
		default:
			long min = Math.min(ns[0], ns[1]);
			for(int i=2; i<ns.length; i++) {
				min = Math.min(min, ns[i]);
			}
			return min;
		}
	}
	
	public static long max(long... ns) {
		switch(ns.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return ns[0];
		case 2:
			return Math.max(ns[0], ns[1]);
		default:
			long max = Math.max(ns[0], ns[1]);
			for(int i=2; i<ns.length; i++) {
				max = Math.max(max, ns[i]);
			}
			return max;
		}
	}
}
