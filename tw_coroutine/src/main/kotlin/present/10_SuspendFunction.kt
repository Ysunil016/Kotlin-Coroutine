package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(){
    val timeTaken = measureTimeMillis {
        runBlocking {
            launch(Dispatchers.Unconfined) { functionA() }
            launch(Dispatchers.Default) { functionB() }
        }
    }
    println("Time Taken $timeTaken")
}

private suspend fun functionA() {
    println("Thread ${Thread.currentThread().name}")
    println("Function A Start")
//    delay(5000L)
    Thread.sleep(5000)
    println("Function A End")
    test()
}

private suspend fun test(){
    delay(5000)
    println("Thread XXX ${Thread.currentThread().name}")
}

private fun functionB() {
    println("Thread ${Thread.currentThread().name}")
    println("Function B Start")
//    delay(5000L)
    Thread.sleep(5000)

    println("Function B End")
}