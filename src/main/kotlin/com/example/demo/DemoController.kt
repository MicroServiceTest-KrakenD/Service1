package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("api")
class DemoController {
    @GetMapping("/")
    fun hello(@RequestParam(required = false) name: String = "World")
        = "Hello, $name"
}