package present

import kotlin.concurrent.thread

fun mainThread() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start Thead Name - ${Thread.currentThread().name}")

    thread { emitMessageA() }
    thread { emitMessageB() }
    thread { emitMessageC() }

    println("Main End Thead Name - ${Thread.currentThread().name}")
}

private fun emitMessageA() {
    Thread.sleep(2000)
    println("Function A Thread Name - ${Thread.currentThread().name}")
}

private fun emitMessageB() {
    Thread.sleep(2000)
    println("Function B Thread Name - ${Thread.currentThread().name}")
}

private fun emitMessageC() {
    Thread.sleep(2000)
    println("Function C Thread Name - ${Thread.currentThread().name}")
}
