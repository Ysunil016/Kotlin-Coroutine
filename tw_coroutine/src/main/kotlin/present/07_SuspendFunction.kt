package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    val timeTaken = measureTimeMillis {
        runBlocking {
            launch(Dispatchers.Default) {
                println("Start Delay - ${Thread.currentThread().name}");
                fetchUserDelay()
                println("End Delay - ${Thread.currentThread().name}");
            }

            launch(Dispatchers.Default) {
                println("Start Thread Call - ${Thread.currentThread().name}");
                fetchUserThread()
                println("End Thread Call - ${Thread.currentThread().name}");
            }
        }
    }
    println("Time Taken $timeTaken")
}

fun fetchUserThread() {
    Thread.sleep(5000)
}

suspend fun fetchUserDelay() {
    delay(5000)
}

