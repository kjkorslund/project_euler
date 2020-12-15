import org.junit.jupiter.api.Test
import util.numericextensions.*

class LongExtensionsTests {
    @Test
    fun scratch() {
//        for (l: Long in -1..100L) {
//            println("$l: ${l.findSmallestPrimeFactor()}")
//        }

//        println((-1L..100000L).asSequence()
//                .filter { it.isPrime() }
//                .joinToString())

        for(l in -1L..100L) {
            println("$l: ${l.findPrimeFactors()} -> ${l.findPrimeFactors().takeIf { it.isNotEmpty() }?.last()}")
        }
    }

    @Test
    fun testEvenOdd() {
        for (l: Long in -5L..5L) {
            val classification = when {
                l.isOdd() -> "odd"
                l.isEven() -> "even"
                else -> "unknown"
            }
            println("$l: $classification")
        }
    }
}