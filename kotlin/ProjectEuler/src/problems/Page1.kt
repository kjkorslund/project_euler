@file:Suppress("unused")
package problems

import util.FibonacciGenerator
import util.Primes
import util.numericextensions.findPrimeFactors
import util.numericextensions.isMultipleOf
import util.numericextensions.isPalindromic
import util.numericextensions.isPrime
import kotlin.math.absoluteValue
import kotlin.math.max

/**
 * [Problem 1](https://projecteuler.net/problem=1): Find the sum of all the multiples of 3 or 5 below 1000.
 */
object P1 : Problem<Int> {
    override fun calculate() : Int {
        var sum = 0
        for (i in 1 until 1000) {
            if (i.isMultipleOf(3) || i.isMultipleOf(5)) {
                sum += i
            }
        }
        return sum
    }
}

/**
 * [Problem 2](https://projecteuler.net/problem=2):
 * By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the
 * even-valued terms.
 */
object P2: Problem<Int> {
    override fun calculate(): Int {
        var sum = 0
        for(i in FibonacciGenerator()) {
            if (i.isEven()) sum += i
            if (i > 4_000_000) break
        }
        return sum
    }
}

/**
 * [Problem 3](https://projecteuler.net/problem=3):
 * What is the largest prime factor of the number 600851475143 ?
 */
object P3: Problem<Long> {
    override fun calculate(): Long {
        val l = 600851475143L
        return l.findPrimeFactors().last()
    }

}

/**
 * [Problem 4](https://projecteuler.net/problem=4)
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
object P4: Problem<Long?> {
    override fun calculate(): Long? {
        var result: Long? = null
        for(l1 in 999L downTo 100L) {
            for (l2 in l1 downTo 100L) {
                val product = l1*l2
                if (product.isPalindromic()) {
                    result = max(product, result ?: 0)
                }
            }
        }
        return result
    }
}

/**
 * [Problem 5](https://projecteuler.net/problem=5)
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
object P5: Problem<Long?> {
    override fun calculate(): Long? {
        return calculate(20L)
    }

    private fun calculate(@Suppress("SameParameterValue") largestDivisor: Long): Long? {
        // Evaluate the range from largest to smallest, because the larger divisors may have the smaller divisors as
        // divisors themselves.  Starting with the larger divisors allows for short-circuiting.
        val divisors = largestDivisor downTo 2L

        // Note: a simple strategy for the increment that will obviously work is to increment by the largest divisor.
        // This performs okay for this problem, but it is SIGNIFICANTLY faster to increment instead by the product of
        // all prime divisors.  The reason this works is less obvious, but it does work (I've tested it)
        val primeDivisors = divisors.filter { it.isPrime() }
        val increment = primeDivisors.reduce { acc, l -> acc * l }
        println("Prime divisors: $primeDivisors")
        println("Increment: $increment")

        return generateSequence(increment) { it + increment }
                .firstOrNull { candidate -> divisors.all { candidate.isMultipleOf(it) } }
    }
}

/**
 * [Problem 6](https://projecteuler.net/problem=6)
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
object P6: Problem<Long> {
    override fun calculate(): Long {
        val n = 100L
        return (n.squareOfSums() - n.sumOfSquares()).absoluteValue
    }

    private fun Long.sumOfSquares(): Long {
        return (1..this).asSequence()
                .map { it*it }
                .sum()
    }

    private fun Long.squareOfSums(): Long {
        val sum = (1..this).sum()
        return sum*sum
    }
}

/**
 * [Problem 7](https://projecteuler.net/problem=7)
 * What is the 10,001st prime number?
 */
object P7: Problem<Long> {
    override fun calculate(): Long {
        val n = 10_001

//        for(i in 1..10) {
//            var answer: Long? = null
//            val time = measureTimeMillis {
//                answer = Primes.sequence().elementAt(n - 1)
//            }
//            println("Answer #$i: $answer ($time ms)")
//        }

        return Primes.sequence().elementAt(n - 1)
    }
}

/**
 * [Problem 8](https://projecteuler.net/problem=8)
 * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this
 * product?
 */
