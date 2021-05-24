package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import present.helper.makeApiRequest
import kotlin.system.measureTimeMillis

const val fetchElementListURI: String = "http://localhost:8080/coroutine/getListOfElements/10"
const val fetchDetailsURI = "http://localhost:8080/coroutine/detail"

fun main() {
    val timeTaken = measureTimeMillis {

//        singleThreadedExecution()
//        coroutineExecution()

    }


    println("Time Taken $timeTaken ms")
}

private fun singleThreadedExecution() {
    val getAllIds = fetchElementListURI
    val getDetailFromId = fetchDetailsURI

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

private fun coroutineExecution() {
    val getAllIds = fetchElementListURI
    val getDetailFromId = fetchDetailsURI

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

private fun parseIdFromResponse(allIdsResponse: String?): List<String>? {
    return allIdsResponse?.substring(1, allIdsResponse.length - 1)?.split(",")
}