package problems.pg01

import problems.Problem
import util.extensions.isPermutationOf
import util.extensions.isPrime

/**
 * We shall say that an n-digit number is pandigital if it makes use of all
 * the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital
 * and is also prime.
 *
 * What is the largest n-digit pandigital prime that exists?
 */
object P41 : Problem<Long> {
    override fun calculate(): Long {
        // New approach:  search for pandigital numbers within the known range for each digit count (e.g. 300..321 for
        //                3-digit pandigitals).  Check each found pandigital number to see if it's prime.
        //                Note: potential future improvement would be to use some sort of 'permutations iterator' rather
        //                      than just searching the entire range.  Most numbers in the range are not pandigital.
        // Note: it's important to convert to a sequence so the operations will be evaluated lazily
        return (1..9).asSequence()
            .map { pandigitalRange(it) }.flatten()
            .filter { isAnyPandigital(it) }
            .filter { it.isPrime() }
//            .onEach { println("Found: $it") }
            .last()

        // Old approach (too slow):  iterate through primes until the value gets larger than the largest possible
        //                           pandigital number.  Check if each prime is pandigital.  Keep the last result found.
//        return Primes.sequence().takeWhile { it < 1_000_000_000 }
//            .filter(::isAnyPandigital)
//            .onEach { println("Found: $it") }
//            .last();
    }

    private fun isAnyPandigital(num: Long): Boolean {
        val digits = num.toString()
        if (digits.length > 9) return false
        val requiredDigits = "123456789".slice(digits.indices)
        return digits.isPermutationOf(requiredDigits)
    }

    private fun pandigitalRange(maxDigit: Int): LongRange {
        var start = maxDigit.toLong()
        var end = maxDigit.toLong()
        for (i in 1 until maxDigit) {
            start *= 10
            end = end*10 + (maxDigit-i)
        }
        return start..end
    }
}