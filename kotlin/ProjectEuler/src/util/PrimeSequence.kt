package util

import util.numericextensions.isMultipleOf

fun primeSequence(): Sequence<Long> = generateSequence(object : () -> Long {
    private val previousPrimes: MutableList<Long> = ArrayList()

    override fun invoke(): Long {
        val lastDiscoveredPrime = previousPrimes.lastOrNull() ?: 1L
        val nextPrime = generateSequence(lastDiscoveredPrime.inc(), Long::inc)
                .first { it.isPrime(previousPrimes) }
        previousPrimes.add(nextPrime)
        return nextPrime
    }

    private fun Long.isPrime(allSmallerPrimes: List<Long>): Boolean {
        return allSmallerPrimes
                .takeWhile { it*it <= this }
                .none { this.isMultipleOf(it) }
    }
})