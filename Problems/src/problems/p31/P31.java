package problems.p31;

public class P31 {
	/*
	 * In England the currency is made up of pound, £, and pence, p, and there
	 * are eight coins in general circulation:
	 * 
	 *    1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
	 * 
	 * It is possible to make £2 in the following way:
	 * 
	 *    1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
	 * 
	 * How many different ways can £2 be made using any number of coins?
	 */
	public static void main(String[] args) {
		for(int i=1; i<10; i++) {
			System.out.println(i + ": " + countCombinations(i));
		}
		System.out.println("200" + ": " + countCombinations(200));
	}
	
	static int[] units = {200, 100, 50, 20, 10, 5, 2, 1};

	static int countCombinations(int target) {
		return _countCombinations(target,0,0);
	}
	
	static int _countCombinations(int target, int current, int unitIndex) {
		int combos = 0;
		if (unitIndex < units.length) {
			while(current < target) {
				//System.out.println("(" + units[unitIndex] + ") " + current);
				combos += _countCombinations(target,current,unitIndex+1);
				current += units[unitIndex];
			}
			if (current == target) combos++;
		}
		return combos;
	}
}
