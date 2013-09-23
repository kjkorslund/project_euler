package utils;


public class SearchUtils {
	public static interface Eval<T> {
		public int eval(T t);
	}
	
	public static long binarySearch(long min, long max, Eval<Long> eval) {
		while(max > min) {
			long center = (max - min) >> 1;
			int result = eval.eval(center);
			if (result < 0) {
				min = center;
			} else if (result > 0) {
				max = center;
			} else {
				// result == 0
				return center;
			}
		}
		throw new NotFoundException();
	}
	
	public static int binarySearch(int min, int max, Eval<Integer> eval) {
		// FIXME fails to terminate
		while(max > min) {
			int center = (max + min) >> 1;
			int result = eval.eval(center);
			if (result < 0) {
				min = center;
			} else if (result > 0) {
				max = center;
			} else {
				// result == 0
				return center;
			}
		}
		throw new NotFoundException();
	}

	public static class NotFoundException extends RuntimeException {
		public NotFoundException() {
			super();
			// TODO Auto-generated constructor stub
		}
	
		public NotFoundException(String arg0, Throwable arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}
	
		public NotFoundException(String arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
	
		public NotFoundException(Throwable arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
	}
}
