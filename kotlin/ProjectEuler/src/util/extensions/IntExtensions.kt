package util.extensions

/**
 * Returns `true` if this value is a multiple of another value
 */
fun Int.isMultipleOf(n: Int) = (this % n == 0)

/**
 * Returns `true` if this value is even
 */
fun Int.isEven() = (this and 1 == 0)

/**
 * Returns `true` if this value is odd
 */
fun Int.isOdd() = (this and 1 != 0)

/**
 * Returns the sequence of base-10 digits composing this number, from smallest to largest place
 */
fun Int.digits(): Sequence<Int> = sequence {
    var remainder = this@digits
    while(remainder > 0) {
        yield(remainder % 10)
        remainder /= 10
    }
}

fun Int.pow(exponent: Int): Long {
    var result = 1L
    repeat(exponent) {
        result *= this
    }
    return result
}