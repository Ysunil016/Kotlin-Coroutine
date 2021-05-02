package thoughtworks

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val timeTaken = measureTimeMillis {
        val url = "http://localhost:8080/coroutine/return"
        val oneResponse = withTimeout(500) { makeApiRequestWithTime(url, 10) }
        println("One - Response $oneResponse")
    }
    println("Time Taken $timeTaken ms")
}
