package problems_pg2.p54.pokermodel.hands;

import problems_pg2.p54.pokermodel.Card;
import problems_pg2.p54.pokermodel.HandRank;

public class HighCard extends NOfAKind {
	
	public HighCard(Card card) {
		super(card);
	}

	@Override
	public HandRank getPokerRank() {
		return HandRank.HIGH_CARD;
	}

}
