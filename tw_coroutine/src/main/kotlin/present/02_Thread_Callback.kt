package present

import kotlin.concurrent.thread

fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start Thead Name - ${Thread.currentThread().name}")

    thread { functionA { callableAFunction(10) { callableBFunction() } } }
    thread { functionB() }
    thread { functionC() }

    println("Main End Thead Name - ${Thread.currentThread().name}")
}

private fun callableAFunction(v:Int,callback: () -> Unit) {
    Thread.sleep(4000)
    println("Callback A $v")
    callback()
}

private fun callableBFunction() {
    Thread.sleep(4000)
    println("Callback B")
}

private fun functionA(callFunction: () -> Unit) {
    Thread.sleep(2000)
    println("Function A Thread Name - ${Thread.currentThread().name}")
    callFunction()
}

private fun functionB() {
    Thread.sleep(2000)
    println("Function B Thread Name - ${Thread.currentThread().name}")
}

private fun functionC() {
    Thread.sleep(2000)
    println("Function C Thread Name - ${Thread.currentThread().name}")
}