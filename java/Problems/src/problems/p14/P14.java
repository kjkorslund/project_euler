package problems.p14;

import java.util.Iterator;
import java.util.NoSuchElementException;

import utils.MathUtils;

public class P14 {
	/*
	 * The following iterative sequence is defined for the set of positive
	 * integers:
	 * 
	 * 		n → n/2 (n is even)
	 * 		n → 3n + 1 (n is odd)
	 * 
	 * Using the rule above and starting with 13, we generate the following
	 * sequence:
	 * 
	 * 		13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
	 * 
	 * It can be seen that this sequence (starting at 13 and finishing at 1)
	 * contains 10 terms. Although it has not been proved yet (Collatz Problem),
	 * it is thought that all starting numbers finish at 1.
	 * 
	 * Which starting number, under one million, produces the longest chain?
	 * 
	 * NOTE: Once the chain starts the terms are allowed to go above one
	 * million.
	 */
	
	public static void main(String[] args) {
//		printSeq(13);
//		System.out.println(chainLength(13));
		
		System.out.println(numWithMaxChain(1000000));
	}
	
	static long numWithMaxChain(long cap) {
		long maxChainLen = 0;
		long result = 0;
		for(long l = 1; l < cap; l++) {
			long lChainLen = chainLength(l);
			if (lChainLen > maxChainLen) {
				maxChainLen = lChainLen;
				result = l;
			}
		}
		return result;
	}
	
	static long chainLength(long l) {
		long len = 0;
		SeqIter iter = new SeqIter(l);
		while(iter.hasNext()) {
			iter.next();
			len++;
		}
		return len;
	}
	
	static void printSeq(long start) {
		SeqIter iter = new SeqIter(start);
		if (iter.hasNext()) {
			System.out.print(iter.next());
			while(iter.hasNext()) {
				System.out.print(" -> " + iter.next());
			}
			System.out.println();
		}
	}
	
	static class SeqIter implements Iterator<Long> {
		long current;
		
		public SeqIter(long start) {
			if (start < 1)
				throw new IllegalArgumentException("start must be >= 1");
			this.current = start;
		}
		
		@Override
		public boolean hasNext() {
			return current > 0;
		}

		@Override
		public Long next() {
			if (hasNext()) {
				long result = current;
				if (current > 1) {
					if (MathUtils.isEven(current)) {
						current = current / 2;
						return result;
					}
					current = 3*current + 1;
					return result;
				}
				current = 0;
				return result;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("removal not supported");
		}
		
	}
}
