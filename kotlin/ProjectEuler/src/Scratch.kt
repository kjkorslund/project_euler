import problems.P35
import problems.P35.rotateLeft
import util.Primes
import util.extensions.factorial
import util.extensions.isPrime

fun main() {
    println((1..100).filter { it.isPrime() }.toList())
}