package thoughtworks

import kotlin.system.measureTimeMillis

fun main() {
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
        println(allDetailsValues)
    }
    println("Time Taken $timeTaken ms")
}