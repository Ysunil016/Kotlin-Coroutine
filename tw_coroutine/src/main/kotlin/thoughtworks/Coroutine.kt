package thoughtworks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val timeTaken = measureTimeMillis {
        val url = "http://localhost:8080/coroutine/return"
        val oneResponse = async(Dispatchers.IO) { makeApiRequestWithTime(url, 5) }
        val twoResponse = async(Dispatchers.IO) { makeApiRequestWithTime(url, 10) }

        println("One - Response ${oneResponse.await()}")
        println("Two - Response ${twoResponse.await()}")
    }
    println("Time Taken $timeTaken ms")
}
