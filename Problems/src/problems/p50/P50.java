package problems.p50;

import java.util.Arrays;
import java.util.HashMap;

import utils.PrimeUtils;

public class P50 {
	/*
	 * The prime 41, can be written as the sum of six consecutive primes:
	 * 
	 *    41 = 2 + 3 + 5 + 7 + 11 + 13
	 * 
	 * This is the longest sum of consecutive primes that adds to a prime below
	 * one-hundred.
	 * 
	 * The longest sum of consecutive primes below one-thousand that adds to a
	 * prime, contains 21 terms, and is equal to 953.
	 * 
	 * Which prime, below one-million, can be written as the sum of the most
	 * consecutive primes?
	 */
	public static void main(String[] args) {
		int length = 6;
		
		while(length < 1000) {
			long start = 1;
			Result result = getResult(start, length);
			while(result.sum < 1000000) {
				if (PrimeUtils.isPrime(result.sum)) {
					System.out.println("Match:  " + result);
					break;
				}
				
				start = nextPrime(start);
				result = getResult(start, length);
			}
			length++;
		}
	}
	
	private static Result getResult(long start, int length) {
		Result result = new Result(new long[length],0);
		long prime = start;
		
		for(int i=0; i<length; i++) {
			result.consecutivePrimes[i] = prime;
			result.sum += prime;
			prime = nextPrime(prime);
		}
		return result;
	}
	
	private static HashMap<Long, Long> nextPrimeMap = new HashMap<>();
	private static long nextPrime(long n) {
		if (nextPrimeMap.containsKey(n)) return nextPrimeMap.get(n);
		
		long l = n;
		while(!PrimeUtils.isPrime(++l));
		nextPrimeMap.put(n, l);
		return l;
	}
	
	private static class Result {
		long[] consecutivePrimes;
		long sum;
		
		public Result(long[] consecutivePrimes, long sum) {
			this.consecutivePrimes = consecutivePrimes;
			this.sum = sum;
		}
		
		@Override
		public String toString() {
			return sum + "=sum(" + consecutivePrimes.length + ":" + Arrays.toString(consecutivePrimes) + ")";
		}
	}

}
