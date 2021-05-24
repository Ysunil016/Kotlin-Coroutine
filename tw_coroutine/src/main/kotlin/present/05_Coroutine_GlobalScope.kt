package present

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Fire and Forget
fun mainOne() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    // Coroutine Builder
    GlobalScope.launch {

        println("Start Global Coroutine routine on ${Thread.currentThread().name}")

        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }

        println("End Global Coroutine on ${Thread.currentThread().name}")
    }

    println("Main End on - ${Thread.currentThread().name}")
}

// Sleep to Observe.
fun mainTwo() {
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

    Thread.sleep(50)

    println("Main End on - ${Thread.currentThread().name}")
}

// Proper Wait for Coroutine to Finish
fun mainThree() = runBlocking {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    val job: Job = GlobalScope.launch {

        println("Start Global Coroutine routine on ${Thread.currentThread().name}")

        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }

        println("End Global Coroutine on ${Thread.currentThread().name}")

    }

    job.join()

    println("Main End on - ${Thread.currentThread().name}")
}