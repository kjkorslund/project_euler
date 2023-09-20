@file:Suppress("unused")
package problems

import util.LongFraction
import util.Primes
import util.extensions.*

/**
 * In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general
 * circulation:
 *
 *     1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
 *
 * It is possible to make £2 in the following way:
 *
 *     1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 *
 * How many different ways can £2 be made using any number of coins?
 *
 */
object P31 : Problem<Int> {
    private val COIN_AMOUNTS: List<Int> = listOf(1, 2, 5, 10, 20, 50, 100, 200)

    override fun calculate(): Int {
        return countSumCombinations(0, 200, COIN_AMOUNTS.sortedDescending())
    }

    private fun countSumCombinations(startSum: Int, targetSum: Int, availableAmounts: List<Int>): Int {
        if (availableAmounts.isEmpty()) return 0

        val amount = availableAmounts.first()
        val remainingAmounts = availableAmounts.subList(1, availableAmounts.size)

        var currentSum = startSum
        var combinations = 0
        while (currentSum < targetSum) {
            combinations += countSumCombinations(currentSum, targetSum, remainingAmounts)
            currentSum += amount
        }
        if (currentSum == targetSum) combinations += 1
        return combinations
    }
}

/**
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example,
 * the 5-digit number, 15234, is 1 through 5 pandigital.
 *
 * The product 7254 is unusual, as the identity, 39 x 186 = 7254, containing multiplicand, multiplier, and product is
 * 1 through 9 pandigital.
 *
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9
 * pandigital.
 *
 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
object P32 : Problem<Int> {
    override fun calculate(): Int {
        // Note: Pandigital product identities must have exactly nine digits.  The largest possible product that can
        //       be produced by a 4-digit combination of multiplicands is 99 x 99 = 9801, which is a total of only eight
        //       digits.  Likewise, the smallest possible product of a 6-digit combination of multiplicands is
        //       1 x 11111 = 11111, which at 11 digits is too many. Therefore, only 5-digit combinations of
        //       multiplicands can possibly produce a pandigital product identity.  This can be used to limit the search
        //       range significantly.

        val uniqueProducts = mutableSetOf<Int>()
        fun checkProductsOf(iRange: IntRange, jRange: IntRange) {
            for (i in iRange) {
                for (j in jRange) {
                    val product = i*j
                    val numbers = listOf(i,j,product)
                    if (isPandigital(numbers)) {
                        println("Match: ${numbers[0]} x ${numbers[1]} = ${numbers[2]}")
                        uniqueProducts.add(product)
                    }
                }
            }
        }

        checkProductsOf(1..9, 1111..9999)
        checkProductsOf(11..99, 111..999)

        println("Unique products:  $uniqueProducts")
        return uniqueProducts.sum()
    }

    /**
     * Returns true if the given set of numbers is pandigital for the given IntRange
     */
    private fun isPandigital(numbers: List<Int>, range: IntRange = 1..9): Boolean {
        if (!(0..9).contains(range)) throw IndexOutOfBoundsException("Pandigital range must be within 0..9")

        val allDigits = numbers.fold(emptyList<Int>()) { list, it -> list + it.digits() }
//        println("${allDigits}")

        return allDigits.size == range.size() && allDigits.containsAll(range.toList())
    }
}

/**
 * The fraction 49/98 is a curious fraction, as an inexperienced
 * mathematician in attempting to simplify it may incorrectly believe that
 * 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 *
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 *
 * There are exactly four non-trivial examples of this type of fraction,
 * less than one in value, and containing two digits in the numerator and
 * denominator.
 *
 * If the product of these four fractions is given in its lowest common
 * terms, find the value of the denominator.
 */
object P33 : Problem<Int> {
    override fun calculate(): Int {
        var product = LongFraction(1,1)
        for (d in 12 until 100) {
            for (n in 11 until d) {
                val fraction = LongFraction(n.toLong(), d.toLong())
                if (isCuriousFraction(fraction)) {
                    println("$fraction (${fraction.reduce()})")
                    product *= fraction
                }
            }
        }
        return product.denominator.toInt()
    }

    private fun isCuriousFraction(fraction: LongFraction): Boolean {
        val nDigits = fraction.numerator.digits().toList().reversed()
        val dDigits = fraction.denominator.digits().toList().reversed()

        val nonTrivialIndexPairs = mutableListOf<Pair<Int, Int>>()
        for (nIndex in nDigits.indices) {
            for (dIndex in dDigits.indices) {
                if (nDigits[nIndex] == dDigits[dIndex] && nDigits[nIndex] != 0) {
                    nonTrivialIndexPairs.add(Pair(nIndex, dIndex))
                }
            }
        }

        for (indexPair in nonTrivialIndexPairs) {
            val modifiedFraction = LongFraction(
                Long.fromDigits(nDigits.filterIndexed { index, _ -> index != indexPair.first }),
                Long.fromDigits(dDigits.filterIndexed { index, _ -> index != indexPair.second })
            )
            if (fraction.equivalentTo(modifiedFraction)) return true
        }
        return false
    }
}

