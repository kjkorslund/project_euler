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