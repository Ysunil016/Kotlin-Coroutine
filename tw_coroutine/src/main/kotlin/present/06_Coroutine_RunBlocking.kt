package present

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

// Default Nature of Coroutine w.r.t its Scope
fun mainDefault() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    runBlocking {

        println("Started on ${Thread.currentThread().name}")

        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }

        println("Ended on ${Thread.currentThread().name}")
    }

    println("Main End on - ${Thread.currentThread().name}")
}


//Coroutine with Definite Dispatcher Selection
fun mainDispatcher() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    runBlocking(Dispatchers.Default) {

        println("Start Coroutine routine on ${Thread.currentThread().name}")

        for (i in 1 until 10) {
            println("Value on $i ${Thread.currentThread().name}")
            delay(400)
        }

        println("End Global Coroutine on ${Thread.currentThread().name}")
    }

    println("Main End on - ${Thread.currentThread().name}")
}


// Coroutine for Async Task - Launch (No Return)
fun mainLaunch() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    runBlocking {
        println("Run Block Start on - ${Thread.currentThread().name}")

        for (i in 1 until 100) {
            launch(Dispatchers.Default) {
                delay(5000)
                println("Value - $i -> Task on Launch - ${Thread.currentThread().name}")
            }
        }

        println("Run Block End on - ${Thread.currentThread().name}")
    }

    println("Main End on - ${Thread.currentThread().name}")
}


// Coroutine for Async Task - Time Taken
fun mainTimeTaken() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    val timeTaken = measureTimeMillis {
        runBlocking {
            println("Run Block Start on - ${Thread.currentThread().name}")

            for (i in 1 until 100) {
                launch(Dispatchers.Default) {
                    delay(5000)
                    println("Value - $i -> Task on Launch - ${Thread.currentThread().name}")
                }
            }

            println("Run Block End on - ${Thread.currentThread().name}")
        }
    }

    println("Time Taken - $timeTaken")

    println("Main End on - ${Thread.currentThread().name}")
}


// Coroutine for Async Task - Async (Returning Function)
fun mainAsync() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    val timeTaken = measureTimeMillis {
        runBlocking {
            println("Run Block Start on - ${Thread.currentThread().name}")

            val opOne = async { delay(5000); 100 }
            val opTwo = async { delay(4000); 500 }

            val valueOne = opOne.await()
            val valueTwo = opTwo.await()

            println("Final Sum : ${valueOne + valueTwo}")

            println("Run Block End on - ${Thread.currentThread().name}")
        }
    }
    println("Time Taken - $timeTaken")

    println("Main End on - ${Thread.currentThread().name}")


}


// Coroutine leading Data Lost - Dirty Read and Write
fun mainDataLost() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    val totalCount = 50001;
    val buffer = mutableListOf<Int>();

    runBlocking {
        println("Run Block Start on - ${Thread.currentThread().name}")


        for (i in 1 until totalCount) {

            launch(Dispatchers.Default) {
                delay(2)
                buffer.add(i)
            }

        }

        println("Run Block End on - ${Thread.currentThread().name}")
    }

    println("Buffer Size - Expected : ${totalCount - 1} - Actual : ${buffer.size} ")

    println("Main End on - ${Thread.currentThread().name}")

}


// Resolve Coroutine leading Data Lost - Jugad Methodology (Not - Recommended)
fun mainJugad() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    val totalCount = 50001;
    val buffer = mutableListOf<Int>();

    runBlocking {
        val currentContext = coroutineContext

        println("Run Block Start on - ${Thread.currentThread().name}")


        for (i in 1 until totalCount) {

            launch(Dispatchers.Default) {
                delay(20)

                withContext(currentContext) {
                    buffer.add(i)
                }
            }

        }

        println("Run Block End on - ${Thread.currentThread().name}")
    }

    println("Buffer Size - Expected : ${totalCount - 1} - Actual : ${buffer.size} ")

    println("Main End on - ${Thread.currentThread().name}")

}


// Resolve Coroutine leading Data Lost - Mutex Lock
fun mainMutex() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    val totalCount = 500001;
    val buffer = mutableListOf<Int>();

    runBlocking {
        val mutex = Mutex()

        println("Run Block Start on - ${Thread.currentThread().name}")


        for (i in 1 until totalCount) {

            launch(Dispatchers.Default) {
                delay(20)

                mutex.withLock {
                    buffer.add(i)
                }
            }

        }

        println("Run Block End on - ${Thread.currentThread().name}")
    }

    println("Buffer Size - Expected : ${totalCount - 1} - Actual : ${buffer.size} ")

    println("Main End on - ${Thread.currentThread().name}")

}


// Exception Handling in Coroutine
fun mainException() {
    runBlocking {
        val handleException =
            CoroutineExceptionHandler { _, throwable -> println("Handler " + throwable.localizedMessage) }

        val job = GlobalScope.launch(handleException) {
            throw RuntimeException("Exception Message")
        }
        job.join()
    }
}


