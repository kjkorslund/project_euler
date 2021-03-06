package problems.p12;

import java.util.Iterator;

import utils.PrimeUtils;

public class P12 {
	/*
	 * The sequence of triangle numbers is generated by adding the natural
	 * numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 =
	 * 28. The first ten terms would be:
	 * 
	 *    1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
	 * 
	 * Let us list the factors of the first seven triangle numbers:
	 * 
	 *     1: 1
	 *     3: 1,3
	 *     6: 1,2,3,6
	 *    10: 1,2,5,10
	 *    15: 1,3,5,15
	 *    21: 1,3,7,21
	 *    28: 1,2,4,7,14,28
	 * 
	 * We can see that 28 is the first triangle number to have over five
	 * divisors.
	 * 
	 * What is the value of the first triangle number to have over five hundred
	 * divisors?
	 */
	
	public static void main(String[] args) {
		System.out.println(firstTriangleWithNDivisors(500));
	}
	
	static long firstTriangleWithNDivisors(final int target) {
		TriangleIter iter = new TriangleIter();
		int nDivisors = 0;
		int maxLevel = 0;
		long tri = 0;
		while(nDivisors < target) {
			tri = iter.next();
			//System.out.println("divisors[" + tri + "] = " + Arrays.toString(PrimeUtils.divisors(tri)));
			nDivisors = PrimeUtils.divisors(tri).length;
			int level = (nDivisors*10)/target;
			if (level > maxLevel) {
				maxLevel = level;
				System.out.println(level + " - " + (target*level)/10);
			}
		}
		return tri;
	}
	
	
	static long getTriangle(int nth) {
		long sum = 0;
		for(long add = nth; add > 0; add--) {
			sum += add;
		}
		return sum;
	}
	
//	static class TriangleCache {
//		
//		ArrayList<Long> cache = new ArrayList<Long>();
//		
//		public TriangleCache() {
//			cache.add(1L);
//		}
//		
//		long getTriangle(int nth) {
//			while(cache.size() < nth) {
//				cache.add(cache.get(cache.size()-1) + cache.size() + 1);
//			}
//			return cache.get(nth-1);
//		}
//	}
	
	static class TriangleIter implements Iterator<Long> {
		long current = 1;
		long add = 2;
		
		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Long next() {
			current = current + add;
			add++;
			return current;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
