package util.extensions

fun <T,S> Iterable<T>.cartesianProduct(other: Iterable<S>): Iterable<Pair<T, S>> {
    return this.flatMap { first -> other.map { second -> first to second } }
}