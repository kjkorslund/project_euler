package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PrimeUtils {
	public static long[] primeFactors(long input) {
		ArrayList<Long> list = new ArrayList<Long>();
		while(input > 1) {
			long primeFactor = firstPrimeFactor(input);
			list.add(primeFactor);
			input = input / primeFactor;
		}
		
		return ArrayUtils.toArrayL(list);
	}
	
	public static boolean isPrime(long num) {
		return (num > 1) && (firstPrimeFactor(num) == num);
	}
	
	public static long[] computePrimeFactors(long input) {
		long firstPrime = firstPrimeFactor(input);
		if (firstPrime < input) {
			long[] subsequentPrimes = computePrimeFactors(input/firstPrime);

			long[] result = new long[subsequentPrimes.length + 1];
			result[0] = firstPrime;
			System.arraycopy(subsequentPrimes, 0, result, 1, subsequentPrimes.length);
			subsequentPrimes = null;
			return result;
		}
		return new long[]{input};
	}
	
	public static long[] divisors(long num) {
		HashSet<Long> divisors = new HashSet<Long>();
		_computeDivisorsUsingPrimeFactors(num, divisors);
		divisors.add(1L);
		return ArrayUtils.toArrayL(divisors);
	}
	
	public static List<Long> divisorsList(long num) {
		HashSet<Long> divisors = new HashSet<Long>();
		_computeDivisorsUsingPrimeFactors(num, divisors);
		divisors.add(1L);
		return new ArrayList<Long>(divisors);
	}
	
	static long[] _computeDivisorsUsingPrimeFactors(long input, HashSet<Long> divisors) {
		// This is a modified version of computePrimeFactors (modified for efficiency's sake)
		long firstPrime = firstPrimeFactor(input);
		if (firstPrime < input) {
			long[] subsequentPrimes = _computeDivisorsUsingPrimeFactors(input/firstPrime,divisors);

			long[] result = new long[subsequentPrimes.length + 1];
			result[0] = firstPrime;
			System.arraycopy(subsequentPrimes, 0, result, 1, subsequentPrimes.length);
			subsequentPrimes = null;
			
			HashSet<Long> d = new HashSet<Long>(divisors);
			for(Long divisor : d) {
				divisors.add(firstPrime*divisor);
			}
			divisors.add(firstPrime);
			d = null;
			return result;
		}
		divisors.add(input);
		return new long[]{input};
		
		
//		if (primeFactors.length > 0) {
//			long firstPrime = primeFactors[0];
//			divisors.add(firstPrime);
//			if (primeFactors.length > 1) {
//				long[] subsequentPrimes = new long[primeFactors.length - 1];
//				System.arraycopy(primeFactors, 1, subsequentPrimes, 0, subsequentPrimes.length);
//				primeFactors = null;
//				for(long l : )
//				
//				computeDivisorsFromPrimeFactors(subsequentPrimes, divisors);
//			}
//		}
	}
	
	public static long firstPrimeFactor(long input) {
		if ((input & 1l) == 0l) return 2;
		for(long l = 3; l*l <= input; l+=2) {
			if (input%l == 0l) {
				return l;
			}
		}
		return input;
	}
	
	private static HashMap<Long, Long> nextPrimeMap = new HashMap<>();
	public static long nextPrime(long n) {
		return nextPrime(n,true);
	}
	public static long nextPrime(long n, boolean cacheResult) {
		if (nextPrimeMap.containsKey(n)) return nextPrimeMap.get(n);
		
		long l = n;
		while(!PrimeUtils.isPrime(++l));
		if (cacheResult) nextPrimeMap.put(n, l);
		return l;
	}
}
