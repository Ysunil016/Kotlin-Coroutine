import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {

  runBlocking {
    launch(Dispatchers.Default) {
      println("Inside First Dispatcher $coroutineContext")
      withContext(Dispatchers.IO){
        println("Inside Second Dispatcher $coroutineContext")
      }
      println("Inside Third Dispatcher $coroutineContext")
    }
  }

}