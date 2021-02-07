@file:Suppress("unused")
package problems

import kotlin.math.max

/**
 * [Problem 64](https://projecteuler.net/problem=64):  How many continued fractions for N <= 10,000 have an odd period?
 */
object P64 : Problem<Int> {
    override fun calculate(): Int {
        // TODO: figure out how to solve the subproblem
        return -2;
    }
}

/**
 * [Problem 67](https://projecteuler.net/problem=67)
 * Find the maximum total from top to bottom of the triangle (see p67_triangle.txt)
 */
object P67: Problem<Long> {
    private val triangleStrings = this.readResource("p67_triangle.txt") { reader ->
        reader.useLines { it.toList() }
    }

    override fun calculate(): Long {
        triangleStrings.forEach { println(it) }

        val triangle = triangleStrings.map { it.split(' ').map(String::toLong)}
        fun Long.toStringPadded(len: Int) = this.toString().padStart(len, ' ')

        return triangle.reduceRight {secondToLast, last ->
            val combined = secondToLast.mapIndexed { index, it ->
                max(it + last[index], it + last[index+1])
            }
            combined
        }.first()
    }
}