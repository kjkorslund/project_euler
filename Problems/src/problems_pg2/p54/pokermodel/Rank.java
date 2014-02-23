package problems_pg2.p54.pokermodel;

import java.util.Comparator;


public enum Rank {
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
	
	private final int rankOrdinal;
	
	private Rank(int rankOrdinal) {
		this.rankOrdinal = rankOrdinal;
	}
	
	public int getRankOrdinal() {
		return rankOrdinal;
	}
	
	public static Comparator<Rank> COMPARE_ACES_LOW = new RankComparator();
	public static Comparator<Rank> COMPARE_ACES_HIGH = new RankComparator() {
		protected int getRankOrdinal(Rank r) {
			if (r == ACE) {
				return KING.getRankOrdinal()+1;
			}
			return super.getRankOrdinal(r);
		};
	};
	
	private static class RankComparator implements Comparator<Rank> {
		@Override
		public int compare(Rank r1, Rank r2) {
			return Integer.compare(getRankOrdinal(r1),getRankOrdinal(r2));
		}
		
		protected int getRankOrdinal(Rank r) {
			return r.getRankOrdinal();
		}
	}
}
