package problems.p21;

import java.util.HashMap;

import utils.PrimeUtils;

public class P21 {
	/*
	 * Let d(n) be defined as the sum of proper divisors of n (numbers less than
	 * n which divide evenly into n).
	 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair
	 * and each of a and b are called amicable numbers.
	 * 
	 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22,
	 * 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1,
	 * 2, 4, 71 and 142; so d(284) = 220.
	 * 
	 * Evaluate the sum of all the amicable numbers under 10000.
	 */
	public static void main(String[] args) {
//		System.out.println(sumOfProperDivisors(220));
//		System.out.println(sumOfProperDivisors(284));
		
		//HashMap<Integer,Long> divisorMap = new HashMap<Integer, Long>();
		long sum = 0;
		long count = 0;
		for(long i=2; i<10000; i++) {
			long j = sumOfProperDivisors(i);
			if (i != j && sumOfProperDivisors(j) == i) {
				System.out.println("spd(" + i + ") == spd(" + j + ")");
				sum += i;
				count++;
			}
		}
		System.out.println(sum);
		System.out.println(count);
	}
	
	static long sumOfProperDivisors(long n) {
		long sum = 0;
		for(long divisor : PrimeUtils.divisors(n)) {
			if (divisor == n) continue;
			sum += divisor;
		}
		return sum;
	}
}
