@file:Suppress("unused")
package problems

import util.FibonacciGenerator
import util.numericextensions.*
import util.primeSequence
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
        return primeSequence().elementAt(n - 1)
    }
}