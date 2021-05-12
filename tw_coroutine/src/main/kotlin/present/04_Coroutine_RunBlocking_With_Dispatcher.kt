package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    runBlocking(Dispatchers.Default) {
        println("Start Global Coroutine routine on ${Thread.currentThread().name}")
        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }
        println("End Global Coroutine on ${Thread.currentThread().name}")
    }

    println("Main End on - ${Thread.currentThread().name}")
}