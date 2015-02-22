package problems_pg2.p54.pokermodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public enum PokerHand {
	HIGH_CARD,
	ONE_PAIR,
	TWO_PAIRS,
	THREE_OF_A_KIND,
	STRAIGHT,
	FLUSH,
	FULL_HOUSE,
	FOUR_OF_A_KIND,
	STRAIGHT_FLUSH,
	ROYAL_FLUSH;
	
	public static PokerHand[] descendingValues() {
		PokerHand[] values = values();
		Arrays.sort(values, Collections.reverseOrder());
		return values;
	}
}
