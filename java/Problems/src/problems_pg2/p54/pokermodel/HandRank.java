package problems_pg2.p54.pokermodel;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * PokerRank represents the overall rank of a poker hand. It implements the
 * {@link Comparable} interface, to make it easy to compare two
 * {@link HandRank}s to see which is the winner.
 * 
 * @author Kevin
 * 
 */
public class HandRank implements Comparable<HandRank> {
	public static HandRank fromCards(PokerHand handRank, Collection<Card> usedCards,
			Collection<Card> unusedCards) {
		HandRank pokerRank = new HandRank(handRank);
		for (Card card : usedCards) {
			pokerRank.usedCardRanks.add(card.rank);
		}
		for (Card card : unusedCards) {
			pokerRank.unusedCardRanks.add(card.rank);
		}
		return pokerRank;
	}
	
	private final PokerHand hand;
	private final NavigableSet<CardRank> usedCardRanks;
	private final NavigableSet<CardRank> unusedCardRanks;

	public HandRank(PokerHand hand, Collection<CardRank> usedCardRanks,
			Collection<CardRank> unusedCardRanks) {
		this(hand);
		this.usedCardRanks.addAll(usedCardRanks);
		this.unusedCardRanks.addAll(unusedCardRanks);
	}
	
	private HandRank(PokerHand hand) {
		this.hand = hand;
		this.usedCardRanks = new TreeSet<>(CardRank.comparatorAcesHigh());
		this.unusedCardRanks = new TreeSet<>(CardRank.comparatorAcesHigh());
	}
	
	@Override
	public int compareTo(HandRank o) {
		// [kjk] Compare by hand rank first, then by card rank within the set,
		// then by card rank outside the set.
		int result = hand.compareTo(o.hand);
		if (result != 0) return result;
		
		result = compareSets(usedCardRanks, o.usedCardRanks);
		if (result != 0) return result;
		
		return compareSets(unusedCardRanks, o.unusedCardRanks);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "HandRank{" + hand + ", " + usedCardRanks + ", " + unusedCardRanks + "}";
	}

	private static <T extends Comparable<T>> int compareSets(NavigableSet<T> first, NavigableSet<T> second) {
		Iterator<T> firstIter = first.descendingIterator();
		Iterator<T> secondIter = second.descendingIterator();
		
		while(firstIter.hasNext() && secondIter.hasNext()) {
			T t1 = firstIter.next();
			T t2 = secondIter.next();
			int result = first.comparator().compare(t1,t2);
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
