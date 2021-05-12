package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import present.helper.makeApiRequest
import kotlin.system.measureTimeMillis

fun main() {
    val timeTaken = measureTimeMillis {
        val getAllIds = "http://localhost:8080/coroutine/getListOfElements/100"
        val getDetailFromId = "http://localhost:8080/coroutine/detail"

        val allIdsResponse = makeApiRequest(getAllIds)
        val allIds = parseIdFromResponse(allIdsResponse)
        val allDetailsValues = mutableListOf<String>()

        runBlocking {

            val mainContext = coroutineContext

                allIds?.forEach {
                    launch(Dispatchers.Default) {
                        val job = async(Dispatchers.IO) {
                            makeApiRequest("$getDetailFromId/$it")
                        }
                        val value = job.await() ?: "Nothing"

//                        withContext(mainContext){
                            allDetailsValues.add(value)
//                        }
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

