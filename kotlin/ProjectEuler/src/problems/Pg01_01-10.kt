@file:Suppress("unused")
package problems

import util.Fibonacci
import util.Primes
import util.extensions.*
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
        return Fibonacci.intSequence()
            .filter { it.isEven() }
            .takeWhile { it <= 4_000_000 }
            .sum()
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
        var maxProduct = 0L
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
