package problems.p11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class P11 {
	/*
	 * In the 20×20 grid below, four numbers along a diagonal line have been marked in red.
	 * 
	 * [grid.txt]
	 * 
	 * The product of these numbers is 26 × 63 × 78 × 14 = 1788696.
	 * 
	 * What is the greatest product of four adjacent numbers in any direction (up, down, left, right, or diagonally) in the 20×20 grid?
	 */
	

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws IOException {
		Grid grid = Grid.build(new BufferedReader(new FileReader(new File("src/problems/p11/grid.txt"))));
		System.out.println(maxLineProduct(grid));
	}
	
	static int maxLineProduct(Grid grid) {
		int max = 0;
		for(int r=0; r<grid.rows; r++) {
			for (int c=0; c<grid.cols; c++) {
				// right
				if (c+3 < grid.cols) {
					int product = grid.get(r, c) * grid.get(r, c+1) * grid.get(r, c+2) * grid.get(r,c+3);
					max = Math.max(max, product);
				}
				// down
				if (r+3 < grid.rows) {
					int product = grid.get(r, c) * grid.get(r+1, c) * grid.get(r+2, c) * grid.get(r+3,c);
					max = Math.max(max, product);
				}
				// downright
				if (r+3 < grid.rows && c+3 < grid.cols) {
					int product = grid.get(r, c) * grid.get(r+1, c+1) * grid.get(r+2, c+2) * grid.get(r+3,c+3);
					max = Math.max(max, product);
				}
				// downleft
				if (r+3 < grid.rows && c >= 3) {
					int product = grid.get(r, c) * grid.get(r+1, c-1) * grid.get(r+2, c-2) * grid.get(r+3,c-3);
					max = Math.max(max, product);
				}
			}
		}
		return max;
	}

}
