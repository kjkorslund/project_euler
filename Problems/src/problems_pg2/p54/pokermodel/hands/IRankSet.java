package problems_pg2.p54.pokermodel.hands;

import problems_pg2.p54.pokermodel.CardRank;

public interface IRankSet extends IPokerHand {
	public CardRank getSetRank();
	public int getCount();
}
