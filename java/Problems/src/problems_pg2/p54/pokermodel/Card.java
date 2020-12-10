package problems_pg2.p54.pokermodel;

public class Card {

	public final Suit suit;
	public final CardRank rank;

	public Card(Suit suit, CardRank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return rank.toString() + suit.toString();
	}
}
