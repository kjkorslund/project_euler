package problems_pg2.p54.pokermodel;

import java.util.Comparator;


public enum CardRank {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13);
	
	private final int value;
	
	private CardRank(int value) {
		this.value = value;
	}
	
	public int getValueAcesLow() {
		return value;
	}
	
	public int getValueAcesHigh() {
		return (this == ACE) ? KING.value + 1 : value;
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
