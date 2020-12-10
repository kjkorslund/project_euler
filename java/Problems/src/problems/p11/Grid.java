package problems.p11;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import utils.ArrayUtils;

public class Grid {
	public static Grid build(BufferedReader br) throws IOException {
		int cols = 0;
		int rows = 0;
		ArrayList<Integer> data = new ArrayList<Integer>();
		for(String line = br.readLine(); line != null; line = br.readLine()) {
			String[] nums = line.split("\\s+");
			for(String num : nums) {
				data.add(Integer.parseInt(num));
			}
			if (cols == 0) cols = nums.length;
			rows++;
		}
		
		Grid grid = new Grid(rows, cols);
		grid.populate(ArrayUtils.toArray(data));
		return grid;
	}
	
	public final int rows;
	public final int cols;
	int[] data;
	
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
	}
	
	public void populate(int... data) {
		this.data = data;
	}
	
	public int get(int row, int col) {
		return data[row*cols + col];
	}
}
