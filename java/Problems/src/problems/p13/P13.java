package problems.p13;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import utils.ArrayUtils;
import utils.MathUtils;

public class P13 {
	/*
	 * Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
	 * 
	 * [see numbers.txt]
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		// Basic strategy: sum first ten digits of each number, and throw away
		// the overflow
		File inputFile = new File("src/problems/p13/numbers.txt");
//		long[] inputs = readInputFirstTenDigits(inputFile);
//		System.out.println(sumDiscardingOverflow(inputs, 10));
//		System.out.println(sumDiscardingOverflow(inputs, 2));
//		for(int i=0; i<10; i++) {
//			for(int j=0; j<i; j++) System.out.print(" ");
//			System.out.println(sumDiscardingOverflow(inputs, 10-i));
//		}
		
		BigInteger[] numbers = readInput(inputFile);
		BigInteger sum = BigInteger.ZERO;
		for(BigInteger num : numbers) {
			sum = sum.add(num);
		}
		
		System.out.println(sum);
	}
	
	private static final Pattern LAST_TEN_DIGITS = Pattern.compile("\\d{10}+");
	
	static long sumDiscardingOverflow(long[] inputs, int digits) {
		long cap = MathUtils.pow(10, digits);
		long sum = 0;
		for(long l : inputs) {
//			System.out.println(l + ":");
//			System.out.println("  " + sum + " + " + l + " = " + (sum+l));
//			System.out.println("    % " + cap + " = " + ((sum+l)%cap));
//			System.out.println();
			sum = (sum + l) % cap;
		}
		return sum;
	}
	
	static BigInteger[] readInput(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		ArrayList<BigInteger> results = new ArrayList<BigInteger>();
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			results.add(new BigInteger(line));
		}
		s.close();
		return results.toArray(new BigInteger[results.size()]);
	}
	
	static long[] readInputFirstTenDigits(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		ArrayList<Long> results = new ArrayList<Long>();
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String lastTen = line.substring(line.length() - 10);
			
			//System.out.println(line + " -> " + Long.parseLong(lastTen));
			results.add(Long.parseLong(lastTen));
		}
		s.close();
		return ArrayUtils.toArrayL(results);
	}

}
