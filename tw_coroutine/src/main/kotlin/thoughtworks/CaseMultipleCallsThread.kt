package thoughtworks

import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun test() {
    thread { Thread.sleep(5000) }
    print(Thread.activeCount())
}

fun main() {
    val timeTaken = measureTimeMillis {
        val getAllIds = "http://localhost:8080/coroutine/getListOfElements/5000"
        val getDetailFromId = "http://localhost:8080/coroutine/detail"

        val allIdsResponse = makeApiRequest(getAllIds)
        val allIds = parseIdFromResponse(allIdsResponse)
        println(allIds?.size)
        val allDetailsValues = mutableListOf<String>()
        allIds?.forEach {
            thread {
                makeApiRequest("$getDetailFromId/$it")?.let { allDetailsValues.add(it) }
            }
        }
        Thread.sleep(60000)
    }
    println("Time Taken $timeTaken ms")
}

fun parseIdFromResponse(allIdsResponse: String?): List<String>? {
    return allIdsResponse?.substring(1, allIdsResponse.length - 1)?.split(",")
}
