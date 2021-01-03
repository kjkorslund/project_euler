package problems

import kotlin.system.measureTimeMillis

interface Problem<T> {
    fun calculate(): T

    fun name(): String? {
        return this::class.simpleName
    }
}

fun main() {
    val problems = listOf(P3, P5, P7, P9)
    for (problem in problems) {
        var answer: Any? = null
        val time = measureTimeMillis {
            answer = problem.calculate()
        }
        println("Answer for ${problem.name()}: $answer ($time ms)")
    }


//    println("Enter a problem to run, leave blank to quit")
//    generateSequence {
//        print("Problem to run? ")
//        readLine()
//    }.takeWhile {
//        !it.isEmpty()
//    }.forEach {
//        val problem : Problem<Any?> = Class.forName("problems.${it.toUpperCase()}").kotlin.objectInstance as Problem<Any?>
//        var answer: Any? = null
//        val time = measureTimeMillis {
//            answer = problem.calculate()
//        }
//        println("Answer: $answer ($time ms)")
//    }
}