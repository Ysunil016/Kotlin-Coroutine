package thoughtworks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val timeTaken = measureTimeMillis {
        val url = "http://localhost:8080/coroutine/fireForget"
        launch(Dispatchers.Default) { makeApiRequestWithTime(url, 5) }
        launch(Dispatchers.Default) { makeApiRequestWithTime(url, 10) }
    }
    println("Time Taken $timeTaken ms")
}
