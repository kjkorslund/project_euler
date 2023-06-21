@file:Suppress("unused")
package problems

import util.LongFraction
import util.extensions.contains
import util.extensions.digits
import util.extensions.fromDigits
import util.extensions.size

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