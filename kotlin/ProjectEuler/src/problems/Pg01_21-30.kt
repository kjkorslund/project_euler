@file:Suppress("unused")
package problems

import util.numericextensions.*

/**
 * [Problem 21](https://projecteuler.net/problem=21)
 * Evaluate the sum of all the amicable numbers under 10000.
 */
object P21: Problem<Long> {
    override fun calculate(): Long {
        return (1L..9999L)
            .filter { it.getAmicablePartner() != null }
//            .also { println(it) }
            .sum()
    }

    // d(n), as defined in the problem statement
    private fun d(n: Long) = n.findDivisors().dropLast(1)
//        .also {println("d($n) = ${it.joinToString("+")}")}
        .sum()

    private fun Long.getAmicablePartner(): Long? {
        val pair = d(this)
        return pair.takeIf {
            it != this && d(it) == this
        }
    }
}

/**
 * [Problem 22](https://projecteuler.net/problem=22)
 * What is the total of all the name scores in the file?
 */
object P22: Problem<Long> {
    private val names = this.readResource("p22_names.txt") { reader ->
        Regex("\"(\\w+)\"").findAll(reader.readText())
            .mapNotNull { it.groups[1]?.value }
            .toList()
//            .also(::println)
    }

    override fun calculate(): Long {
//        "COLIN".let { println("$it = ${nameScore(it)}") }
        return names.sorted().asSequence()
//            .also(::println)
//            .also {println(it.indexOfFirst { it == "COLIN" }.inc())}
            .mapIndexed {index, it -> index.inc() * nameScore(it)}
            .sum()
    }

    private fun nameScore(name: String): Long =
        name.asSequence().map { (it - 'A' + 1).toLong() }
//            .onEach { println(it) }
            .sum()

}