package problems_pg2.p54.pokermodel;

import java.util.Collection;
import java.util.Comparator;

/**
 * Evaluates a poker hand to determine its highest {@link PokerRank}.
 * @author Kevin
 *
 */
public abstract class PokerHandEvaluator {
	public PokerRank evaluateHand(Collection<Card> hand) {
		// [kjk] Overall strategy:
		// 1) Start from highest hand rank and work toward lowest, looking for a
		// match.
		// 2) Depending on the size of the hand, there may be more than one
		// combination of cards that match the hand rank (not applicable to
		// standard poker, but perhaps to variants like Texas Hold'em). Evaluate
		// which combination has the highest value.
		// 3) Create and return a PokerRank that splits the hand into matching
		// and non-matching cards
	}
	
	static abstract class HandRankEvaluator {
		static HandRankEvaluator ROYAL_FLUSH = new HandRankEvaluator() {
			@Override
			PokerRank evaluate(Collection<Card> hand) {
				// TODO I need to come up with a good strategy for efficiently
				// evaluating the various hand ranks
				return null;
			}
		};
		
		abstract PokerRank evaluate(Collection<Card> hand);
	}
}
