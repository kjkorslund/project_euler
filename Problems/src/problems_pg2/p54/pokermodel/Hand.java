package problems_pg2.p54.pokermodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand implements Comparable<Hand> {

	private final Card[] cards;
	private final List<Card> cardsReadOnly;
	
	public Hand(Card... cards) {
		this.cards = Arrays.copyOf(cards, cards.length);
		this.cardsReadOnly = Collections.unmodifiableList(Arrays.asList(this.cards));
	}

	public List<Card> getCards() {
		return cardsReadOnly;
	}
	
	@Override
	public int compareTo(Hand o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
