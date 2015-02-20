package problems_pg2.p54.pokermodel.hands;

import java.util.AbstractList;
import java.util.List;

public class CompositeList<T> extends AbstractList<T> {
	private List<T>[] sublists;

	public CompositeList(List<T>... sublists) {
		this.sublists = sublists;
	}
	
	@Override
	public T get(int index) {
		int i = 0;
		int size = sublists[i].size();
		while(index >= size) {
			index -= size;
			size = sublists[++i].size();
		}
		return sublists[i].get(index);
	}

	@Override
	public int size() {
		int totalSize = 0;
		for(List<T> sublist : sublists) {
			totalSize += sublist.size();
		}
		return totalSize;
	}
	
}