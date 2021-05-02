package thoughtworks

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit

fun makeApiRequestWithTime(url: String, time: Long): String? {
    val client = OkHttpClient()
        .newBuilder()
        .readTimeout(5, TimeUnit.MINUTES)
        .connectTimeout(5, TimeUnit.MINUTES)
        .build()

    val request = Request.Builder().url("$url/$time").build()

    val response = client.newCall(request).execute()

    return parseResponse(response)?.string()
}


fun makeApiRequest(url: String): String? {
    val client = OkHttpClient()
        .newBuilder()
        .readTimeout(5, TimeUnit.MINUTES)
        .connectTimeout(5, TimeUnit.MINUTES)
        .build()

    val request = Request.Builder().url(url).build()

    val response = client.newCall(request).execute()

    return  parseResponse(response)?.string()
}

fun parseResponse(response: Response): ResponseBody? {
    return response.body()
}