package util.extensions

import java.util.*

fun <T,S> Iterable<T>.cartesianProduct(other: Iterable<S>): Iterable<Pair<T, S>> {
    return this.flatMap { first -> other.map { second -> first to second } }
}

// Return all combinations
fun <T> Iterable<T>.allCombinations(): Sequence<List<T>> = sequence {
    if (this@allCombinations.any()) {
        val first = this@allCombinations.first()
        yield(listOf(first))

        val rest = this@allCombinations.drop(1)
        if (rest.isNotEmpty()) {
            val restCombinations = rest.allCombinations()
            for (restCombination in restCombinations) {
                yield(restCombination)
                yield(listOf(first) + restCombination)
            }
        }
    }
}