package problems_pg2.p54.pokermodel.hands;

import problems_pg2.p54.pokermodel.Card;
import problems_pg2.p54.pokermodel.HandRank;

public class ThreeOfAKind extends NOfAKind {

	public ThreeOfAKind(Card first, Card second, Card third) {
		super(first, second, third);
	}
	
	@Override
	public HandRank getPokerRank() {
		return HandRank.THREE_OF_A_KIND;
	}
}
