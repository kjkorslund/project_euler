package problems.pg01

import problems.Problem
import util.extensions.*


/**
 * We shall say that an n-digit number is pandigital if it makes use of all
 * the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital
 * and is also prime.
 *
 * What is the largest n-digit pandigital prime that exists?
 */
object P41 : Problem<Long> {
    override fun calculate(): Long {
        // New approach:  search for pandigital numbers within the known range for each digit count (e.g. 300..321 for
        //                3-digit pandigitals).  Check each found pandigital number to see if it's prime.
        //                Note: potential future improvement would be to use some sort of 'permutations iterator' rather
        //                      than just searching the entire range.  Most numbers in the range are not pandigital.
        // Note: it's important to convert to a sequence so the operations will be evaluated lazily
        return (1..9).asSequence()
            .map { pandigitalRange(it) }.flatten()
            .filter { isAnyPandigital(it) }
            .filter { it.isPrime() }
//            .onEach { println("Found: $it") }
            .last()

        // Old approach (too slow):  iterate through primes until the value gets larger than the largest possible
        //                           pandigital number.  Check if each prime is pandigital.  Keep the last result found.
//        return Primes.sequence().takeWhile { it < 1_000_000_000 }
//            .filter(::isAnyPandigital)
//            .onEach { println("Found: $it") }
//            .last();
    }

    private fun isAnyPandigital(num: Long): Boolean {
        val digits = num.toString()
        if (digits.length > 9) return false
        val requiredDigits = "123456789".slice(digits.indices)
        return digits.isPermutationOf(requiredDigits)
    }

    private fun pandigitalRange(maxDigit: Int): LongRange {
        // TODO: bug.  The range should start at 1[0...], not {maxDigit}[0...]
        //       e.g. 1234 is pandigital even though it's not in range 4000..4321
        var start = maxDigit.toLong()
        var end = maxDigit.toLong()
        for (i in 1 until maxDigit) {
            start *= 10
            end = end*10 + (maxDigit-i)
        }
        return start..end
    }
}

/*
 * The nth term of the sequence of triangle numbers is given by, tn =
 * ½n(n+1); so the first ten triangle numbers are:
 *
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 *
 * By converting each letter in a word to a number corresponding to its
 * alphabetical position and adding these values we form a word value. For
 * example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word
 * value is a triangle number then we shall call the word a triangle word.
 *
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text
 * file containing nearly two-thousand common English words, how many are
 * triangle words?
 */
object P42 : Problem<Int> {
    override fun calculate(): Int {
        val triangleSequence = generateSequence(1, Int::inc).map { (it * (it+1)) / 2 }
        triangleSequence.take(20).joinToString().let { println(it) }

        val words = readResource("p42_words-modified.txt") { reader ->
            reader.readText().lines()
        }
//        words.forEach { println(it) }

        return words.count(this::isTriangleWord)
    }

    private fun isTriangleWord(word: String): Boolean {
        val wordValue = word.toCharArray().map { it.code - 'A'.code + 1 }.sum()
        return wordValue.isTriangleNum();
    }
}

/*
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made
 * up of each of the digits 0 to 9 in some order, but it also has a rather
 * interesting sub-string divisibility property.
 *
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we
 * note the following:
 *
 *    d2d3d4=406 is divisible by 2
 *    d3d4d5=063 is divisible by 3
 *    d4d5d6=635 is divisible by 5
 *    d5d6d7=357 is divisible by 7
 *    d6d7d8=572 is divisible by 11
 *    d7d8d9=728 is divisible by 13
 *    d8d9d10=289 is divisible by 17
 *
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */
object P43 : Problem<Long> {
    override fun calculate(): Long {
        return "0123456789".lexicographicPermutations()
            .filter(this::hasDivisibilityProperty)
            .map(String::toLong)
            .sum()
    }

    private fun hasDivisibilityProperty(digits: String): Boolean {
        // Note: substring ranges are one less than in the problem statement due to the string indices being zero-based
        //       instead of ones-based
        return digits.substring(1..3).toLong().isMultipleOf(2)
                && digits.substring(2..4).toLong().isMultipleOf(3)
                && digits.substring(3..5).toLong().isMultipleOf(5)
                && digits.substring(4..6).toLong().isMultipleOf(7)
                && digits.substring(5..7).toLong().isMultipleOf(11)
                && digits.substring(6..8).toLong().isMultipleOf(13)
                && digits.substring(7..9).toLong().isMultipleOf(17)
    }

}