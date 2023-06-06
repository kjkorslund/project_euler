package util.extensions

/**
 * @param other int range to compare against
 * @return true if this int range contains the other int range, false otherwise
 */
//fun IntRange.contains(other: IntRange): Boolean = this.first <= other.first && this.last >= other.last

fun <T: Comparable<T>> ClosedRange<T>.contains(other: ClosedRange<T>): Boolean {
    return this.start <= other.start && this.endInclusive >= other.endInclusive
}

fun IntRange.size(): Int = 1 + this.last - this.first