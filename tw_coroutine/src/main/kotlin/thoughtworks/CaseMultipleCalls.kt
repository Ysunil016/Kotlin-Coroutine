package thoughtworks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking(Dispatchers.Default) {
        val timeTaken = measureTimeMillis {
            val getAllIds = "http://localhost:8080/coroutine/getListOfElements/500"
            val getDetailFromId = "http://localhost:8080/coroutine/detail"

// By Default Dispatchers.IO, has 64 Thread Pool Size
//            512825 ms - ~ 8 min. - 5000 Entries
//            610010 ms - ~ 10 min - 6000 Entries
//            80480  ms - ~ 8 Sec  - 1000 Entries.

            val allIdsResponse = withContext(Dispatchers.IO) { makeApiRequest(getAllIds) }
            val allIds = parseIdFromResponse(allIdsResponse)
            val allDetailsValues = mutableListOf<String>()
//            runBlocking(Executors.newFixedThreadPool(4000).asCoroutineDispatcher()) {
            runBlocking {
                allIds?.forEach {
                    launch {
                        val job = async(Dispatchers.IO) {
                            makeApiRequest("$getDetailFromId/$it")
                        }
                        allDetailsValues.add(job.await() ?: "Nothing")
                        println(System.currentTimeMillis())
                    }
                }
            }
            println(allDetailsValues.size)
        }
        println("Time Taken $timeTaken ms")
    }
}