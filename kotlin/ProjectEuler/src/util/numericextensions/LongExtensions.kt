package util.numericextensions

import util.Primes
import util.stringextensions.isPalindrome

fun Long.isMultipleOf(n: Long) = (this%n == 0L)
fun Long.isEven() = (this and 1L == 0L)
fun Long.isOdd() = (this and 1L != 0L)

fun Long.isPrime() = Primes.isPrime(this)

fun Long.findSmallestPrimeFactor(): Long? {
    return when {
        this <= 1 -> null
        else -> Primes.sequence().takeWhile { it*it <= this }
                .firstOrNull { this.isMultipleOf(it) } ?: this
    }
}

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

fun Long.isPalindromic() = toString().isPalindrome()

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