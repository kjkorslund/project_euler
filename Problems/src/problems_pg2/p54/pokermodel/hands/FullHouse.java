package problems_pg2.p54.pokermodel.hands;

import java.util.List;

import problems_pg2.p54.pokermodel.Card;
import problems_pg2.p54.pokermodel.HandRank;

public class FullHouse implements IPokerHand {
	
	private ThreeOfAKind threeOfAKind;
	private OnePair pair;

	public FullHouse(ThreeOfAKind threeOfAKind, OnePair pair) {
		this.threeOfAKind = threeOfAKind;
		this.pair = pair;
	}

	@Override
	public HandRank getPokerRank() {
		return HandRank.FULL_HOUSE;
	}

	@Override
	public List<Card> getCards() {
		return new CompositeList<Card>(threeOfAKind.getCards(), pair.getCards());
	}
}
