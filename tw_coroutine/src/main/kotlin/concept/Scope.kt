import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
  println("Program Execution Now Block")
  runBlocking {
    launch {
      delay(1000L)
      println("Task from RunBlocking")
    }

    GlobalScope.launch {
      delay(500L)
      println("Task from Global Scope")
    }

    coroutineScope {
      launch {
        delay(2000L)
        println("Task from CoroutineScope")
      }
    }
  }
  println("Program Execution Now Continues")
}
