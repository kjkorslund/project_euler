package problems_pg2.p54.pokermodel;

import java.util.Comparator;


public enum CardRank {
	ACE('A',1),
	TWO('2',2),
	THREE('3', 3),
	FOUR('4', 4),
	FIVE('5', 5),
	SIX('6', 6),
	SEVEN('7', 7),
	EIGHT('8', 8),
	NINE('9', 9),
	TEN('T', 10),
	JACK('J', 11),
	QUEEN('Q', 12),
	KING('K', 13);
	
	private final char ch;
	private final int value;
	
	private CardRank(char ch, int value) {
		this.ch = ch;
		this.value = value;
	}
	
	public int getValueAcesLow() {
		return value;
	}
	
	public int getValueAcesHigh() {
		return (this == ACE) ? KING.value + 1 : value;
	}
	
	@Override
	public String toString() {
		return Character.toString(ch);
	}
	
	public static CardRank forChar(char c) {
		for(CardRank rank : values()) {
			if (rank.ch == c) {
				return rank;
			}
		}
		return null;
	}
	
	public static Comparator<CardRank> comparatorAcesLow() {
		return new AcesLowComparator();
	}
	
	public static Comparator<CardRank> comparatorAcesHigh() {
		return new AcesHighComparator();
	}
	
	private static class AcesLowComparator implements Comparator<CardRank> {
		@Override
		public int compare(CardRank r1, CardRank r2) {
			return Integer.compare(r1.getValueAcesLow(),r2.getValueAcesLow());
		}
	}
	
	private static class AcesHighComparator implements Comparator<CardRank> {
		@Override
		public int compare(CardRank r1, CardRank r2) {
			return Integer.compare(r1.getValueAcesLow(),r2.getValueAcesLow());
		}
	}
}
