package util

object Geometrics {
    fun triangleNum(n: Long): Long = n * (n + 1) / 2
    fun pentagonalNum(n: Long): Long = n * (3*n - 1) / 2
    fun hexagonalNum(n: Long): Long = n * (2*n - 1)

    fun wholes() = generateSequence(1L, Long::inc)
    fun triangles() = wholes().map(::triangleNum)
    fun pentagonals() = wholes().map(::pentagonalNum)
    fun hexagonals() = wholes().map(::hexagonalNum)
}