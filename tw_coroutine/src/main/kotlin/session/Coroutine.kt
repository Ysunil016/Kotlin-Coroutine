package session

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import java.io.File
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

// Out of Memory
fun threadImplementation() {
    println("Main Start")
    for (i in 1..5000) {
        thread {
            print("$i.")
            Thread.sleep(5000)
        }
    }
    println("Main Close")
}

fun mai3n() {
    println("Main Start")
    GlobalScope.launch {
        launch(Dispatchers.Default) {
            println("Inside Coroutine")
            testM()
        }
    }
    Thread.sleep(10000)
    println("Main Close")
}

fun testM() {
    repeat(5000) {
        println("Inside Function")
    }
    File("test.txt").writeText("XYZ")
}

// Coroutine
fun mai20n() {
    runBlocking {
        println("Main Start")
        val job = launch(Dispatchers.Default) {
            delay(5000)
        }

        job.cancel()
    }
    println("Main Close")
}

fun main20() {
    runBlocking {
        println("Main Start")

        val time = measureTimeMillis {
            val job1: Deferred<Int> = async(Dispatchers.Default) {
                delay(5000)
                250
            }
            val job2: Deferred<Int> = async(Dispatchers.Default) {
                delay(10000)
                400
            }
            print("${job1.await() + job2.await()}")
        }
        println("Time ${time}")

        println()
    }
    println("Main Close")
}

fun main7() {
    runBlocking {
        println("Main Start")

        val time = measureTimeMillis {
            val job1: Deferred<Int> = async {
                println("11 ${Thread.currentThread().name}")
                delay(5000)
                println("12 ${Thread.currentThread().name}")
                250
            }
            val job2: Deferred<Int> = async {
                delay(10000)
                println("2 ${Thread.currentThread().name}")
                400
            }
            print("${job1.await() + job2.await()}")
        }
        println("Time ${time}")

        println()
    }
    println("Main Close")
}

fun mai23n() {
    runBlocking {
        println("Main Start")

        val time = measureTimeMillis {
            val job1: Job = launch {
                println("11 ${Thread.currentThread().name}")
                delay(5000)
                println("12 ${Thread.currentThread().name}")
            }
            val job2: Job = launch {
                delay(10000)
                println("2 ${Thread.currentThread().name}")
            }
//            job1.join()
            println("END")
        }
        println("Time ${time}")

        println()
    }
    println("Main Close")
}


fun main98() {
    runBlocking {
        println("Main Start")
        val job = launch(Dispatchers.Default) {
            try {
                repeat(500) {
                    delay(5000)
                }
            } catch (e: CancellationException) {
                println("Cancel Cancelled")

            }

        }
        delay(500)
        job.cancelAndJoin()
    }
    println("Main Close")
}


fun main56() {
    runBlocking {
        println("Main Start")
        val job = launch(Dispatchers.Default) {
            try {
                repeat(500) {
                    delay(5000)
                }
            } catch (e: CancellationException) {
                println("Cancel Cancelled")
            }
        }
        delay(500)
        job.cancelAndJoin()
    }
    println("Main Close")
}


fun mai232n() {
    runBlocking {
        println("Main Start")
        val job = launch(Dispatchers.Default, start = CoroutineStart.LAZY) {
            try {
                repeat(500) {
                    delay(5000)
                }
            } catch (e: CancellationException) {
                println("Cancel Cancelled")
            }
        }
        job.start()

        delay(500)
    }
    println("Main Close")
}


fun main() {
    runBlocking {
        println("Main Start")
        val z = withTimeoutOrNull(2000L) {
            try {
                repeat(500) {
                    delay(5000)
                }
            } catch (e: TimeoutCancellationException) {
                println("Cancelled")
            }
            "123"
        }
        println(z)
        delay(500)
    }
    println("Main Close")
}


