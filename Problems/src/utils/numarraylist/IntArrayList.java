package utils.numarraylist;

import java.util.AbstractList;

public class IntArrayList extends AbstractList<Integer> {
	
	private final int[] array;

	public IntArrayList(int[] array) {
		this.array = array;
	}

	@Override
	public Integer get(int index) {
		return array[index];
	}

	@Override
	public int size() {
		return array.length;
	}

}
