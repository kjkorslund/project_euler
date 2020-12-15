package util.stringextensions

fun String.isPalindrome(): Boolean {
    val median = (this.length - 1) / 2
//    println("Median of '$this': $median")
    return (0..median).asSequence()
            .all {
                val pair = (this.length - 1) - it
//                println("Checking $it-$pair pair: ${this[it]} == ${this[pair]}")
                this[it] == this[pair]
            }
}