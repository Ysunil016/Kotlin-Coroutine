package thoughtworks

import kotlin.system.measureTimeMillis

fun main() {
    val timeTaken = measureTimeMillis {
        val url = "http://localhost:8080/coroutine/return"
        val oneResponse = makeApiRequestWithTime(url, 5)
        val twoResponse = makeApiRequestWithTime(url, 10)

        println("Job One - Response $oneResponse")
        println("Job Two - Response $twoResponse")
    }
    println("Time Taken $timeTaken ms")
}
