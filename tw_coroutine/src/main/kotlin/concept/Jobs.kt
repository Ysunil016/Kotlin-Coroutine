import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {
    val jobOne = launch {
//      delay(6000L)
      println("Job 1 Launched")

      val jobTwo = launch {
        println("Job 2 Launched")
        delay(3000L)
        println("Job 2 Finishing")
      }

      val jobThree = launch {
        println("Job 3 Launched")
        delay(3000L)
        println("Job 3 Finishing")
      }

      jobTwo.invokeOnCompletion { println("Job 2 is Completed") }
      jobThree.invokeOnCompletion { println("Job 3 is Completed") }
    }

    jobOne.invokeOnCompletion { println("Job 1 is Completed") }
    delay(1000L)
    println("Canceling Job 1")
    jobOne.cancel()
  }
}