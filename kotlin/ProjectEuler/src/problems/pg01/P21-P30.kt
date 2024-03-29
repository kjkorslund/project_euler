@file:Suppress("unused")
package problems.pg01

import problems.Problem
import util.Fibonacci
import util.Primes
import util.extensions.cartesianProduct
import util.extensions.*
import util.extensions.lexicographicPermutations
import java.math.BigInteger

/**
 * [Problem 21](https://projecteuler.net/problem=21)
 * Evaluate the sum of all the amicable numbers under 10000.
 */
object P21: Problem<Long> {
    override fun calculate(): Long {
        return (1L..9999L)
            .filter { it.getAmicablePartner() != null }
//            .also { println(it) }
            .sum()
    }

    // d(n), as defined in the problem statement
    private fun d(n: Long) = n.findDivisors().dropLast(1)
//        .also {println("d($n) = ${it.joinToString("+")}")}
        .sum()

    private fun Long.getAmicablePartner(): Long? {
        val pair = d(this)
        return pair.takeIf {
            it != this && d(it) == this
        }
    }
}

/**
 * [Problem 22](https://projecteuler.net/problem=22)
 * What is the total of all the name scores in the file?
 */
object P22: Problem<Long> {
    private val names = this.readResource("p22_names.txt") { reader ->
        Regex("\"(\\w+)\"").findAll(reader.readText())
            .mapNotNull { it.groups[1]?.value }
            .toList()
//            .also(::println)
    }

    override fun calculate(): Long {
//        "COLIN".let { println("$it = ${nameScore(it)}") }
        return names.sorted().asSequence()
//            .also(::println)
//            .also {println(it.indexOfFirst { it == "COLIN" }.inc())}
            .mapIndexed {index, it -> index.inc() * nameScore(it) }
            .sum()
    }

    private fun nameScore(name: String): Long =
        name.asSequence().map { (it - 'A' + 1).toLong() }
//            .onEach { println(it) }
            .sum()

}

/**
 * [Problem 23](https://projecteuler.net/problem=23)
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
object P23: Problem<Long> {
    override fun calculate(): Long {
        val limit = 28123L
        val abundantNumbers = (1 until limit).asSequence()
            .filter { it.isAbundant() }
            .toList()
//            .also(::println)

        val abundantSums = sequence {
            for(i in abundantNumbers.indices) {
                for (j in i until abundantNumbers.size) {
                    val sum = abundantNumbers[i] + abundantNumbers[j]
//                    println("${abundantNumbers[i]} + ${abundantNumbers[j]} = $sum")
                    if (sum > limit) break else yield(sum)
                }
            }
        }.toSet()
//            .also {println(it.size)}


        return (1..limit)
            .filter { !abundantSums.contains(it) }
//            .also {println(it.size)}
            .sum()
    }

    private fun Long.isPerfect() = (this.findProperDivisors().sum() == this)
    private fun Long.isDeficient() = (this.findProperDivisors().sum() < this)
    private fun Long.isAbundant() = (this.findProperDivisors().sum() > this)
}

/**
 * [Problem 24](https://projecteuler.net/problem=24)
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
object P24: Problem<Long> {
    override fun calculate(): Long {
//        "210".lexicographicPermutations().forEach { println(it) }

        val target = 1_000_000
        return "0123456789".lexicographicPermutations()
            .drop(target.dec())
            .first().toLong()
    }
}

/**
 * [Problem 25](https://projecteuler.net/problem=25)
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 */
object P25: Problem<Long> {
    override fun calculate(): Long {
//        fibonacciBig().take(10).forEach { println(it) }

        val targetDigits = 1000
        val target: BigInteger = BigInteger.TEN.pow(targetDigits.dec())
        return Fibonacci.bigIntegerSequence().indexOfFirst { it >= target }.inc().toLong()
    }
}

/**
 * [Problem 26](https://projecteuler.net/problem=26)
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
object P26: Problem<Int> {
    override fun calculate(): Int {
//        println("3227/555 = .${fractionalDigits(3227, 555).take(10).joinToString("")}")
//        println("  Numerators: ${fractionalDigitNumerators(3227, 555).take(10).joinToString(" ")}")
//        println("  Repeating portion: ${repeatingFractionalDigits(3227, 555)}")

        return (1 until 1000)
            .map { it to repeatingFractionalDigits(1, it).size }
            .maxByOrNull { it.second }!!.first
    }

    private fun fractionalDigits(numerator: Int, denominator: Int) = sequence {
        var n = numerator%denominator
        while(n > 0) {
            yield((n * 10) / denominator)
            n = (n * 10) % denominator
        }
    }

    private fun fractionalDigitNumerators(numerator: Int, denominator: Int) = sequence {
        var n = numerator%denominator
        while(n > 0) {
            yield(n)
            n = (n * 10) % denominator
        }
    }

    @Suppress("SameParameterValue")
    private fun repeatingFractionalDigits(numerator: Int, denominator: Int): List<Int> {
        val encounteredNumerators = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        for(n in fractionalDigitNumerators(numerator,denominator)) {
            if (encounteredNumerators.add(n)) {
                result.add(n)
            } else {
                return result.subList(result.lastIndexOf(n), result.size)
                    .map { (it * 10) / denominator }
            }
        }
        return emptyList()
    }
}

/**
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of
 * primes for consecutive values of n, starting with n = 0.
 */
