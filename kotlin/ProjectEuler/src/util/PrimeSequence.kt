package util

import util.numericextensions.isMultipleOf

fun primeSequence(): Sequence<Long> = object: Sequence<Long> {
    private val knownPrimes: MutableList<Long> = arrayListOf(2L, 3L)

    private fun Long.isPrime() = knownPrimes
            .takeWhile { it*it <= this }
            .none { this.isMultipleOf(it) }

    override fun iterator(): Iterator<Long> = object: Iterator<Long> {
        private var lastPrime: Long? = null

        override fun hasNext() = true

        override fun next(): Long {
            val nextPrime = when(val lastPrime = this.lastPrime) {
                null -> 2L
                2L -> 3L
                else -> {
                    if (knownPrimes.last() > lastPrime ) {
                        knownPrimes[knownPrimes.binarySearch(lastPrime) + 1]
                    } else {
                        val np = generateSequence(lastPrime+2) { it+2 }
                                .first { it.isPrime() }
                        knownPrimes.add(np)
                        np
                    }
                }
            }
            this.lastPrime = nextPrime
            return nextPrime
        }
    }
}
