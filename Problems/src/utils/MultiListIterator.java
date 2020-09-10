package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MultiListIterator<T> implements Iterator<List<T>> {
    // *** WIP incomplete! ***

    private final List<ListState> listStates;
    private final List<ListState> listStatesReversed;

    public MultiListIterator(List<T>... lists) {
        this(Arrays.asList(lists));
    }

    public MultiListIterator(List<List<T>> lists) {
        this.listStates = new ArrayList<>(lists.size());
        for(List<T> list : lists) {
            listStates.add(new ListState(list));
        }

        this.listStatesReversed = new ArrayList<>(listStates);
        Collections.reverse(listStatesReversed);
    }

    @Override
    public boolean hasNext() {
        return !listStates.get(0).isDepleted();
    }

    @Override
    public List<T> next() {
        List<T> result = new ArrayList<>(listStates.size());
        for(ListState listState : listStates) {
            result.add(listState.get());
        }

        for(ListState listState : listStatesReversed) {
            listState.advance();
            if (listState.isDepleted() && listState != listStates.get(0)) {
                listState.reset();
                continue;
            }
            return result;
        }

        for(ListState listState : listStatesReversed) {
            if (listState.hasMore()) {
                listState.advance();
                return result;
            } else {
                listState.reset();
            }
        }

        return result;
    }

    private class ListState {
        private final List<T> list;
        private int cursor;

        public ListState(List<T> list) {
            this.list = list;
            this.cursor = 0;
        }

        public boolean hasMore() {
            return cursor+1 < list.size();
        }

        public boolean isDepleted() {
            return cursor >= list.size();
        }

        public void advance() {
            cursor++;
        }

        public void reset() {
            cursor = 0;
        }

        public T get() {
            return list.get(cursor);
        }
    }
}
