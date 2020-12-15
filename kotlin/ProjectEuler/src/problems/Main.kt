package problems

import kotlin.reflect.KClass
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

interface Problem<T> {
    fun calculate(): T
}

fun main() {
    val problem = P5
    var answer: Any? = null
    val time = measureTimeMillis {
        answer = problem.calculate()
    }
    println("Answer: $answer ($time ms)")


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