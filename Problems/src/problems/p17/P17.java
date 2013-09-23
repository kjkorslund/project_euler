package problems.p17;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P17 {
	/*
	 * If the numbers 1 to 5 are written out in words: one, two, three, four,
	 * five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
	 * 
	 * If all the numbers from 1 to 1000 (one thousand) inclusive were written
	 * out in words, how many letters would be used?
	 * 
	 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and
	 * forty-two) contains 23 letters and 115 (one hundred and fifteen) contains
	 * 20 letters. The use of "and" when writing out numbers is in compliance
	 * with British usage.
	 */
	
	public static void main(String[] args) {
//		int num = 115;
//		String wordString = toWordString(num);
//		System.out.println(wordString);
//		String compacted = compact(wordString);
//		System.out.println(compacted);
//		System.out.println(compacted.length());
		
		int sum = 0;
		for(int i=1; i<=1000; i++) {
			sum = sum + compact(toWordString(i)).length();
		}
		System.out.println(sum);
	}
	
	static final Pattern PATTERN_NONLETTER = Pattern.compile("[^a-zA-Z]+");
	static String compact(String numStr) {
		Matcher m = PATTERN_NONLETTER.matcher(numStr);
		return m.replaceAll("");
	}
	
	static String toWordString(int num) {
		if (num < 1000) {
			if (num < 10) {
				return toWordStringOnes(num);
			}
			if (num < 20) {
				return toWordStringTeens(num-10);
			}
			if (num < 100) {
				StringBuilder sb = new StringBuilder(toWordStringTies(num / 10));
				int ones = num % 10;
				if (ones > 0) {
					sb.append('-');
					sb.append(toWordStringOnes(ones));
				}
				return sb.toString();
			}
			StringBuilder sb = new StringBuilder(toWordStringOnes(num / 100));
			sb.append(" hundred");
			int remainder = num % 100;
			if (remainder > 0) {
				sb.append(" and ");
				sb.append(toWordString(remainder));
			}
			return sb.toString();
		}
		return "one thousand";
	}
	
	static String toWordStringOnes(int num) {
		switch(num) {
		case 0: return "zero";
		case 1: return "one";
		case 2: return "two";
		case 3: return "three";
		case 4: return "four";
		case 5: return "five";
		case 6: return "six";
		case 7: return "seven";
		case 8: return "eight";
		case 9: return "nine";
		}
		throw new IllegalArgumentException();
	}
	
	static String toWordStringTeens(int num) {
		switch(num) {
		case 0: return "ten";
		case 1: return "eleven";
		case 2: return "twelve";
		case 3: return "thirteen";
		case 4: return "fourteen";
		case 5: return "fifteen";
		case 6: return "sixteen";
		case 7: return "seventeen";
		case 8: return "eighteen";
		case 9: return "nineteen";
		}
		throw new IllegalArgumentException();
	}
	
	static String toWordStringTies(int num) {
		switch(num) {
		case 1: return "ten";
		case 2: return "twenty";
		case 3: return "thirty";
		case 4: return "forty";
		case 5: return "fifty";
		case 6: return "sixty";
		case 7: return "seventy";
		case 8: return "eighty";
		case 9: return "ninety";
		}
		throw new IllegalArgumentException();
	}

}
