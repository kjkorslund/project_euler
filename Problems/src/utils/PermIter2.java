package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This version is improved to properly permutate lists with duplicate digits.
 * It also doesn't require the list to be of a Comparable type. However, care
 * has to be taken, because the iterator will return duplicate entries.
 * 
 * @author Kevin
 * 
 * @param <T>
 */
public class PermIter2<T> implements Iterator<List<T>> {

	private final ArrayList<T> original;
	private final ArrayList<Integer> originalPerm = new ArrayList<>();
	
	private ArrayList<Integer> nextPermutation;
	
	public PermIter2(Collection<T> members) {
		original = new ArrayList<>(members);
		nextPermutation = new ArrayList<Integer>(members.size());
		for(int i=0; i<members.size(); i++) {
			originalPerm.add(i);
			nextPermutation.add(i);
		}
	}
	
	@Override
	public boolean hasNext() {
		return nextPermutation != null;
	}

	@Override
	public List<T> next() {
		if (nextPermutation == null) throw new NoSuchElementException();
		
		ArrayList<T> result = getResult();
		
		int k = -1;
		for(int i=0; i<nextPermutation.size()-1; i++) {
			if (nextPermutation.get(i).compareTo(nextPermutation.get(i+1)) > 0) continue;
			k = i;
		}
		
		if (k < 0) {
			nextPermutation = null;
		} else {
			int l = k+1;
			for(int i=l+1; i<nextPermutation.size(); i++) {
				if (nextPermutation.get(k).compareTo(nextPermutation.get(i)) > 0) continue;
				l = i;
			}
			
			Integer temp = nextPermutation.get(k);
			nextPermutation.set(k,nextPermutation.get(l));
			nextPermutation.set(l,temp);
			
			for(int i=k+1,j=nextPermutation.size()-1; i<j; i++,j--) {
				temp = nextPermutation.get(i);
				nextPermutation.set(i,nextPermutation.get(j));
				nextPermutation.set(j,temp);
			}
		}
		
		return result;
	}
	
	private ArrayList<T> getResult() {
		ArrayList<T> result = new ArrayList<>(original);
		for(int i=0; i<nextPermutation.size(); i++) {
			Integer index = nextPermutation.get(i);
			result.set(i, original.get(index));
		}
		return result;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("iterator does not support remove()");
	}

}
