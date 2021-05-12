package present

fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    functionA()
    functionB()
    functionC()

    println("Main End on - ${Thread.currentThread().name}")
}

private fun functionA(){
    Thread.sleep(2000)
    println("Function A on - ${Thread.currentThread().name}")
}

private fun functionB(){
    Thread.sleep(2000)
    println("Function B on - ${Thread.currentThread().name}")
}

private fun functionC(){
    Thread.sleep(2000)
    println("Function C on - ${Thread.currentThread().name}")
}