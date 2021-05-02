import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {
    launch(CoroutineName("sunilCoroutine")) {
      println("This is Running from ${coroutineContext[CoroutineName.Key]}")
    }
  }
}
