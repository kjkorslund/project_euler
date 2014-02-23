package problems.p45;

public class P45 {
	/*
	 * Triangle, pentagonal, and hexagonal numbers are generated by the
	 * following formulae:
	 *    Triangle Tn=n(n+1)/2 1, 3, 6, 10, 15, ...
	 *    Pentagonal Pn=n(3n−1)/2 1, 5, 12, 22, 35, ...
	 *    Hexagonal Hn=n(2n−1) 1, 6, 15, 28, 45, ...
	 * 
	 * It can be verified that T(285) = P(165) = H(143) = 40755.
	 * 
	 * Find the next triangle number that is also pentagonal and hexagonal.
	 */
	public static void main(String[] args) {
//		System.out.println("T(285) = " + triangle(285));
//		System.out.println("P(165) = " + pentagonal(165));
//		System.out.println("H(143) = " + hexagonal(143));
		long cap = 100000;
		
		long nTriangle = 1;
		long nPentagonal = 1;
		long nHexagonal = 1;
		while(nTriangle < cap && nPentagonal < cap && nHexagonal < cap) {
			long fTriangle = triangle(nTriangle);
			long fPentagonal = pentagonal(nPentagonal);
			long fHexagonal = hexagonal(nHexagonal);
			
			if (fTriangle == fPentagonal && fTriangle == fHexagonal) {
				System.out.println("Match: T(" + nTriangle + ") = P("
					+ nPentagonal + ") = H(" + nHexagonal + ") = " + fTriangle);
			}
			
			switch (getMinSeries(fTriangle, fPentagonal, fHexagonal)) {
			case TRIANGLE:
				nTriangle++;
				break;
			case PENTAGONAL:
				nPentagonal++;
				break;
			case HEXAGONAL:
				nHexagonal++;
				break;
			}
			
		}
	}
	
	static long triangle(long n) {
		// Predetermined that anything above this value will exceed MAX_LONG
//		if (n < 1753413057) {
			return n*(n+1)/2;
//		}
//		throw new IndexOutOfBoundsException();
	}
	
	static long pentagonal(long n) {
		// Predetermined that anything above this value will exceed MAX_LONG
		if (n < 1753413057) {
			return n*(3*n-1)/2;
		}
		throw new IndexOutOfBoundsException();
	}
	
	static long hexagonal(long n) {
		// Predetermined that anything above this value will exceed MAX_LONG
//		if (n < 1753413057) {
			return n*(2*n-1);
//		}
//		throw new IndexOutOfBoundsException();
	}
	
	static Series getMinSeries(long triangle, long pentagonal, long hexagonal) {
		long min = triangle;
		Series minSeries = Series.TRIANGLE;
		if (pentagonal < min) {
			min = pentagonal;
			minSeries = Series.PENTAGONAL;
		}
		if (hexagonal < min) {
			min = hexagonal;
			minSeries = Series.HEXAGONAL;
		}
		return minSeries;
	}
	
	private static enum Series {
		TRIANGLE,
		PENTAGONAL,
		HEXAGONAL;
	}
}