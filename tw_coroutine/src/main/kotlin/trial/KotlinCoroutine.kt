package trial

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    CoroutineScope(Dispatchers.Unconfined).launch {
        val currentContext = coroutineContext;
        launch(currentContext) {
            repeat(5000) {
                println("Inside First on Thread $coroutineContext")
                delay(1000)
            }
        }
        val secondJob = launch(context = currentContext, CoroutineStart.LAZY) {
            repeat(5000) {
                println("Inside Second on Thread $coroutineContext")
                delay(1000)
            }
        }
        launch {
            repeat(5000) {
                println("Inside Third on Thread $coroutineContext")
                delay(1000)
            }
        }
        delay(2000)
        secondJob.start()
    }

    delay(5000)
}