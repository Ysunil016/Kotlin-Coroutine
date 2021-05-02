package thoughtworks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val timeTaken = measureTimeMillis {
        val url = "http://localhost:8080/coroutine/return"
        val oneResponse = async(Dispatchers.IO) { makeApiRequestWithTime(url, 20) }

        delay(1000)
        println("XX")
        oneResponse.cancelAndJoin()
        try {
            println("One - Response ${oneResponse.await()}")
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }
    println("Time Taken $timeTaken ms")
}
