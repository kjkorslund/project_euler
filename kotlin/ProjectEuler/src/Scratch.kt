import util.extensions.isPermutationOf
import util.extensions.isPrime

fun main() {
    val inputs: List<Pair<String, String>> = listOf(
        Pair("abc", "cba"),
        Pair("baba", "abba"),
        Pair("foobar", "bazfoo"),
        Pair("Open Sesame 123", "seSame2  penO13"),
        Pair("abc", "abcd"),
        Pair("abcd", "abc"),
        Pair("","")
    )

    inputs.forEach { println("$it: ${it.first.isPermutationOf(it.second)}") }
}