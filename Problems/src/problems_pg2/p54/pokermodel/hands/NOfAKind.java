package problems_pg2.p54.pokermodel.hands;

import java.util.Arrays;
import java.util.List;

import problems_pg2.p54.pokermodel.Card;
import problems_pg2.p54.pokermodel.CardRank;

public abstract class NOfAKind implements IRankSet {

	protected final CardRank rank;
	protected final Card[] cards;

	public NOfAKind(Card... cards) {
		if (cards.length == 0) throw new IllegalArgumentException();
		this.rank = cards[0].rank;
		
		for(int i=1; i<cards.length; i++) {
			if (cards[i].rank != this.rank) throw new IllegalArgumentException();
		}
		this.cards = cards;
	}
	
	@Override
	public List<Card> getCards() {
		return Arrays.asList(cards);
	}

	@Override
	public CardRank getSetRank() {
		return rank;
	}

	@Override
	public int getCount() {
		return cards.length;
	}

}
