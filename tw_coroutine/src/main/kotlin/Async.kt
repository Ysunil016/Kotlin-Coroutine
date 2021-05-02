import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
  runBlocking {
    val xFun = async { getX() }
    val yFun = async { getY() }

    println("Doing Some Processing")
    delay(500L)
    println("Waiting for Values")

    val total = xFun.await() + yFun.await()
    println("Total is $total")
  }
}

suspend fun getX(): Int{
  delay(4000L)
  val value = Random.nextInt(100)
  println("Return Found Value for X = $value")
  return value
}

suspend fun getY(): Int{
  delay(5000L)
  val value = Random.nextInt(1000)
  println("Return Found Value for Y = $value")
  return value
}