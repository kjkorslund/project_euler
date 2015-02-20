package problems_pg2.p54.pokermodel.hands;

import problems_pg2.p54.pokermodel.Card;
import problems_pg2.p54.pokermodel.HandRank;

public class OnePair extends NOfAKind {

	public OnePair(Card first, Card second) {
		super(first,second);
	}
	
	@Override
	public HandRank getPokerRank() {
		return HandRank.ONE_PAIR;
	}
}
