@file:Suppress("unused")

package problems.pg01

import problems.Problem
import util.Geometrics
import util.Primes
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

/**
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
        val wordValue = word.toCharArray().sumOf { it.code - 'A'.code + 1 }
        return wordValue.isTriangleNum()
    }
}

/**
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

/**
 * Pentagonal numbers are generated by the formula, P_n=n(3n−1)/2. The first
 * ten pentagonal numbers are:
 *
 * 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
 *
 * It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their
 * difference, 70 − 22 = 48, is not pentagonal.
 *
 * Find the pair of pentagonal numbers, Pj and Pk, for which their sum and
 * difference is pentagonal and D = |Pk − Pj| is minimised; what is the
 * value of D?
 */
object P44 : Problem<Long> {
    override fun calculate(): Long {
        // New approach:  iterate over the pentagonal sequence and find the first pentagonal that meets the answer
        // criteria (i.e. it's the difference of two other pentagonals pk and pj for which their sum is also pentagonal)
        data class PentagonalNum(val n: Long, val p: Long) {
            constructor(n: Long) : this(n, pentagonalNumber(n))
        }

        // Evaluates whether or not a given pentagonal number pDiff meets the answer criteria:
        //   1. pDiff = pk - pj, where pk and pj are pentagonal numbers
        //   2. pSum = pk + pj, where pk and pj are the same pentagonal numbers as in (1)
        // To find pk and pj, we start with pj.n = 1 and pk.n = pDiff.n, with pk-pj < pDiff.  Then the following
        // logic is applied recursively until pk.n == pj.n, at which point we know that no more solutions yielding pDiff
        // are possible:
        //   -  If pk-pj < pDiff, increment pk to the next pentagonal number
        //   -  If pk-pj > pDiff, increment pj to the next pentagonal number
        //   -  If pk-pj == pDiff, check if pk+pj is also pentagonal, and if so, return true
        //
        fun evaluateDiff(pDiff: PentagonalNum): Pair<PentagonalNum, PentagonalNum>? {
            var pj = PentagonalNum(1)
            var pk = PentagonalNum(pDiff.n)
            while(pj.n < pk.n) {
                val diff = pk.p - pj.p
                if (diff < pDiff.p) pk = PentagonalNum(pk.n + 1)
                else if (diff > pDiff.p) pj = PentagonalNum(pj.n + 1)
                else if (isPentagonal(pk.p + pj.p)) return Pair(pk, pj)
                else pk = PentagonalNum(pk.n + 1)
            }
            return null
        }

        val answer = generateSequence(1L, Long::inc).map(::PentagonalNum)
            .onEach { if (it.n % 1000 == 0L) println("Evaluating $it") }
            .firstNotNullOf(::evaluateDiff)

        println("Found (${answer.first}, ${answer.second})")
        println("  Difference: ${answer.first.p - answer.second.p}")
        println("  Sum: ${answer.first.p + answer.second.p}")

        return answer.first.p - answer.second.p
    }

    private fun pentagonalNumber(n: Long): Long {
        return (n * (3*n - 1)) / 2
    }

    private fun isPentagonal(p: Long): Boolean {
        return inversePentagonal(p) != null
    }

    private fun inversePentagonal(p: Long): Long? {
        // Original equation:
        //   p = n*(3*n − 1) / 2
        // Solving for n using the quadratic formula yields:
        //   n = (sqrt(24*p + 1) + 1) / 6
        // Returns null if there are no whole-number solutions for n
        // Note: method performance could potentially be improved using a known values cache, like with primes

        val root = ((24 * p + 1) root 2) ?: return null
        val numerator = root + 1
        return if (numerator.isMultipleOf(6)) numerator / 6 else null
    }
}

/**
 * Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:
 *    Triangle Tn=n(n+1)/2 1, 3, 6, 10, 15, ...
 *    Pentagonal Pn=n(3n−1)/2 1, 5, 12, 22, 35, ...
 *    Hexagonal Hn=n(2n−1) 1, 6, 15, 28, 45, ...
 * It can be verified that T(285) = P(165) = H(143) = 40755.
 * Find the next triangle number that is also pentagonal and hexagonal.
 */
