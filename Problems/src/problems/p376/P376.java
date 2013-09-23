package problems.p376;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class P376 {
	/*
	 * Consider the following set of dice with nonstandard pips:
	 * 
	 *    Die A: 1 4 4 4 4 4
	 *    Die B: 2 2 2 5 5 5
	 *    Die C: 3 3 3 3 3 6
	 * 
	 * A game is played by two players picking a die in turn and rolling it. The
	 * player who rolls the highest value wins.
	 * 
	 * If the first player picks die A and the second player picks die B we get
	 * P(second player wins) = 7/12 > 1/2
	 * 
	 * If the first player picks die B and the second player picks die C we get
	 * P(second player wins) = 7/12 > 1/2
	 * 
	 * If the first player picks die C and the second player picks die A we get
	 * P(second player wins) = 25/36 > 1/2
	 * 
	 * So whatever die the first player picks, the second player can pick
	 * another die and have a larger than 50% chance of winning. A set of dice
	 * having this property is called a nontransitive set of dice.
	 * 
	 * We wish to investigate how many sets of nontransitive dice exist. We will
	 * assume the following conditions:
	 * 
	 * There are three six-sided dice with each side having between 1 and N
	 * pips, inclusive. Dice with the same set of pips are equal, regardless of
	 * which side on the die the pips are located. The same pip value may appear
	 * on multiple dice; if both players roll the same value neither player
	 * wins. The sets of dice {A,B,C}, {B,C,A} and {C,A,B} are the same set.
	 * 
	 * For N = 7 we find there are 9780 such sets. How many are there for N = 30
	 * ?
	 */
	
	public static void main(String[] args) {
		
	}
	
//	static class DieSet {
//		private final Die[] dice;
//
//		public DieSet(Die... dice) {
//			if (dice.length != 3) throw new IllegalArgumentException();
//			this.dice = dice;
//		}
//		
//		public boolean computeIsNontransitive() {
//			
//		}
//		
//		public double computeWinProbability(Die mine, Die theirs) {
//			
//		}
//	}
	
	static class Die {
		final HashMap<Integer,Integer> pipCounts = new HashMap<Integer,Integer>();

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((pipCounts == null) ? 0 : pipCounts.hashCode());
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
			Die other = (Die) obj;
			if (pipCounts == null) {
				if (other.pipCounts != null)
					return false;
			} else if (!pipCounts.equals(other.pipCounts))
				return false;
			return true;
		}
	}
	
	static class DieIterator implements Iterator<Die> {
		final Die START = new Die();
		final Die END = new Die();
		
		Die current = START;
				
		public DieIterator(int n) {
			START.pipCounts.put(1, 6);
			END.pipCounts.put(n, 6);
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !END.equals(current);
		}

		@Override
		public Die next() {
			if (hasNext()) {
				
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

}
