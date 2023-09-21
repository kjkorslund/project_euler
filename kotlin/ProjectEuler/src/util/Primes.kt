package util

import util.extensions.isMultipleOf

object Primes {
    // TODO: ideas for further improvement
    // 1. Develop a better way to manage isPrime chain extension mode.  The two modes are currently tuned based on the
    //    distance between the candidate and the max known prime in the chain.  It seems to work pretty well but the
    //    threshold was set somewhat arbitrarily.  And there might be a better heuristic I haven't considered yet.

    // Invariant: knownPrimesChain always holds an ordered list of every prime number from 2..n, where n is the last
    //            (and largest) prime number to be added to the list
    private val knownPrimesChain: MutableList<Long> = mutableListOf(2L, 3L, 5L, 7L, 11L)

    // Note:  knownPrimesSet doesn't follow the same invariant as knownPrimesChain.  The set may contain primes
    //        that are not contiguous; i.e. the presence of prime n in the set does not imply all primes < n are known.
    //        This is done for efficiency; it is only necessary to have completed the known primes chain through sqrt(n)
    //        in order to determine if n is prime
    private val knownPrimesSet: MutableSet<Long> = knownPrimesChain.toMutableSet()

    fun isPrime(candidate: Long): Boolean {
        // Tuning parameter; when the distance between the candidate and the top of the chain is large, extend only to
        // sqrt(candidate).  When the distance is small, extend all the way to candidate.  The threshold was set
        // somewhat arbitrarily; more experimentation may be useful to help refine further
        if (candidate - knownPrimesChain.last() > 10_000L) {
            extendUntil { it * it > candidate }
            if (knownPrimesSet.contains(candidate)) return true
            else if (isPrimeInternal(candidate)) {
                knownPrimesSet.add(candidate)
                return true
            }
            return false
        } else {
            extendUntil { it >= candidate }
            return knownPrimesSet.contains(candidate)
        }
    }

    fun sequence(): Sequence<Long> = sequence {
        var lastPrimeIndex = 0
        while(true) {
            extendUntil { knownPrimesChain.size > lastPrimeIndex }
            yield(knownPrimesChain[lastPrimeIndex++])
        }
    }

    private fun extendUntil(predicate: (Long) -> Boolean) {
        while (true) {
            synchronized(knownPrimesChain) {
                if (predicate(knownPrimesChain.last())) {
                    return@extendUntil
                }
                val nextPrime = generateSequence(knownPrimesChain.last() + 2) { it + 2 }
                    .first(this::isPrimeInternal)
                knownPrimesChain.add(nextPrime)
                knownPrimesSet.add(nextPrime)
            }
        }
    }

    private fun isPrimeInternal(candidate: Long): Boolean {
        synchronized(knownPrimesChain) {
            return knownPrimesChain.takeWhile { it * it <= candidate }
                .none { candidate.isMultipleOf(it) }
        }
    }
}