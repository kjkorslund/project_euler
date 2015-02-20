package problems_pg2.p54.pokermodel.hands;

import java.util.List;

import problems_pg2.p54.pokermodel.Card;
import problems_pg2.p54.pokermodel.HandRank;

public interface IPokerHand {
	public HandRank getPokerRank();
	public List<Card> getCards();
}
