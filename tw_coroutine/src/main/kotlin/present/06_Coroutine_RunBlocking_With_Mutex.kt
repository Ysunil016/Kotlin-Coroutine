package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

fun mainW() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    var valueCount = 0
    runBlocking {
        for (i in 1 until 5000001) {
            launch(Dispatchers.Default) {
                valueCount++
            }
        }
    }
    println("Final Value = $valueCount")

    println("Main End on - ${Thread.currentThread().name}")
}

fun mainC() {
    var valueCount = 0
    val list = mutableListOf<Int>()
    val timeTaken = measureTimeMillis {
        runBlocking {
            val mainContext = coroutineContext
            for (i in 1 until 5000001) {
                launch(Dispatchers.Default) {
                    withContext(mainContext) {
                        valueCount++
                        list.add(i)
                    }
                }
            }
        }
    }
    println("Final Value = $valueCount & Size = ${list.size} with Time $timeTaken")
}

fun mainML() {
    var valueCount = 0
    val timeTaken = measureTimeMillis {
        runBlocking {
            val mutexLock = Mutex()
            for (i in 1 until 5000001) {
                launch(Dispatchers.Default) {
                    mutexLock.lock()
                    valueCount++
                    mutexLock.unlock()
                }
            }
        }
    }
    println("Final Value = $valueCount Time - $timeTaken")
}

fun mainM() {
    var valueCount = 0
    var list = mutableListOf<Int>()
    val timeTaken = measureTimeMillis {
        runBlocking {
            val mutexLock = Mutex()
            for (i in 1 until 5000001) {
                launch(Dispatchers.Default) {
                    mutexLock.withLock {
                        valueCount++
                        list.add(i)
                    }
                }
            }
        }
    }
    println("Final Value = $valueCount & ${list.size} with Time - $timeTaken")
}
