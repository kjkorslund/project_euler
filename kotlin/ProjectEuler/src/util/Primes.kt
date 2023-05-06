package util

import util.extensions.isMultipleOf

object Primes {
    // TODO for future improvement:
    // 1.  Implement private function "extendUntil(predicate: (Long) -> Boolean)" and maybe make it synchronous?
    // 2.  Modify sequence function to use extendUntil
    // 3.  Implement new function "isPrime(l: Long)", have it use "extendUntil"

    // Invariant: knownPrimes always contains an ordered sequence of every prime number from 2..n, where n is the last
    //            (and largest) prime number to be added to the list
    private val knownPrimes: MutableList<Long> = mutableListOf(2L, 3L, 5L, 7L, 11L)

    fun isPrime(candidate: Long): Boolean {
        extendUntil { it >= candidate }
        return knownPrimes.binarySearch(candidate) >= 0
    }

    fun sequence(): Sequence<Long> = sequence {
        var lastPrimeIndex = 0
        while(true) {
            extendUntil { knownPrimes.size > lastPrimeIndex }
            yield(knownPrimes[lastPrimeIndex++])
        }
    }

    private fun extendUntil(predicate: (Long) -> Boolean) {
        while (true) {
            synchronized(knownPrimes) {
                if (predicate(knownPrimes.last())) {
                    return@extendUntil
                }
                val nextPrime = generateSequence(knownPrimes.last() + 2, { it + 2 })
                        .first { candidate ->
                            knownPrimes.takeWhile { it*it <= candidate }
                                    .none { candidate.isMultipleOf(it) }
                        }
                knownPrimes.add(nextPrime)
            }
        }
    }
}