/**
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 *
 * Find the sum of all numbers which are equal to the sum of the factorial
 * of their digits.
 *
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */
object P34 : Problem<Long> {
    override fun calculate(): Long {
        // Note on upper bound: 9! = 362880, and the upper bound occurs where 9! * nDigits is less than the largest
        // possible number with nDigits.  By my calculations, this occurs for nDigits = 8 (2,903,040 < 10,000,000), i.e.
        // the maximum possible sum of factorials from an 8-digit number is only a 7-digit number, therefore no 8-digit
        // numbers can be a match.
        var sumOfSums = 0L
        for (l in 3L until 10_000_000L) {
            val sum = l.digits()
                .map { it.toLong().factorial()!! }
                .sum()
            if (l == sum) {
                println(l)
                sumOfSums += sum
            }
        }
        return sumOfSums
    }

}

/**
 * The number, 197, is called a circular prime because all rotations of the
 * digits: 197, 971, and 719, are themselves prime.
 *
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37,
 * 71, 73, 79, and 97.
 *
 * How many circular primes are there below one million?
 */
object P35 : Problem<Int> {
    override fun calculate(): Int {
        val matches = mutableSetOf<Int>()
        for (i in 1 until 1_000_000) {
            if (matches.contains(i)) continue
            if (!i.isPrime()) continue

            val rotations = digitRotations(i).toList()
            if (rotations.all { it.isPrime() }) {
//                println(rotations)
                matches.addAll(rotations)
            }
        }
        println(matches.toList().sorted())
        return matches.size
    }

    private fun digitRotations(i: Int): Sequence<Int> = sequence {
        yield(i)
        val digits = i.digits().toList().reversed()
        var rotatedDigits = digits.rotateLeft()
        while (rotatedDigits != digits) {
            yield (Int.fromDigits(rotatedDigits))
            rotatedDigits = rotatedDigits.rotateLeft()
        }
    }

    fun <T> List<T>.rotateLeft(): List<T> {
        return this.drop(1) + this.first()
    }
}

/*
 * The decimal number, 585 = 1001001001b (binary), is palindromic in both
 * bases.
 *
 * Find the sum of all numbers, less than one million, which are palindromic
 * in base 10 and base 2.
 *
 * (Please note that the palindromic number, in either base, may not include
 * leading zeros.)
 */
object P36 : Problem<Int> {
    override fun calculate(): Int {
       return (1 until 1_000_000)
            .filter(this::isPalindromicInBoth)
            .sum()
    }

    private fun isPalindromicInBoth(n: Int): Boolean {
        val decimalStr = n.toString()
        val binaryStr = n.toString(2)
        return decimalStr.isPalindrome() && binaryStr.isPalindrome()
    }
}

/*
 * The number 3797 has an interesting property. Being prime itself, it is
 * possible to continuously remove digits from left to right, and remain
 * prime at each stage: 3797, 797, 97, and 7. Similarly we can work from
 * right to left: 3797, 379, 37, and 3.
 *
 * Find the sum of the only eleven primes that are both truncatable from
 * left to right and right to left.
 *
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
object P37 : Problem<Long> {
    override fun calculate(): Long {
        // Bit of a cheat: the problem states there are 11 results so the search proceeds only until 11 are found
        // It should be possible to write an algorithm that doesn't need to know the number of results, but I'm not
        // exactly sure how to define the constraints.  Truncatable primes must start and end with a prime digit (1, 3,
        // 5, 7), but the digits in-between are allowed to be non-prime.  It's unclear how to know for sure that
//        return Primes.sequence().dropWhile { it < 10 }
//            .filter(this::isTruncatablePrime).take(11)
//            .onEach { println("$it") }
//            .sum()

        // This code is experimental/WIP, trying to find a way to identify candidates via expansion in order to avoid
        // the constraint problem
        val results: MutableSet<Long> = mutableSetOf()
        val candidates: MutableList<Long> = mutableListOf(2, 3, 5, 7)
        val candidateDigits: List<Int> = listOf(1,3,7,9) // Other digits are guaranteed non-primes
        while (candidates.isNotEmpty()) {
            val candidate = candidates.removeFirst()
//            println("Search: $candidate (Remaining: $candidates)")
            candidateDigits.map { expandRight(candidate, it) }
                .filter { it.isPrime() }
//                .filter { it.isPrime() && leftTruncates(it).all(Long::isPrime) }
                .forEach {
                    candidates.add(it)
                    if (isTruncatablePrime(it)) {
                        results.add(it)
                        println("Found: $it")
                    }
                }
        }

        return results.sum();
    }

    private fun expandRight(num: Long, digit: Int): Long {
        return Long.fromDigits(num.digits().toList().reversed() + digit)
    }

    private fun leftTruncates(num: Long): Sequence<Long> {
        fun truncateLeft(n: Long): Long? = if (n >= 10) {
            n.digits().toList().reversed().drop(1).let { Long.fromDigits(it) }
        } else null

        return generateSequence(truncateLeft(num), ::truncateLeft)
    }

    private fun isTruncatablePrime(num: Long): Boolean {
        fun truncateLeft(n: Long): Long = n.digits().toList().reversed().drop(1).let { Long.fromDigits(it) }
        fun truncateRight(n: Long): Long = n.digits().toList().drop(1).reversed().let { Long.fromDigits(it) }

        val leftTruncates = generateSequence(num) { if (it >= 10) truncateLeft(it) else null }
        val rightTruncates = generateSequence(num) { if (it >= 10) truncateRight(it) else null }

        return leftTruncates.all { it.isPrime() } && rightTruncates.all { it.isPrime() }
    }
}