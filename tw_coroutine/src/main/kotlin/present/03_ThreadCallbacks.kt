package present

import kotlin.concurrent.thread

fun mainCallback() {
    Thread.currentThread().name = "Main Thread"

    println("Main Start Thead Name - ${Thread.currentThread().name}")

    thread { fetchUser { saveUserToDatabase { emitSuccessMessage() } } }
    thread { fetchUser { saveUserToDatabase { emitSuccessMessage() } } }

    println("Main End Thead Name - ${Thread.currentThread().name}")
}


private fun fetchUser(callback: () -> Unit) {
    Thread.sleep(2000)
    println("User Fetched - ${Thread.currentThread().name}")
    callback()
}

private fun saveUserToDatabase(callback: () -> Unit) {
    Thread.sleep(4000)
    println("User Saved in Database - ${Thread.currentThread().name}")
    callback()
}

private fun emitSuccessMessage() {
    Thread.sleep(4000)
    println("Emitting Success Message - ${Thread.currentThread().name}")
}