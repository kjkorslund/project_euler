package problems.p18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class P18 {
	/*
	 * By starting at the top of the triangle below and moving to adjacent
	 * numbers on the row below, the maximum total from top to bottom is 23.
	 * 
	 * 		[mini-triangle]
	 * 
	 * That is, 3 + 7 + 4 + 9 = 23.
	 * 
	 * Find the maximum total from top to bottom of the triangle below:
	 * 
	 * 		[see triangle.txt]
	 * 
	 * NOTE: As there are only 16384 routes, it is possible to solve this
	 * problem by trying every route. However, Problem 67, is the same challenge
	 * with a triangle containing one-hundred rows; it cannot be solved by brute
	 * force, and requires a clever method! ;o)
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		Triangle t = Triangle.createFromFile(new File("src/problems/p18/largetriangle.txt"));
		//t.print();
		System.out.println(t.computeMaxSums());
	}
	
	static class Triangle {
		public static Triangle createFromFile(File f) throws FileNotFoundException {
			Scanner scanner = new Scanner(f);
			ArrayList<String> lines = new ArrayList<String>();
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty()) continue;
				lines.add(line);
			}
			scanner.close();
			
			Triangle t = new Triangle(lines.size());
			int lIndex = 0;
			for(String line : lines) {
				Level level = t.levels[lIndex];
				scanner = new Scanner(line);
				int nIndex = 0;
				while(scanner.hasNextInt()) {
					level.nums[nIndex] = scanner.nextInt();
					nIndex++;
				}
				lIndex++;
			}
			return t;
		}
		
		final Level[] levels;
		
		public Triangle(int levels) {
			this.levels = new Level[levels];
			for(int i=0; i<levels; i++) {
				this.levels[i] = new Level(i);
			}
		}
		
		public void print() {
			for(Level level : levels) {
				System.out.println(level);
			}
		}
		
		public int computeMaxSums() {
			Level prevLevel = null;
			for(Level level : levels) {
				if (prevLevel == null) {
					level.maxSum[0] = level.nums[0];
				} else {
					level.maxSum[0] = prevLevel.maxSum[0] + level.nums[0];
					level.maxSum[level.size-1] = prevLevel.maxSum[level.size-2] + level.nums[level.size-1];
					for(int i=1; i<level.size-1; i++) {
						level.maxSum[i] = Math.max(prevLevel.maxSum[i-1], prevLevel.maxSum[i]) + level.nums[i];
					}
				}
				prevLevel = level;
			}
			
			int max = 0;
			for(int levelMax : levels[levels.length-1].maxSum) {
				max = Math.max(max, levelMax);
			}
			return max;
		}
	}
	
	static class Level {
		final int size;
		final int[] nums;
		final int[] maxSum;

		public Level(int levelID) {
			this.size = levelID + 1;
			this.nums = new int[size];
			this.maxSum = new int[size];
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (nums.length > 0) {
				sb.append(nums[0]);
				for (int i=1; i<nums.length; i++) {
					sb.append(' ');
					sb.append(nums[i]);
				}
			}
			return sb.toString();
		}
	}
}
