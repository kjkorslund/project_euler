package problems_pg2.p54.pokermodel.hands;

import java.util.List;

import problems_pg2.p54.pokermodel.Card;
import problems_pg2.p54.pokermodel.HandRank;

public class TwoPairs implements IPokerHand {
	
	public final OnePair lower;
	public final OnePair higher;

	public TwoPairs(OnePair lower, OnePair higher) {
		this.lower = lower;
		this.higher = higher;
	}

	@Override
	public HandRank getPokerRank() {
		return HandRank.TWO_PAIRS;
	}

	@Override
	public List<Card> getCards() {
		return new CompositeList<Card>(lower.getCards(), higher.getCards());
	}
}
