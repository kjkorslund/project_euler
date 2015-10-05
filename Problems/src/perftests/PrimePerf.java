package perftests;

import utils.PrimeUtils;


public class PrimePerf {
	public static void main(String[] args) {
		long n = 100_000_000_000_000l;
		System.out.println("Searching for next prime after " + n + "...");
		long p = PrimeUtils.nextPrime(n);
		System.out.println("Testing prime " + p + "...");
		
		long t = System.currentTimeMillis();
		for(int i=0; i<32; i++) {
			PrimeUtils.isPrime(p);
		}
		t = System.currentTimeMillis() - t;
		
		System.out.println(p);
		System.out.println("Total time (ms): " + t);
	}
}
