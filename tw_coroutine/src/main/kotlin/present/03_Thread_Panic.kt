package present

import kotlin.concurrent.thread

fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start Thead Name - ${Thread.currentThread().name}")

    for (i in 1 until 5000) {
        thread { functionA(i) }
    }

    println("Main End Thead Name - ${Thread.currentThread().name}")
}

private fun functionA(input: Int) {
    Thread.sleep(5000)
    println("Value $input on - ${Thread.currentThread().name}")
}