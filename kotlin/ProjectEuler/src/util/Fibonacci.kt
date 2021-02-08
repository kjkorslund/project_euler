package util

import java.math.BigInteger

object Fibonacci {
    fun bigIntegerSequence() = sequence<BigInteger> {
        yield(BigInteger.ONE)
        yield(BigInteger.ONE)
        var last = BigInteger.ONE
        var last2 = BigInteger.ONE
        while(true) {
            val next = last + last2
            yield(next)
            last2 = last
            last = next
        }
    }

    fun longSequence() = sequence<Long> {
        yield(1L)
        yield(1L)
        var last = 1L
        var last2 = 1L
        while(true) {
            val next = last + last2
            yield(next)
            last2 = last
            last = next
        }
    }

    fun intSequence() = sequence<Int> {
        yield(1)
        yield(1)
        var last = 1
        var last2 = 1
        while(true) {
            val next = last + last2
            yield(next)
            last2 = last
            last = next
        }
    }
}