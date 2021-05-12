import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
      println("Exception Handled ${throwable.localizedMessage}")
    }

    val job = GlobalScope.launch(exceptionHandler) {
      println("Throwing Exception from Job")
      throw IndexOutOfBoundsException("Ex in Coroutine")
    }
    job.join()

    val def = GlobalScope.async {
      println("Throwing Ex from ASync")
      throw ArithmeticException("Arithmetic Exception")
    }

    try{ def.await() }
    catch (e:ArithmeticException){ print("Exception : ${e.localizedMessage}") }
  }

}