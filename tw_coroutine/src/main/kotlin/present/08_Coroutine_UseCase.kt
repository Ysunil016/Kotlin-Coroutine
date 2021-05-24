package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import present.helper.makeApiRequest
import kotlin.system.measureTimeMillis

// Making API Calls, Using Single Thread
fun mainNormalExecution() {
    val timeTaken = measureTimeMillis {
        val getAllIds = "http://localhost:8080/coroutine/getListOfElements/10"
        val getDetailFromId = "http://localhost:8080/coroutine/detail"

        val allIdsResponse = makeApiRequest(getAllIds)
        val allIds = parseIdFromResponse(allIdsResponse)
        val allDetailsValues = mutableListOf<String>()

        allIds?.forEach {
            val value = makeApiRequest("$getDetailFromId/$it") ?: " Nothing "
            allDetailsValues.add(value)
            println("Adding Value $value")
        }
        println(allDetailsValues.size)
    }
    println("Time Taken $timeTaken ms")
}

// Making API Calls, Using Coroutine
fun mainCoroutineExecution() {
    val timeTaken = measureTimeMillis {
        val getAllIds = "http://localhost:8080/coroutine/getListOfElements/10"
        val getDetailFromId = "http://localhost:8080/coroutine/detail"

        val allIdsResponse = makeApiRequest(getAllIds)
        val allIds = parseIdFromResponse(allIdsResponse)
        val allDetailsValues = mutableListOf<String>()

        runBlocking {

            val mutex = Mutex()

            allIds?.forEach {
                launch(Dispatchers.IO) {
                    val job = async(Dispatchers.IO) {
                        makeApiRequest("$getDetailFromId/$it")
                    }

                    val value = job.await() ?: "Nothing"
                    println("Adding Value $value")

                    mutex.withLock {
                        allDetailsValues.add(value)
                    }
                }
            }

        }
        println(allDetailsValues.size)
    }
    println("Time Taken $timeTaken ms")
}

private fun parseIdFromResponse(allIdsResponse: String?): List<String>? {
    return allIdsResponse?.substring(1, allIdsResponse.length - 1)?.split(",")
}
