package session

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// Package Details
//fun main() {
////    one()
//    two()
////    three()
//}

fun one() = runBlocking {
    launch {
        println("Inside Coroutine")
    }
//    delay(10)
    println("Completed")
}

// launch(), returns Job and this - Job is represented as a coroutine.
suspend fun testSuspend() {
    coroutineScope {
        print(coroutineContext)
    }
    println("Inside Suspend")
}

fun two() = runBlocking {
    val job = launch {
        repeat(5) {
            println("Inside Coroutine")
        }
        print(coroutineContext)
        testSuspend()
    }
    job.join() // Delay for the Completion of Coroutine
    println("Completed")
}

// runBlocking() – The Bridge between Blocking and Non-Blocking Worlds
// runBlocking() will create a new coroutine.
// Moreover, this coroutine will wait for all child coroutines in it to finish executing before it ends.
// In addition, runBlocking() will block the current thread.
// In other words, the current thread will blocked and will not continue until runBlocking() ends.

fun three() {
    GlobalScope.launch {
        val job = launch {
            repeat(5) {
                println("Inside Coroutine")
            }
        }
        job.join() // Delay for the Completion of Coroutine
        println("Completed")
    }
    Thread.sleep(3000)
    print("End Main")
}

// Suspend function -
// suspend is a keyword provided by Kotlin language.
// It is used to modify a function or a lambda.
// A suspending function can be suspended and later can be resumed.
// Suspending function can only be called in coroutines or other suspending functions.


//suspend fun printHello(count: Int) {
//    println("Hello $count")
//    delay(1000)
//}
//fun main() = runBlocking {
//    launch {
//        repeat(3) {
//            printHello(it)
//        }
//    }
//    repeat(3) {
//        println("--- $it")
//        delay(1000)
//    }
//    println("Complete")
//}

fun mainz() = runBlocking {
    val job = launch {
        val start = System.currentTimeMillis()
        for (i in 1..999999) {
            println("$i")
            delay(1)
        }
        val spent = System.currentTimeMillis() - start
        println("Coroutine spent $spent")
    }
    delay(70)
    job.cancel()
    println("Complete")
}


//Lazy Coroutines
fun lazyCoroutine() = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        println("Hello World")
    }
    println("Start coroutine")
    job.start()
    println("Complete")
}

// Both Default and IO dispatcher have a thread pool,
// so the dispatcher can execute multiple coroutines at the same time.

// If no dispatcher is specified,
// most coroutine builders will inherit the current dispatcher.
// Before, it was mentioned that coroutine builders will inherit the current context,
// and the context also includes a dispatcher

fun main2() {
    val startTime = System.currentTimeMillis()
    runBlocking {
        launch(Dispatchers.Unconfined) {
            delay(1000)
            println("child job: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            delay(3000)
            println("child job: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            delay(5000)
            println("child job: ${Thread.currentThread().name}")
        }
        println("parent job: ${coroutineContext}")
    }
    print("FInished in ${(System.currentTimeMillis() - startTime) / 1000}")
}

//Sometimes you want to return a value after a coroutine is executed.
// async() and launch() create a new coroutine, but async() will return a value,


fun mai2n() = runBlocking {
    launch {
        delay(1000)
        println("job: 2nd ${Thread.currentThread().name}")
    }
    withContext(Dispatchers.Default) {
        println("job: ${Thread.currentThread().name}")
        delay(5000)
        launch {
            delay(5000)
        }
        "Complete"
    }

    println("runBlocking: ${Thread.currentThread().name}")
}

fun mai4n() = runBlocking {
    coroutineScope {
        launch {
            println("job 1: ${Thread.currentThread().name}")
        }
        launch {
            println("job 2: ${Thread.currentThread().name}")
        }
    }
    println("main job: ${Thread.currentThread().name}")
}

fun main() {
    CoroutineScope(Dispatchers.Default).launch {
        launch {
            println("job 1: ${Thread.currentThread().name}")
        }
        launch {
            println("job 2: ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(5000)
    println("main job: ${Thread.currentThread().name}")
}
