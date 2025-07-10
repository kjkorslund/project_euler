@file:Suppress("unused")

package problems.pg02

import problems.Problem
import util.Primes
import util.extensions.allCombinations
import util.extensions.digits
import util.extensions.isPrime

/**
 * By replacing the 1st digit of the 2-digit number *3, it turns out that
 * six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all
 * prime.
 *
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this
 * 5-digit number is the first example having seven primes among the ten
 * generated numbers, yielding the family: 56003, 56113, 56333, 56443,
 * 56663, 56773, and 56993. Consequently 56003, being the first member of
 * this family, is the smallest prime with this property.
 *
 * Find the smallest prime which, by replacing part of the number (not
 * necessarily adjacent digits) with the same digit, is part of an eight
 * prime value family.
 */
object P51 : Problem<Long> {
    override fun calculate(): Long {
        println("Example 1: ${familySize("*3")}")
        println("Example 2: ${familySize("56**3")}")

        val answer = Primes.sequence().filter { isPotentialMatch(it) }
            .first { compatiblePatterns(it).map { familySize(it) }.contains(8) }

        // Print details, just for confirmation
        compatiblePatterns(answer).map { Pair(it, familySize(it)) }.forEach { println(it) }

        return answer
    }

    // Note: with a family of size 8+, the value of the shared digit in the smallest prime must be 2 or less.
    //       Therefore I only need to consider primes with digits 2 or less, and only check those digits for matches
    private fun isPotentialMatch(p: Long): Boolean = p.digits().filter { it <= 2 }.any()

    // Generate pattern strings that are compatible with a family size of 8 or higher (i.e. pattern strings where the
    // wildcard digit(s) are only zeros, only ones, or only twos)
    private fun compatiblePatterns(p: Long): Sequence<String> = sequence {
        val pStr = p.toString()
        for (d in '0'..'2') {
            val indices = pStr.withIndex()
                .filter { it.value == d }
                .map { it.index }
                .toList()
//            println("$d: ${indices.allCombinations().toList()}")
            for (combo in indices.allCombinations()) {
                val chars = pStr.toCharArray()
                for (index in combo) {
                    chars[index] = '*'
                }
                yield(String(chars))
            }
        }
    }

    // Given a pattern string such as '*3' or '56**3', check how many numbers matching the pattern are prime
    private fun familySize(pattern: String): Int =
        ('0'..'9').asSequence()
            .map { pattern.replace('*', it) }
            .filter { !it.startsWith('0')} // Exclude results like '03' from consideration
            .map { it.toInt()}
            .filter { it.isPrime() }
            .count()
}