package problems_pg2.p54.pokermodel;

import java.util.Arrays;

public class PokerHand implements Comparable<PokerHand> {

	private final Card[] cards;
	
	public PokerHand(Card... cards) {
		this.cards = Arrays.copyOf(cards, cards.length);
	}

	public Card[] getCards() {
		return Arrays.copyOf(cards, cards.length);
	}
	
	public RankedCardSet getHighestRankedCardSet() {
		
	}

	@Override
	public int compareTo(PokerHand o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
