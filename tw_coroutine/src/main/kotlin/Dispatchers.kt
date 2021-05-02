import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//      launch(Dispatchers.Main) {
//        println("Main Dispatcher ${Thread.currentThread().name}")
//      }

      launch(Dispatchers.Unconfined) {
        println("Unconfined 1 Dispatcher ${Thread.currentThread().name}")
        delay(100L)
        println("Unconfined 2 Dispatcher ${Thread.currentThread().name}")
      }

      launch(Dispatchers.Default) {
        println("Default Dispatcher ${Thread.currentThread().name}")
      }

      launch(Dispatchers.IO) {
        println("IO Dispatcher ${Thread.currentThread().name}")
      }

      launch(newSingleThreadContext("MyThread")) {
        println("Single Thread Dispatcher ${Thread.currentThread().name}")
      }

      println("Main Thread ${Thread.currentThread().name}")

    }
}