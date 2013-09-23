package problems.p39;

import java.util.Arrays;

import utils.IntegerMath;

public class P39 {
	/*
	 * If p is the perimeter of a right angle triangle with integral length
	 * sides, {a,b,c}, there are exactly three solutions for p = 120.
	 * 
	 * {20,48,52}, {24,45,51}, {30,40,50}
	 * 
	 * For which value of p <= 1000, is the number of solutions maximised?
	 */
	public static void main(String[] args) {
		int[] pCount = new int[1000];
		
		// [kjk] should be unnecessary but it's cheap so meh...
		Arrays.fill(pCount, 0);
		
		
		for(int a=1; a<999; a++) {
			for(int b=a; b+a < 1000; b++) {
				Integer c = computeHyp(a, b);
				if (c == null) continue;
				int p = a+b+c;
				if (p > 1000) continue;
				if (p == 420) System.out.println(Arrays.toString(new int[]{a,b,c}));
				pCount[p-1]++;
			}
		}
		
		int maxP = 0;
		int maxCount = 0;
		for(int i=0; i<1000; i++) {
			if (pCount[i] > 3) System.out.println(i+1 + ": " + pCount[i]);
			if (pCount[i] <= maxCount) continue;
			maxCount = pCount[i];
			maxP = i+1;
		}
		System.out.println(maxP + " --> " + maxCount);
	}
	
	static Integer computeHyp(int a, int b) {
		int aabb = a*a + b*b;
		int c = IntegerMath.isqrt(aabb);
		if (c*c == aabb) return c;
		return null;
	}
	
	static class Solution {
		final int a;
		final int b;
		final int c;
		
		public Solution(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			result = prime * result + c;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Solution other = (Solution) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			if (c != other.c)
				return false;
			return true;
		}
		
	}
}
