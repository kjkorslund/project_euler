package problems_pg2.p60;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import utils.DigitUtils;
import utils.PrimeUtils;

public class P60 {

	/*
	 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two
	 * primes and concatenating them in any order the result will always be
	 * prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The
	 * sum of these four primes, 792, represents the lowest sum for a set of
	 * four primes with this property.
	 * 
	 * Find the lowest sum for a set of five primes for which any two primes
	 * concatenate to produce another prime.
	 */
	public static void main(String[] args) {
//		validate(Arrays.asList(3L, 7L, 109L, 673L));
//		printResult(Arrays.asList(3L, 7L, 109L, 673L));
//		System.out.println();
		
		int targetSize = 5;
		long cap = 100000;
		long p = PrimeUtils.nextPrime(0);
		
		ResultsProcessor processor = new ResultsProcessor();
		HashSet<Long> previousPrimes = new HashSet<Long>();
		while(p <= cap) {
			previousPrimes.add(p);
			p = PrimeUtils.nextPrime(p);
			for (long pPrev : previousPrimes) {
				if (isRemarkablePair(p, pPrev)) {
					processor.addPair(p, pPrev);
				}
			}
			
			Result bestResult = processor.getBestResult(p, targetSize);
			if (bestResult != null) {
				System.out.println(p + " --> " + bestResult + ": " + isRemarkableSet(bestResult.primes));
//				printResult(bestResult);
			}
		}
	}
	
	private static void validate(Collection<Long> candidate) {
		boolean result = isRemarkableSet(candidate);
		System.out.println(candidate + ": " + result);
	}
	
	private static void printResult(Collection<Long> result) {
		long sum = 0;
		for(long l : result) {
			sum += l;
		}
		System.out.println("Result: " + sum + " " + result);
	}

	/**
	 * NOTE - for efficiency's sake, it is assumed that the caller has already
	 * verified that all inputs are prime!
	 * 
	 * @param primes
	 * @return
	 */
	private static boolean isRemarkableSet(Collection<Long> primes) {
		LinkedList<Long> primesList = new LinkedList<Long>(primes);
		
		while(!primesList.isEmpty()) {
			long p1 = primesList.removeFirst();
			for(long p2 : primesList) {
				if (!isRemarkablePair(p1, p2)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean isRemarkablePair(Long p1, Long p2) {
		long pConcat = DigitUtils.concatenateDigits(p1, p2);
		if (!PrimeUtils.isPrime(pConcat)) return false;
		
		pConcat = DigitUtils.concatenateDigits(p2, p1);
		return PrimeUtils.isPrime(pConcat);
	}
	
	private static class Result implements Comparable<Result> {
		private final TreeSet<Long> primes;
		private final long sum;

		public Result(Long... primes) {
			this(Arrays.asList(primes));
		}
		
		public Result(Collection<Long> primes) {
			this.primes = new TreeSet<Long>(primes);
			
			long sum = 0;
			for(long prime : this.primes) {
				sum += prime;
			}
			this.sum = sum;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			boolean isFirst = true;
			sb.append(sum).append(" (");
			for(long prime : primes) {
				if (isFirst) {
					sb.append(prime);
					isFirst = false;
					continue;
				}
				
				sb.append(" + ").append(prime);
			}
			sb.append(")");
			return sb.toString();
		}

		@Override
		public int compareTo(Result o) {
			return Long.compare(this.sum, o.sum);
		}
	}
	
	private static class ResultsProcessor {
		private final Map<Long, Set<Long>> pairsMap = new HashMap<Long, Set<Long>>();
		
		public void addPair(long p1, long p2) {
			Set<Long> p1Set = getOrCreatePairsSet(p1);
			Set<Long> p2Set = getOrCreatePairsSet(p2);
			p1Set.add(p2);
			p2Set.add(p1);
		}
		
		public Result getBestResult(long p, int targetSize) {
			Set<Long> pPairs = pairsMap.get(p);
			if (pPairs == null) return null;
			
			return getBestResult(Collections.singleton(p), pPairs, targetSize);
		}
		
		private Result getBestResult(Set<Long> pPartial, Set<Long> pPairs, int targetSize) {
			if (pPartial.size() + pPairs.size() < targetSize) return null;
			if (pPairs.isEmpty()) return new Result(pPartial);
			
			Result bestResult = null;
			for(long pair : pPairs) {
				Set<Long> pairPairs = pairsMap.get(pair);
				if (pairPairs == null) continue;
				
				Set<Long> newPartial = new HashSet<Long>(pPartial);
				newPartial.add(pair);
				
				Set<Long> newPairs = new HashSet<Long>(pPairs);
				newPairs.remove(pair);
				newPairs.retainAll(pairPairs);
				
				Result result = getBestResult(newPartial, newPairs, targetSize);
				if (result != null && (bestResult == null || result.sum < bestResult.sum)) {
					bestResult = result;
				}
			}
			return bestResult;
		}
		
		public Set<Long> getPairsSet(long p) {
			if (pairsMap.containsKey(p)) {
				return Collections.unmodifiableSet(pairsMap.get(p));
			}
			return Collections.emptySet();
		}
		
		private Set<Long> getOrCreatePairsSet(long p) {
			Set<Long> result = pairsMap.get(p);
			if (result == null) {
				result = new HashSet<Long>();
				pairsMap.put(p, result);
			}
			return result;
		}
	}
}
