package problems.p15;

import java.util.ArrayList;

public class P15 {
	/*
	 * Starting in the top left corner of a 2×2 grid, there are 6 routes (without backtracking) to the bottom right corner.
	 * 
	 * [diagram]
	 * 
	 * How many routes are there through a 20×20 grid?
	 */
	
	public static void main(String[] args) {
		Grid grid = new Grid(21);
		System.out.println(grid.computeRoutes());
	}
	
	static class Grid {
		final int sideLength;
		final Long[] data;
		
		public Grid(int sideLength) {
			this.sideLength = sideLength;
			data = new Long[sideLength*sideLength];
			data[0] = 1L;
		}
		
		public Long get(int row, int col) {
			return data[row*sideLength + col];
		}
		
		public Long set(int row, int col, long val) {
			int index = row*sideLength + col;
			Long old = data[index];
			data[index] = val;
			return old;
		}
		
		public long computeRoutes() {
			return computeRoutes(sideLength-1,sideLength-1);
		}
		
		long computeRoutes(int row, int col) {
			if (get(row,col) == null) {
				long routes = 0;
				if (row > 0) {
					routes = routes + computeRoutes(row-1,col);
				}
				if (col > 0) {
					routes = routes + computeRoutes(row,col-1);
				}
				set(row,col,routes);
			}
			return get(row,col);
		}
	}
}
