package present

import kotlin.concurrent.thread

fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start Thead Name - ${Thread.currentThread().name}")

    thread { functionA() }
    thread { functionB() }
    thread { functionC() }

    println("Main End Thead Name - ${Thread.currentThread().name}")
}

private fun functionA(){
    Thread.sleep(2000)
    println("Function A Thread Name - ${Thread.currentThread().name}")
}

private fun functionB(){
    Thread.sleep(2000)
    println("Function B Thread Name - ${Thread.currentThread().name}")
}

private fun functionC(){
    Thread.sleep(2000)
    println("Function C Thread Name - ${Thread.currentThread().name}")
}