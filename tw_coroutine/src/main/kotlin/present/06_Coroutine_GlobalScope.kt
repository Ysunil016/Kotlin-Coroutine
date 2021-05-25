package present

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Fire and Forget
fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

//    fireAndForget()
//    fireAndForgetWithSleep()
//    waitForCoroutineCompletion()

    println("Main End on - ${Thread.currentThread().name}")
}


private fun fireAndForget() {
    GlobalScope.launch {

        println("Start Global Coroutine routine on ${Thread.currentThread().name}")

        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }

        println("End Global Coroutine on ${Thread.currentThread().name}")
    }
}

// Sleep to Observe.
fun fireAndForgetWithSleep() {
    GlobalScope.launch {

        println("Start Global Coroutine routine on ${Thread.currentThread().name}")

        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }

        println("End Global Coroutine on ${Thread.currentThread().name}")
    }

    Thread.sleep(50)
}

// Proper Wait for Coroutine to Finish
fun waitForCoroutineCompletion() = runBlocking {
    val job: Job = GlobalScope.launch {

        println("Start Global Coroutine routine on ${Thread.currentThread().name}")

        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }

        println("End Global Coroutine on ${Thread.currentThread().name}")

    }

    job.join()
}


//## Use - Case

/**
 *      Background job, that dumps data by making multiple network calls.
 *      and returns nothing.
 *
 *      ** Let's say Task took - 40 Mins.
 *
 *      In this case we can use GlobalScope.launch { }. -> Fire and Forget.
 *
 *      ** GlobalScope, Lives to Entire the Application's Life.
 */


