package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis


fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start Thead Name - ${Thread.currentThread().name}")

    val timeTaken = measureTimeMillis {
        createThreads()
        createCoroutine()
    }

    println("Main End Thead Name - ${Thread.currentThread().name}")

    println("Time Taken - $timeTaken")
}


fun createCoroutine() {
    runBlocking {
        for (i in 1 until 10001) {
            launch(Dispatchers.Default) {
                delay(1000)
                println("on Thread - ${Thread.currentThread().name}")
            }
        }
    }
}


private fun createThreads() {
    for (id in 1 until 5000) {
        thread {
            Thread.sleep(5000)
            println("Detail for $id on - ${Thread.currentThread().name}")
        }
    }
}
