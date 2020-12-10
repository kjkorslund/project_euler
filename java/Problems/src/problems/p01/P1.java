package problems.p01;

public class P1 {
	/*
	 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
	 * 
	 * Find the sum of all the multiples of 3 or 5 below 1000.
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(sumMultiples(10));
		System.out.println(sumMultiples(1000));
	}
	
	public static int sumMultiples(int cap) {
		int m3 = 3;
		int m5 = 5;
		int sum = 0;
		while(m3 < cap || m5 < cap) {
			if (m3 < m5) {
				sum = sum + m3;
				m3 += 3;
			}
			else if (m5 < m3) {
				sum = sum + m5;
				m5 += 5;
			}
			else {
				// m3 == m5
				sum = sum + m3;
				m3 += 3;
				m5 += 5;
			}
		}
		return sum;
	}

}
