package util

import util.numericextensions.isMultipleOf

object Primes {
    // Invariant: knownPrimes always contains an ordered sequence of every prime number from 2..n, where n is the last
    //            (and largest) prime number to be added to the list
    private val knownPrimes: MutableList<Long> = mutableListOf(2L, 3L, 5L, 7L, 11L)

    private fun Long.isPrime() = knownPrimes
            .takeWhile { it*it <= this }
            .none { this.isMultipleOf(it) }

    fun sequence(): Sequence<Long> = sequence {
        var lastPrimeIndex = 0
        while(true) {
            while (lastPrimeIndex >= knownPrimes.size) {
                // Note: 2 is the only even prime, and since it is pre-populated in the known primes list, all even
                //       numbers can safely be skipped when searching for the next prime
                val nextPrime = generateSequence(knownPrimes.last()+2, { it+2 })
                        .first { it.isPrime() }
                knownPrimes.add(nextPrime)
            }
            yield(knownPrimes[lastPrimeIndex++])
        }
    }
}