object P27: Problem<Int> {
    override fun calculate(): Int {
        val aRange = (-999 .. 999)
        val bRange = (-999 .. 999)

        return aRange.cartesianProduct(bRange)
            .maxBy { quadraticSequence(it.first, it.second).count() }
            .let { it.first * it.second }
    }

    private fun quadraticSequence(a: Int, b: Int): Sequence<Long> = sequence {
        var n = 0
        var result = quadraticFun(n, a, b)
        while(Primes.isPrime(result)) {
            yield(result)
            result = quadraticFun(++n, a, b)
        }
    }

    private fun quadraticFun(n: Int, a: Int, b: Int): Long = n.toLong().let { it*it + a*it + b }
}

/*
 * Starting with the number 1 and moving to the right in a clockwise
 * direction a 5 by 5 spiral is formed as follows:
 *
 *     21 22 23 24 25
 *     20  7  8  9 10
 *     19  6  1  2 11
 *     18  5  4  3 12
 *     17 16 15 14 13
 *
 * It can be verified that the sum of the numbers on the diagonals is 101.
 *
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral
 * formed in the same way?
 */
object P28: Problem<Int> {
    override fun calculate(): Int {
        val sideLength = 1001
        return spiralDiagonals()
            .takeWhile { it <= sideLength*sideLength }
            .sum()
    }

    private fun spiralDiagonals(): Sequence<Int> = sequence {
        // The initial yield (1) is the center of the spiral.  After that, the four corners of each subsequent 'shell'
        // are yielded.  The corners of first shell have an increment of 2, and each subsequent shell has an increment 2
        // more than the previous layer (e.g. 4 for the second layer, 6 for the 3rd layer, etc.)

        var n = 1
        var increment = 2
        yield(n)

        while(true) {
            repeat(4) {
                n = (n+increment).also { yield(it) }
            }
            increment += 2
        }
    }
}

/*
 * Consider all integer combinations of ab for 2 <= a <= 5 and 2 <= b <= 5:
 *
 *     2^2=4, 2^3=8, 2^4=16, 2^5=32
 *     3^2=9, 3^3=27, 3^4=81, 3^5=243
 *     4^2=16, 4^3=64, 4^4=256, 4^5=1024
 *     5^2=25, 5^3=125, 5^4=625, 5^5=3125
 *
 * If they are then placed in numerical order, with any repeats removed, we
 * get the following sequence of 15 distinct terms:
 *
 *     4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
 *
 * How many distinct terms are in the sequence generated by a^b for 2 <= a <=
 * 100 and 2 <= b <= 100?
 */
object P29: Problem<Int> {
    override fun calculate(): Int {
        val aRange = 2..100
        val bRange = 2..100
        return aRange.cartesianProduct(bRange)
            .map { BigInteger.valueOf(it.first.toLong()).pow(it.second) }
            .toSet()
            .size
    }
}

/*
 * Surprisingly there are only three numbers that can be written as the sum
 * of fourth powers of their digits:
 *
 *    1634 = 14 + 64 + 34 + 44
 *    8208 = 84 + 24 + 04 + 84
 *    9474 = 94 + 44 + 74 + 44
 *
 * As 1 = 14 is not a sum it is not included.
 *
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 *
 * Find the sum of all the numbers that can be written as the sum of fifth
 * powers of their digits.
 */
object P30: Problem<Long> {
    private const val EXPONENT = 5
    private val precomputedPowers = (0..9).associateWith { it.pow(EXPONENT) }

    override fun calculate(): Long {
        val searchRange = 2.. findMaxPossibleNumber()
        return searchRange
            .filter { it == powerSum(it) }
            .sum()
    }

    /**
     * Finds the maximum possible number that could possibly be equal to its power sum.  The logic behind
     * this is tricky, but basically the idea is that for a given number of digits, there is a maximum possible power
     * sum, e.g. for exponent of 2 and 3 digits, the max power sum is 9^2*3 = 243.  If the max power sum is less than
     * the maximum possible value for that number of digits (e.g. 243 < 999), then we know that the power sum can no
     * longer 'keep up' with the digit count.
     */
    private fun findMaxPossibleNumber(): Long {
        val precomputedPower9 = precomputedPowers[9]!!

        fun isPowerSumPossible(digitCount: Int): Boolean {
            val maxVal = (10).pow(digitCount) - 1
            val maxPowerSum = precomputedPower9 * digitCount
            return maxVal <= maxPowerSum
        }

        return generateSequence(1, Int::inc)
            .dropWhile { isPowerSumPossible(it)}
            .first()
//            .also { println("$exponent: $it, ${(10).pow(it) - 1}, ${precomputedPower9 * it}") }
            .let { (10).pow(it) - 1 }
    }

    private fun powerSum(number: Long): Long = number.digits().sumOf { precomputedPowers[it]!! }

}