package problems.p22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class P22 {
	/*
	 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text
	 * file containing over five-thousand first names, begin by sorting it into
	 * alphabetical order. Then working out the alphabetical value for each
	 * name, multiply this value by its alphabetical position in the list to
	 * obtain a name score.
	 * 
	 * For example, when the list is sorted into alphabetical order, COLIN,
	 * which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
	 * So, COLIN would obtain a score of 938 × 53 = 49714.
	 * 
	 * What is the total of all the name scores in the file?
	 */
	public static void main(String[] args) throws Exception {
		//System.out.println(computeNameScore("COLIN"));
		
		File inputFile = new File("src/problems/p22/names-modified.txt");
		
		ArrayList<String> names = readInput(inputFile);
		System.out.println(names.size());
		Collections.sort(names);
		
		long nameScoreSum = 0;
		for(int i=0; i<names.size(); i++) {
			String name = names.get(i);
			long score = (i+1)*computeNameScore(name);
			nameScoreSum += score;
		}
		System.out.println(nameScoreSum);
	}
	
	static int computeNameScore(String name) {
		int score = 0;
		for(int i=0;i<name.length();i++) {
			int letterScore = (name.charAt(i) - 'A' + 1);
			score += letterScore;
		}
		return score;
	}

	static ArrayList<String> readInput(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		ArrayList<String> results = new ArrayList<String>();
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			results.add(line);
		}
		s.close();
		return results;
	}
}
