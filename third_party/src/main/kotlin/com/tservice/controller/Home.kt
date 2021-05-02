package com.tservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/coroutine")
class Home {

    @GetMapping("/fireForget/{time}")
    fun exec(@PathVariable time: Long = 0L): String {
        println("Fire and Forget - $time Sec Task")
        Thread.sleep(time * 1000)
        return "Exec in $time Sec"
    }

    @GetMapping("/return/{time}")
    fun execReturn(@PathVariable time: Long = 0L): String {
        Thread.sleep(time * 1000)
        println("Return Request Came for $time Sec Task")
        return "Exec in $time Sec"
    }

    @GetMapping("/getListOfElements/{limit}")
    fun getListOfElements(@PathVariable limit: Int = 10): List<UUID> {
        val listOfElements = mutableListOf<UUID>()
        repeat(limit) {
            listOfElements.add(UUID.randomUUID())
        }
        return listOfElements;
    }

    @GetMapping("/detail/{id}")
    fun getDetail(@PathVariable id: String): String {
        Thread.sleep(5000)
        return id.substring(0, 5);
    }

}


