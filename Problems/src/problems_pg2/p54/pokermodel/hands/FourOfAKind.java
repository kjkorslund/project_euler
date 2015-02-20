package problems_pg2.p54.pokermodel.hands;

import problems_pg2.p54.pokermodel.Card;
import problems_pg2.p54.pokermodel.HandRank;

public class FourOfAKind extends NOfAKind {

	public FourOfAKind(Card first, Card second, Card third, Card fourth) {
		super(first, second, third, fourth);
	}
	
	@Override
	public HandRank getPokerRank() {
		return HandRank.FOUR_OF_A_KIND;
	}
}
