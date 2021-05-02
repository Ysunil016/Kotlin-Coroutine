import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  globalScope()
  lightWeight()
}

private fun lightWeight(){
  runBlocking {
    repeat(1_000_000){
      launch {
        println(".")
      }
    }
  }
}

private fun globalScope() {
  GlobalScope.launch {
    delay(2000)
    print("World !!")
  }
  print("Hello, ")
  Thread.sleep(3000)
}