package problems_pg2.p54.pokermodel.hands;

import problems_pg2.p54.pokermodel.CardRank;

public interface IStraight extends IPokerHand {
	public CardRank getStart();
	public CardRank getEnd();
}
