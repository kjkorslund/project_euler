package problems_pg2.p54.pokermodel;

public enum Suit {
	CLUBS('C'),
	DIAMONDS('D'),
	HEARTS('H'),
	SPADES('S');
	
	private final char ch;

	private Suit(char ch) {
		this.ch = ch;
	}
	
	@Override
	public String toString() {
		return Character.toString(ch);
	};
	
	public static Suit forChar(char c) {
		for(Suit suit : values()) {
			if (suit.ch == c) {
				return suit;
			}
		}
		return null;
	}
}
