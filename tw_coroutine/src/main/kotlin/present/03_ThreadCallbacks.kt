package present

import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    Thread.currentThread().name = "Main Thread"

    val timeTaken = measureTimeMillis {
        println("Main Start Thead Name - ${Thread.currentThread().name}")

        val threadA = thread { fetchUser { saveUserToDatabase { emitSuccessMessage() } } }
        val threadB = thread { fetchUser { saveUserToDatabase { emitSuccessMessage() } } }

        threadA.join()
        threadB.join()

        println("Main End Thead Name - ${Thread.currentThread().name}")
    }

    println("Time Taken - $timeTaken")
}


private fun fetchUser(callback: () -> Unit) {
    Thread.sleep(2000)
    println("User Fetched - ${Thread.currentThread().name}")
    callback()
}

private fun saveUserToDatabase(callback: () -> Unit) {
    Thread.sleep(4000)
    println("User Saved in Database - ${Thread.currentThread().name}")
    callback()
}

private fun emitSuccessMessage() {
    Thread.sleep(4000)
    println("Emitting Success Message - ${Thread.currentThread().name}")
}