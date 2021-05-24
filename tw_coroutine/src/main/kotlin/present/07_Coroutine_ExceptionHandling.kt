package present

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    exceptionHandlingInCoroutine()
}

fun exceptionHandlingInCoroutine() {

    runBlocking {

        val handleException =
            CoroutineExceptionHandler { _, throwable -> println("Handler " + throwable.localizedMessage) }

        val job = GlobalScope.launch(handleException) { throw RuntimeException("Exception Message") }

        job.join()
    }
}