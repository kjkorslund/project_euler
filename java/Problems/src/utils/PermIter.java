package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PermIter<T extends Comparable<? super T>> implements Iterator<List<T>> {

	List<T> nextPermutation;
	
	public PermIter(Collection<T> members) {
		nextPermutation = new ArrayList<T>(members);
		Collections.sort(nextPermutation);
	}
	
	@Override
	public boolean hasNext() {
		return nextPermutation != null;
	}

	@Override
	public List<T> next() {
		if (nextPermutation == null) throw new NoSuchElementException();
		
		ArrayList<T> result = new ArrayList<T>(nextPermutation);
		
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
			
			T temp = nextPermutation.get(k);
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

	@Override
	public void remove() {
		throw new UnsupportedOperationException("iterator does not support remove()");
	}

}
