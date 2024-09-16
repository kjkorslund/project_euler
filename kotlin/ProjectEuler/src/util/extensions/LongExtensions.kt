package util.extensions

import util.Primes

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
    return results
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

/**
 * Returns the sequence of base-10 digits composing this number, from smallest to largest place
 */
fun Long.digits(): Sequence<Int> = sequence {
    var remainder = this@digits
    while(remainder > 0) {
        yield((remainder % 10).toInt())
        remainder /= 10
    }
}

/**
 * Composes a Long from a sequence of base-10 digits, from largest to smallest place
 * (note that the order is reversed as compared to method Long.digits() )
 */
fun Long.Companion.fromDigits(digits: Iterable<Int>): Long {
    var result = 0L
    for (digit in digits) {
        result = result*10 + digit
    }
    return result
}

fun Long.pow(exponent: Int): Long {
    var result = 1L
    repeat(exponent) {
        result *= this
    }
    return result
}

/**
 * Given an exponent, return the non-fractional root of this Long for that exponent, if one exists.
 * All candidates are searched starting from 1, which is probably not the fastest way to do this.
 */
infix fun Long.root(exponent: Int): Long? {
    var l = 1L;
    var pow = l.pow(exponent);
    while(pow < this) {
        pow = (++l).pow(exponent)
    }
    return if (pow == this) l else null
}

fun Long.factorial(): Long? = when {
    this < 0L -> null
    this in (0L..1L) -> 1L
    else -> this * (this-1).factorial()!!
}
