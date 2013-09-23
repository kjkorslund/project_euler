package utils;

public class IntegerMath {
	public static long MAX_LONG_ISQRT = 3037000499L;
	public static final int MAX_INTEGER_ISQRT = 46340;
	
	public static long isqrt(long num) {
		// [kjk] This method is based on Newton's method. The MAX_LONG_ISQRT cap
		// is to prevent overflow on the bounds check.
		long guess = (num+1) >> 1;
		while(guess > MAX_LONG_ISQRT || guess*guess > num) {
			guess = (guess + num/guess) >> 1;
		}
		return guess;
	}
	
	public static int isqrt(int num) {
		// [kjk] This method is based on Newton's method. The MAX_INTEGER_ISQRT
		// cap is to prevent overflow on the bounds check.
		long guess = (num+1) >> 1;
		while(guess > MAX_INTEGER_ISQRT || guess*guess > num) {
			guess = (guess + num/guess) >> 1;
		}
		return (int) guess;
	}
	
	// [kjk] for testing purposes only
	static int slowisqrt(int num) {
		if (num == 1) return 1;
		
		int i=2;
		while(i*i<=num) i++;
		return i-1;
	}
	
//	public static void main(String[] args) {
//		System.out.println("start");
//		for(int i=1; i<5000000; i++) {
//			isqrt(i);
//		}
//		System.out.println("done1");
//		for(int i=1; i<5000000; i++) {
//			slowisqrt(i);
//		}
//		System.out.println("done2");
//	}
}
