package problems_pg2.p54.pokermodel;

import java.util.Arrays;

public class RankedCardSet {
	private final PokerRank rank;
	private final Card[] cards;

	public RankedCardSet(PokerRank rank, Card... cards) {
		this.rank = rank;
		this.cards = cards;
	}

	public PokerRank getRank() {
		return rank;
	}

	public Card[] getCards() {
		return Arrays.copyOf(cards, cards.length);
	}
}