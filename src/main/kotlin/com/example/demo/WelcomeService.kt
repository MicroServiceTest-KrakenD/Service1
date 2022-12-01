package com.example.demo

interface WelcomeService {
    val users: Set<String>
    fun greet(name: String): String
}

