import util.extensions.isPrime

fun main() {
    println((1..100).filter { it.isPrime() }.toList())
}