object P45 : Problem<Long> {
    override fun calculate(): Long {
        val matches = sequence {
            val pentagonals = Geometrics.pentagonals().iterator()
            val hexagonals = Geometrics.hexagonals().iterator()
            var p = pentagonals.next()
            var h = hexagonals.next()
            for (t in Geometrics.triangles()) {
                while (p < t) p = pentagonals.next()
                while (h < t) h = hexagonals.next()
                if (t == p && t == h) yield(t)
            }
        }

        // 1 is a trivial match and 40755 is given by the problem statement so we need the third match
        return matches.take(3)
            .onEach { println("Match: $it") }
            .last()
    }
}

/**
 * It was proposed by Christian Goldbach that every odd composite number can
 * be written as the sum of a prime and twice a square.
 *   9 = 7 + 2×1^2
 *   15 = 7 + 2×2^2
 *   21 = 3 + 2×3^2
 *   25 = 7 + 2×3^2
 *   27 = 19 + 2×2^2
 *   33 = 31 + 2×1^2
 * It turns out that the conjecture was false.
 * What is the smallest odd composite that cannot be written as the sum of a
 * prime and twice a square?
 */
object P46 : Problem<Long> {
    override fun calculate(): Long {
        // Create a sequence of odd composites (note: 1 is neither prime nor a composite)
        val oddComposites = generateSequence(3L) { it + 2 }.filter { !it.isPrime() }

        // Find the first odd composite that is NOT a Goldbach match
        return oddComposites.filter { !isGoldbachMatch(it) }.first()
    }

    private fun isGoldbachMatch(n: Long): Boolean {
        val smallerPrimes = Primes.sequence().takeWhile { it < n }
        for (p in smallerPrimes) {
            val squares = Geometrics.wholes().map { it*it }
            val candidates = squares.map { p + 2*it }.takeWhile { it <= n }
            if (candidates.last() == n) return true
        }
        return false
    }
}

/**
 * The first two consecutive numbers to have two distinct prime factors are:
 *   14 = 2 × 7
 *   15 = 3 × 5
 * The first three consecutive numbers to have three distinct prime factors
 * are:
 *   644 = 2^2 × 7 × 23
 *   645 = 3 × 5 × 43
 *   646 = 2 × 17 × 19
 * Find the first four consecutive integers to have four distinct prime
 * factors. What is the first of these numbers?
 */
object P47 : Problem<Long> {
    override fun calculate(): Long {
        fun matches(targetSize: Int) = sequence {
            var streak = 0
            for (n in Geometrics.wholes()) {
                val size = n.findPrimeFactors().toSet().size
                if (size != targetSize) streak = 0
                else if (++streak == targetSize) yield(n+1-targetSize..n)
            }
        }

        val firstMatch = matches(4).first()
        println("First match: $firstMatch")
        return firstMatch.first
    }
}

/**
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
object P48 : Problem<Long> {
    override fun calculate(): Long {
        // The trick with this problem is that we don't need to calculate the full value of each power, only the last 10
        // digits.  Because of how multiplication works, the last 10 digits of each power will still be correct even if
        // all higher digits are dropped at each step of the power multiplication.  Finally all of the truncated powers
        // can be summed and truncated once more to get the final truncated result
        return Geometrics.wholes().take(1000)
            .map { it.powTruncated(it.toInt(), 10) }
            .sum().truncateToLastNDigits(10)
    }

    // Computes the last N digits of a power
    private fun Long.powTruncated(exponent: Int, digits: Int): Long {
        val truncator = 10L.pow(digits)
        var result = 1L
        repeat(exponent) {
            result = (result * this) % truncator
        }
        return result
    }

    // Truncate a number to its last N digits (e.g. 12345 truncated to last 3 = 345)
    private fun Long.truncateToLastNDigits(n: Int) = this % 10L.pow(n)
}

/**
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
 * increases by 3330, is unusual in two ways:
 *   i)  each of the three terms are prime, and
 *   ii) each of the 4-digit numbers are permutations of one another.
 *
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit
 * primes, exhibiting this property, but there is one other 4-digit
 * increasing sequence.
 *
 * What 12-digit number do you form by concatenating the three terms in this
 * sequence?
 */
