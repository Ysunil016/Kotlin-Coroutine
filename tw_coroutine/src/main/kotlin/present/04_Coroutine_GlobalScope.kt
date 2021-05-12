package present

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    GlobalScope.launch {
        println("Start Global Coroutine routine on ${Thread.currentThread().name}")
        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }
        println("End Global Coroutine on ${Thread.currentThread().name}")
    }

    Thread.sleep(5000)

    println("Main End on - ${Thread.currentThread().name}")
}