object P8: Problem<Long> {
    // Note:  maybe someday this can be a 'const val'; see YouTrack KT-14652
    private const val digitsStr = (
            "73167176531330624919225119674426574742355349194934"
          + "96983520312774506326239578318016984801869478851843"
          + "85861560789112949495459501737958331952853208805511"
          + "12540698747158523863050715693290963295227443043557"
          + "66896648950445244523161731856403098711121722383113"
          + "62229893423380308135336276614282806444486645238749"
          + "30358907296290491560440772390713810515859307960866"
          + "70172427121883998797908792274921901699720888093776"
          + "65727333001053367881220235421809751254540594752243"
          + "52584907711670556013604839586446706324415722155397"
          + "53697817977846174064955149290862569321978468622482"
          + "83972241375657056057490261407972968652414535100474"
          + "82166370484403199890008895243450658541227588666881"
          + "16427171479924442928230863465674813919123162824586"
          + "17866458359124566529476545682848912883142607690042"
          + "24219022671055626321111109370544217506941658960408"
          + "07198403850962455444362981230987879927244284909188"
          + "84580156166097919133875499200524063689912560717606"
          + "05886116467109405077541002256983155200055935729725"
          + "71636269561882670428252483600823257530420752963450"
            )

    private val digits = digitsStr.map(Character::getNumericValue).map(Int::toLong).toList()

    override fun calculate(): Long {
//        println("Digits:  $digits")

        val nAdjacent = 13
        var maxProduct: Long = 0L
        (nAdjacent..digits.size).forEach {
            val sublist = digits.subList(it-nAdjacent, it)
            val currentProduct = sublist.reduce(Long::times)
//            if (currentProduct > maxProduct) {
//                println("New max found:  $currentProduct (from $sublist)")
//            }
//            println("${it-nAdjacent}..$it: $currentProduct  $sublist")
            maxProduct = max(maxProduct, currentProduct)
        }

        return maxProduct
    }
}

/**
 * [Problem 9](https://projecteuler.net/problem=9)
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.  Find the product abc.
 */
object P9: Problem<Long?> {
    override fun calculate(): Long? {
        for (a in 1L..1000L) {
            for (b in 1L..(1000L-a)) {
                val c = 1000L - (a+b)
                if (a*a + b*b == c*c) {
                    println("Solution found: $a^2 + $b^2 = $c^2")
                    return a*b*c
                }
            }
        }
        return null
    }

}

/**
 * [Problem 10](https://projecteuler.net/problem=10)
 * Find the sum of all the primes below two million.
 */
object P10: Problem<Long> {
    override fun calculate(): Long {
        return Primes.sequence().takeWhile { it < 2_000_000 }.sum()
    }
}

/**
 * [Problem 11](https://projecteuler.net/problem=11)
 * What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in
 * the 20×20 grid?  (Note: see link for grid)
 */
object P11: Problem<Long> {
    val gridStr = """
        08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08
        49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
        81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
        52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
        22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
        24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
        32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70
        67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21
        24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72
        21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95
        78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92
        16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
        86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
        19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
        04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
        88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
        04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36
        20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
        20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
        01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48
    """.trimIndent()

    val gridList: List<Long> = gridStr.split(' ', '\r', '\n')
            .map(String::toLong)

    override fun calculate(): Long {
//        println(gridList)

        var maxProduct: Long = 0L
        for(row in 0..19) {
            for (col in 0..19) {
                if (col+3 < 20) {
                    // Right
                    val product = valueAt(row, col) * valueAt(row, col+1) *
                            valueAt(row, col+2) * valueAt(row, col+3)
                    maxProduct = max(maxProduct, product)
                }
                if (row+3 < 20) {
                    // Down
                    val product = valueAt(row, col) * valueAt(row+1, col) *
                            valueAt(row+2, col) * valueAt(row+3, col)
                    maxProduct = max(maxProduct, product)
                }
                if (col+3 < 20 && row+3 < 20) {
                    // Down-right
                    val product = valueAt(row, col) * valueAt(row+1, col+1) *
                            valueAt(row+2, col+2) * valueAt(row+3, col+3)
                    maxProduct = max(maxProduct, product)
                }
                if (col-3 >= 0 && row+3 < 20) {
                    // Down-left
                    val product = valueAt(row, col) * valueAt(row+1, col-1) *
                            valueAt(row+2, col-2) * valueAt(row+3, col-3)
                    maxProduct = max(maxProduct, product)
                }
            }
        }
        return maxProduct
    }

    private fun valueAt(row: Int, col: Int): Long {
        return gridList[row*20 + col]
    }
}