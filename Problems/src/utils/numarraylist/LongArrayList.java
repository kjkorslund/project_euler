package utils.numarraylist;

import java.util.AbstractList;

public class LongArrayList extends AbstractList<Long> {

	private final long[] array;

	public LongArrayList(long[] array) {
		this.array = array;
	}

	@Override
	public Long get(int index) {
		return array[index];
	}

	@Override
	public int size() {
		return array.length;
	}

}
