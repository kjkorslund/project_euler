package problems.p42;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P42 {

	/*
	 * The nth term of the sequence of triangle numbers is given by, tn =
	 * ½n(n+1); so the first ten triangle numbers are:
	 * 
	 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
	 * 
	 * By converting each letter in a word to a number corresponding to its
	 * alphabetical position and adding these values we form a word value. For
	 * example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word
	 * value is a triangle number then we shall call the word a triangle word.
	 * 
	 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text
	 * file containing nearly two-thousand common English words, how many are
	 * triangle words?
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("src/problems/p42/words-modified.txt");
		
		int count = 0;
		int triCount = 0;
		Scanner s = new Scanner(f);
		while(s.hasNextLine()) {
			String word = s.nextLine();
			count++;
			if (isTriangleNum(computeWordScore(word))) {
				triCount++;
			}
		}
		System.out.println(count);
		System.out.println(triCount);
	}
	
	static boolean isTriangleNum(int num) {
		// tn = n*(n+1)/2
		int n=1;
		int tn = 1;
		while(tn < num) {
			n++;
			tn += n;
		}
		return tn == num;
	}
	
	static int computeWordScore(String word) {
		int score = 0;
		for(int i=0;i<word.length();i++) {
			int letterScore = (word.charAt(i) - 'A' + 1);
			score += letterScore;
		}
		return score;
	}

}
