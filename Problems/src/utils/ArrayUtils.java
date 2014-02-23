package utils;

import java.util.Collection;
import java.util.List;

import utils.numarraylist.IntArrayList;
import utils.numarraylist.LongArrayList;

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
	
	public static List<Integer> asList(int[] array) {
		return new IntArrayList(array);
	}
	
	public static List<Long> asList(long[] array) {
		return new LongArrayList(array);
	}
}
