package present

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start on - ${Thread.currentThread().name}")

    runBlocking {
        println("Run Block Start on - ${Thread.currentThread().name}")

        for(i in 1 until 100){
            launch(Dispatchers.Default) {
                delay(50)
                println("Value - $i -> Task on Launch - ${Thread.currentThread().name}")
            }
        }

        println("Run Block End on - ${Thread.currentThread().name}")
    }

    println("Main End on - ${Thread.currentThread().name}")
}