package present

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    val timeTaken = measureTimeMillis {
        runBlocking {
            println("Run Block Start on - ${Thread.currentThread().name}")

            val r1 = async { valueA() }
            val r2 = async { valueB() }

            println("Final Sum : ${r1.await() + r2.await()}")

            println("Run Block End on - ${Thread.currentThread().name}")
        }
    }
    println("Time Taken - $timeTaken")

    println("Main End on - ${Thread.currentThread().name}")
}

private suspend fun valueA(): Int {
    delay(5000)
    return 410
}

private suspend fun valueB(): Int {
    delay(10000)
    return 90
}