package present

fun mainNormal() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    emitMessageA()
    emitMessageB()
    emitMessageC()

    println("Main End on - ${Thread.currentThread().name}")
}

private fun emitMessageA(){
    Thread.sleep(2000)
    println("Function A on - ${Thread.currentThread().name}")
}

private fun emitMessageB(){
    Thread.sleep(2000)
    println("Function B on - ${Thread.currentThread().name}")
}

private fun emitMessageC(){
    Thread.sleep(2000)
    println("Function C on - ${Thread.currentThread().name}")
}