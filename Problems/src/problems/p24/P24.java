package problems.p24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class P24 {
	/*
	 * A permutation is an ordered arrangement of objects. For example, 3124 is
	 * one possible permutation of the digits 1, 2, 3 and 4. If all of the
	 * permutations are listed numerically or alphabetically, we call it
	 * lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
	 * 
	 *     012 021 102 120 201 210
	 * 
	 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3,
	 * 4, 5, 6, 7, 8 and 9?
	 */
	public static void main(String[] args) {
		LexicalPermutationIterator lpi = new LexicalPermutationIterator(new HashSet<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));
		//while(lpi.hasNext()) System.out.println(lpi.next());
		List<Integer> permutation = null;
		for(int i=1; i<=1000000; i++) {
			permutation = lpi.next();
		}
		System.out.println(permutation);
		// 2783915460
	}

	static class LexicalPermutationIterator implements Iterator<List<Integer>> {
		
		Integer[] nextPermutation;
		
		public LexicalPermutationIterator(Set<Integer> digits) {
			ArrayList<Integer> digitArr = new ArrayList<Integer>(digits);
			Collections.sort(digitArr);
			nextPermutation = digitArr.toArray(new Integer[digitArr.size()]);
		}

		@Override
		public boolean hasNext() {
			return nextPermutation != null;
		}

		@Override
		public List<Integer> next() {
			if (nextPermutation == null) throw new NoSuchElementException();
			ArrayList<Integer> result = new ArrayList<Integer>(Arrays.asList(nextPermutation));
			
			int k = -1;
			for(int i=0; i<nextPermutation.length-1; i++) {
				if (nextPermutation[i] > nextPermutation[i+1]) continue;
				k = i;
			}
			
			if (k < 0) {
				nextPermutation = null;
			} else {
				int l = k+1;
				for(int i=l+1; i<nextPermutation.length; i++) {
					if (nextPermutation[k] > nextPermutation[i]) continue;
					l = i;
				}
				
				int temp = nextPermutation[k];
				nextPermutation[k] = nextPermutation[l];
				nextPermutation[l] = temp;
				
				for(int i=k+1,j=nextPermutation.length-1; i<j; i++,j--) {
					temp = nextPermutation[i];
					nextPermutation[i] = nextPermutation[j];
					nextPermutation[j] = temp;
				}
			}
			
			return result;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
