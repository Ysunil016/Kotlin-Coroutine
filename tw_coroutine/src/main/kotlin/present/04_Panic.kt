package present

import kotlin.concurrent.thread


fun mainPanic() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start Thead Name - ${Thread.currentThread().name}")

    for (id in 1 until 5000) {
        thread { fetchDetails(id) }
    }

    println("Main End Thead Name - ${Thread.currentThread().name}")
}

private fun fetchDetails(id: Int) {
    Thread.sleep(5000)
    println("Detail for $id on - ${Thread.currentThread().name}")
}