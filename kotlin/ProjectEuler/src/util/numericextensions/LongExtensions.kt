package util.numericextensions

import util.Primes
import util.stringextensions.isPalindrome

/**
 * Returns `true` if this value is a multiple of another value
 */
fun Long.isMultipleOf(n: Long) = (this%n == 0L)

/**
 * Returns `true` if this value is even
 */
fun Long.isEven() = (this and 1L == 0L)

/**
 * Returns `true` if this value is odd
 */
fun Long.isOdd() = (this and 1L != 0L)

/**
 * Returns `true` if this value is a prime number.  Uses [Primes] for the lookup.
 */
fun Long.isPrime() = Primes.isPrime(this)

/**
 * Find the smallest prime factor of this value.  Uses [Primes] to iterate through candidates.
 * @return smallest prime factor, or `null` if no such factor exists (e.g. for values <= 1)
 * @see findPrimeFactors
 */
fun Long.findSmallestPrimeFactor(): Long? {
    return when {
        this <= 1 -> null
        else -> Primes.sequence().takeWhile { it*it <= this }
                .firstOrNull { this.isMultipleOf(it) } ?: this
    }
}

/**
 * Find all prime factors of this value.  Uses [Primes] to iterate through candidates.
 * @return list of prime factors.  The list will be empty for values that do not have prime factors
 * @see findSmallestPrimeFactor
 */
fun Long.findPrimeFactors(): List<Long> {
    val results = mutableListOf<Long>()
    var next = this
    while(next > 1) {
        // Safe to assert non-null b/c findSmallestPrimeFactor only returns null on inputs <= 1
        val nextFactor = next.findSmallestPrimeFactor()!!
        results.add(nextFactor)
        next /= nextFactor
    }
    return results;
}

/**
 * Returns `true` if this value is palindromic (e.g. 3578753)
 */
fun Long.isPalindromic() = toString().isPalindrome()

/**
 * Find all divisors of this value, including the value itself
 * @return [List] of divisors, in ascending order
 * @see findProperDivisors
 */
fun Long.findDivisors(): List<Long> {
    // Strategy:  start with a set of {1}.  For each prime factor PF, set = union(set, set*PF)
    return when {
        this < 1L -> listOf()
        this == 1L -> listOf(1L)
        else -> {
            val resultSet = mutableSetOf<Long>(1)
            this.findPrimeFactors().forEach { primeFactor ->
                resultSet.addAll(resultSet.map { it*primeFactor })
            }
            return resultSet.sorted()
        }
    }
}

/**
 * Find all divisors of this value, excluding the value itself
 * @return [List] of proper divisors, in ascending order
 * @see findDivisors
 */
fun Long.findProperDivisors(): List<Long> = this.findDivisors().dropLast(1)
