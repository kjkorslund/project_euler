package problems_pg2.p54.pokermodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Evaluates a poker hand to determine its highest {@link HandRank}.
 * @author Kevin
 *
 */
public class PokerHandEvaluator {
	public HandRank evaluateHand(Collection<Card> cards) {
		Evaluator e = new Evaluator(cards);
		return e.evaluate();
	}
	
	private class Evaluator {
		private final HashSet<Card> cards;
		private final TreeSet<CardRank> allRanks = new TreeSet<>(CardRank.comparatorAcesHigh());
		private final HashMap<Suit, TreeSet<CardRank>> ranksBySuit = new HashMap<>();
		{	// Instance initializer block for ranksBySuit
			for (Suit suit : Suit.values()) {
				ranksBySuit.put(suit, new TreeSet<CardRank>(CardRank.comparatorAcesHigh()));
			}
		}
		private final HashMap<CardRank, Integer> rankCounts = new HashMap<>();
		{	// Instance initializer block for rankCounts
			for (CardRank rank : CardRank.values()) {
				rankCounts.put(rank, 0);
			}
		}
		
		public Evaluator(Collection<Card> cards) {
			this.cards = new HashSet<>(cards);
			
			for (Card card : cards) {
				allRanks.add(card.rank);
				ranksBySuit.get(card.suit).add(card.rank);
				rankCounts.put(card.rank, rankCounts.get(card.rank)+1);
			}
		}
		
		public Set<Card> getCards() {
			return Collections.unmodifiableSet(cards);
		}
		
		public HandRank evaluate() {
			for(PokerHand hand : PokerHand.descendingValues()) {
				Set<CardRank> usedCardRanks = bestCardRanks(hand);
				if (usedCardRanks != null) {
					Set<CardRank> unusedCardRanks = new HashSet<>(allRanks);
					unusedCardRanks.removeAll(usedCardRanks);
					return new HandRank(hand, usedCardRanks, unusedCardRanks);
				}
			}
			
			return null;
		}
		
		private Set<CardRank> bestCardRanks(PokerHand hand) {
			switch(hand) { 
			case ROYAL_FLUSH:
				return bestRoyalFlush();
			case STRAIGHT_FLUSH:
				return bestStraightFlush();
			case FOUR_OF_A_KIND:
				return bestFourOfAKind();
			case FULL_HOUSE:
				return bestFullHouse();
			case FLUSH:
				return bestFlush();
			case STRAIGHT:
				return bestStraight();
			case THREE_OF_A_KIND:
				return bestThreeOfAKind();
			case TWO_PAIRS:
				return bestTwoPairs();
			case ONE_PAIR:
				return bestPair();
			case HIGH_CARD:
				return bestHighCard();
			default:
				throw new UnsupportedOperationException("Unsupported hand rank: " + hand);
			}
		}
		
		private Set<CardRank> bestRoyalFlush() {
			Set<CardRank> result = bestStraightFlush();
			if (result != null && result.contains(CardRank.ACE)) {
				return result;
			}
			return null;
		}

		private Set<CardRank> bestStraightFlush() {
			for (Suit suit : Suit.values()) {
				if (!hasFlush(suit)) continue;
				
				TreeSet<CardRank> suitRanks = ranksBySuit.get(suit);
				Set<CardRank> result = bestStraight(suitRanks);
				if (result == null) continue;
				
				return result;
			}
			return null;
		}
		
		private Set<CardRank> bestFourOfAKind() {
			CardRank rank = bestNOfAKind(4);
			if (rank == null) return null;
			return Collections.singleton(rank);
		}

		private Set<CardRank> bestFullHouse() {
			CardRank bestThreeOfAKind = bestNOfAKind(3);
			if (bestThreeOfAKind == null) return null;
			
			CardRank bestPair = bestNOfAKind(2, Collections.singleton(bestThreeOfAKind));
			if (bestPair == null) return null;
			
			HashSet<CardRank> results = new HashSet<>();
			results.add(bestThreeOfAKind);
			results.add(bestPair);
			return results;
		}

		private Set<CardRank> bestFlush() {
			for (Suit suit : Suit.values()) {
				if (!hasFlush(suit)) continue;
				return bestFlush(suit);
			}
			return null;
		}

		private Set<CardRank> bestStraight() {
			return bestStraight(allRanks);
		}

		private Set<CardRank> bestThreeOfAKind() {
			CardRank rank = bestNOfAKind(3);
			if (rank == null) return null;
			return Collections.singleton(rank);
		}

		private Set<CardRank> bestTwoPairs() {
			CardRank bestPair = bestNOfAKind(2);
			if (bestPair == null) return null;
			
			CardRank secondBestPair = bestNOfAKind(2, Collections.singleton(bestPair));
			if (secondBestPair == null) return null;
			
			HashSet<CardRank> results = new HashSet<>();
			results.add(bestPair);
			results.add(secondBestPair);
			return results;
		}

		private Set<CardRank> bestPair() {
			CardRank rank = bestNOfAKind(2);
			if (rank == null) return null;
			return Collections.singleton(rank);
		}

		private Set<CardRank> bestHighCard() {
			CardRank rank = bestNOfAKind(1);
			if (rank == null) return null;
			return Collections.singleton(rank);
		}
		
		// *****
		
		private CardRank bestNOfAKind(int n) {
			return bestNOfAKind(n, Collections.<CardRank>emptySet());
		}
		
		private CardRank bestNOfAKind(int n, Set<CardRank> ranksToSkip) {
			Iterator<CardRank> iter = allRanks.descendingIterator();
			while(iter.hasNext()) {
				CardRank rank = iter.next();
				if (ranksToSkip.contains(rank)) {
					continue;
				}
				
				if (rankCounts.get(rank) >= n) {
					return rank;
				}
			}
			return null;
		}
		
		private boolean hasFlush(Suit suit) {
			return ranksBySuit.get(suit).size() >= 5;
		}
		
		private Set<CardRank> bestFlush(Suit suit) {
			TreeSet<CardRank> suitRanks = ranksBySuit.get(suit);
			if (suitRanks.size() < 5) return null;
			
			Iterator<CardRank> iter = suitRanks.descendingIterator();
			HashSet<CardRank> results = new HashSet<>();
			
			// [kjk] Sometimes, loops are overrated :)
			results.add(iter.next());
			results.add(iter.next());
			results.add(iter.next());
			results.add(iter.next());
			results.add(iter.next());
			
			return results;
		}
		
		private Set<CardRank> bestStraight(TreeSet<CardRank> ranks) {
			Iterator<CardRank> iter = ranks.descendingIterator();
			if (!iter.hasNext()) return null;
			
			TreeSet<CardRank> results = new TreeSet<>(CardRank.comparatorAcesHigh());
			results.add(iter.next());
			while(iter.hasNext()) {
				CardRank rank = iter.next();
				if (isSuccessor(results.first(), rank)) {
					results.add(rank);
					if (results.size() == 5) {
						return results;
					}
				} else {
					results = new TreeSet<>(CardRank.comparatorAcesHigh());
					results.add(rank);
				}
			}
			return null;
		}
		
		private boolean isSuccessor(CardRank rank, CardRank previous) {
			return rank.getValueAcesHigh() - previous.getValueAcesHigh() == 1; 
		}
	}
}