object P49 : Problem<Long> {
    override fun calculate(): Long {
        // This is a bit suboptimal as it repeats the search for each prime within a permutation group, however the
        // bounds are tight enough that it can be afforded (~1k primes in the 4-digit range, 24 permutations per prime)
        // There are a number of optimizations that can later be made if this problem ends up reappearing with looser
        // bounds
        val fourDigitPrimes = Primes.sequence().dropWhile { it < 1000 }.takeWhile { it < 10000 }
        return fourDigitPrimes
            .map { primePermutations(it) }
            .filter { it.size >= 3 }
            .mapNotNull { tripleWithCommonIncrement(it) }
            .toSet()
            .onEach { println("Match: $it") }
            .last().let { (it.first.toString() + it.second.toString() + it.third.toString()).toLong() }
    }

    private fun primePermutations(p: Long): Set<Long> {
        return p.toString().lexicographicPermutations()
            .filter { it.first() != '0' } // Permutation must not have any leading zeros
            .map { it.toLong() }
            .toSet().asSequence()
            .filter { it.isPrime() }
            .toSet()
    }

    private fun tripleWithCommonIncrement(set: Set<Long>): Triple<Long, Long, Long>? {
        if (set.size < 3) return null
        val sorted = set.sorted()

        for (i in sorted.indices) {
            for (j in i+1 until sorted.size) {
                val iVal = sorted[i]
                val jVal = sorted[j]
                val increment = jVal - iVal
                val kVal = jVal + increment
                if (sorted.contains(kVal)) return Triple(iVal, jVal, kVal)
            }
        }
        return null
    }
}

/**
 * The prime 41, can be written as the sum of six consecutive primes:
 *    41 = 2 + 3 + 5 + 7 + 11 + 13
 *
 * This is the longest sum of consecutive primes that adds to a prime below
 * one-hundred.
 *
 * The longest sum of consecutive primes below one-thousand that adds to a
 * prime, contains 21 terms, and is equal to 953.
 *
 * Which prime, below one-million, can be written as the sum of the most
 * consecutive primes?
 */
object P50 : Problem<Long> {
    override fun calculate(): Long {
        // Note:  there are 78498 primes smaller than 1 million.  So algorithmic complexity does matter, at least a bit.
        //        On the other hand - it's the *sum* must be smaller than 1 million, not the individual primes, so there
        //        may be some way to disregard primes larger than a certain threshold because they could not possibly
        //        add up to a sum less than 1 million
        // Note:  the terms must be consecutive, but not necessarily starting from 2 (the 21-term sum equal to 953
        //        starts with 7 and ends with 89)
        // Note:  the minimum possible sum for an n-term sequence can be calculated by iteratively summing the primes
        //        starting from 2, disregarding whether the sum is prime or not.  The largest term with a minimum
        //        possible sum under 1 million is 546, with a minimum possible sum of 997661

        data class Result(val sum: Long, val primes: List<Long>)

        val threshold = 1_000_000L
        val primes = Primes.sequence().takeWhile { it < threshold }.toList()
        var best = Result(0L, emptyList())
        for (i in 0 .. primes.size) {
            for (j in i+1 .. primes.size) {
                val candidate = primes.subList(i, j)
                val sum = candidate.sum()
                if (sum > threshold) break
                if (candidate.size > best.primes.size && sum.isPrime()) {
                    best = Result(sum, candidate)
                    println("New leader: $best")
                }
            }
        }
        return best.sum
    }
}