package other

fun main() {
    postItem("Item")
}

fun postItem(item: String) {
    println("Inside Item Method")
    preparePostAsync { token ->
        submitPostAsync(token, item) { post ->
            processPost(post)
        }
    }
}

fun submitPostAsync(token: String, item: String, method: (String) -> Unit) {
    method("Submit Post - Callback")
}

fun preparePostAsync(callback: (String) -> Unit) {
    callback("Prepare Post - Callback")
}

fun processPost(post: String) {
    println("Post $post")
}