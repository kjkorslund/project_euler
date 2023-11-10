package util.extensions

/**
 * Returns `true` if this value is palindromic (e.g. "racecar")
 */
fun String.isPalindrome(): Boolean {
    val median = (this.length - 1) / 2
//    println("Median of '$this': $median")
    return (0..median).all {
            val pair = (this.length - 1) - it
//            println("Checking $it-$pair pair: ${this[it]} == ${this[pair]}")
            this[it] == this[pair]
        }
}

/**
 * Returns the sequence of lexicographic permutations of this string, i.e. a sequence of all the possible arrangements
 * of the characters in the string, ordered in lexicographic order
 */
fun String.lexicographicPermutations(): Sequence<String> {
    fun _lexicographicPermutations(sortedChars: String): Sequence<String> = sequence {
        if (sortedChars.length == 2) {
            yieldAll(sequenceOf(sortedChars, sortedChars.reversed()))
        } else {
            for (ch in sortedChars.withIndex()) {
                val remainingChars = sortedChars.removeRange(ch.index..ch.index)
                yieldAll(_lexicographicPermutations(remainingChars).map {
                    ch.value + it
                })
            }
        }
    }

    return when(this.length) {
        0 -> emptySequence()
        1 -> sequenceOf(this)
        else -> _lexicographicPermutations(this.toCharArray().also { it.sort() }.concatToString())
    }
}

/**
 * Returns true if the given string is a permutation of this string, i.e. it has the same exact characters, but possibly
 * in a different order (e.g. "baba" and "abba").  Case-sensitive.
 */
fun String.isPermutationOf(other: String): Boolean {
    if (this.length != other.length) return false

    val sortedChars = this.toCharArray().also(CharArray::sort)
    val otherSortedChars = other.toCharArray().also(CharArray::sort)
    return sortedChars.contentEquals(otherSortedChars)
}