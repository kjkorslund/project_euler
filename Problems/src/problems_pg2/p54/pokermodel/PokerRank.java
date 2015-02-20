package problems_pg2.p54.pokermodel;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * PokerRank represents the overall rank of a poker hand. It implements the
 * {@link Comparable} interface, to make it easy to compare two
 * {@link PokerRank}s to see which is the winner.
 * 
 * @author Kevin
 * 
 */
public class PokerRank implements Comparable<PokerRank> {
	public static PokerRank createFromCards(HandRank handRank, Collection<Card> matchingCards,
			Collection<Card> leftoverCards) {
		PokerRank pokerRank = new PokerRank(handRank);
		for (Card card : matchingCards) {
			pokerRank.matchingCardRanks.add(card.rank);
		}
		for (Card card : leftoverCards) {
			pokerRank.leftoverCardRanks.add(card.rank);
		}
		return pokerRank;
	}
	
	private final HandRank handRank;
	private final NavigableSet<CardRank> matchingCardRanks = new TreeSet<>();
	private final NavigableSet<CardRank> leftoverCardRanks = new TreeSet<>();

	public PokerRank(HandRank handRank, Collection<CardRank> matchingCards,
			Collection<CardRank> leftoverCards) {
		this(handRank);
		this.matchingCardRanks.addAll(matchingCards);
		this.leftoverCardRanks.addAll(leftoverCards);
	}
	
	private PokerRank(HandRank handRank) {
		this.handRank = handRank;
	}
	
	@Override
	public int compareTo(PokerRank o) {
		// [kjk] Compare by hand rank first, then by card rank within the set,
		// then by card rank outside the set.
		int result = handRank.compareTo(o.handRank);
		if (result != 0) return result;
		
		result = compareSets(matchingCardRanks, o.matchingCardRanks);
		if (result != 0) return result;
		
		return compareSets(leftoverCardRanks, o.leftoverCardRanks);
	}

	private <T extends Comparable<T>> int compareSets(NavigableSet<T> first, NavigableSet<T> second) {
		Iterator<T> firstIter = first.descendingIterator();
		Iterator<T> secondIter = second.descendingIterator();
		
		while(firstIter.hasNext() && secondIter.hasNext()) {
			T t1 = firstIter.next();
			T t2 = secondIter.next();
			int result = t1.compareTo(t2);
			if (result != 0) {
				return result;
			}
		}
		
		// [kjk] Shouldn't come up in this application, but longer arrays beat shorter arrays
		if (firstIter.hasNext()) return 1;
		if (secondIter.hasNext()) return -1;
		
		// [kjk] Sets are equal length and same elements, so they're equal.
		return 0;
	}
}
