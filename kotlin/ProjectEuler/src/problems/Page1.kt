package problems

import util.FibonacciGenerator
import util.numericextensions.*
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
        val l = 600851475143L;
        return l.findPrimeFactors().last();
    }

}

/**
 * [Problem 4](https://projecteuler.net/problem=4)
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
object P4: Problem<Long?> {
    override fun calculate(): Long? {
        var result: Long? = null
        for(l1 in 999L downTo 1L) {
            for (l2 in l1 downTo 1L) {
                val product = l1*l2;
                if (product.isPalindromic()) {
                    result = max(product, result ?: 0)
                }
            }
        }
        return result
    }
}