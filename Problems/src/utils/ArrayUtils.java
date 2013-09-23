package utils;

import java.util.Collection;

public class ArrayUtils {
	public static int[] toArray(Collection<Integer> collection) {
		int[] result = new int[collection.size()];
		
		int index = 0;
		for(Integer i : collection) {
			result[index++] = i;
		}
		
		return result;
	}
	
	public static long[] toArrayL(Collection<Long> collection) {
		long[] result = new long[collection.size()];
		
		int index = 0;
		for(Long i : collection) {
			result[index++] = i;
		}
		
		return result;
	}
}
