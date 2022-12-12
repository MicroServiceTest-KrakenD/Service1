package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class WelcomeServiceTest @Autowired constructor(
    private val service: WelcomeService
) {

    @Test
    fun getUsers() {
        val list = listOf("TestName1", "TesT NaMe 2", "3")
        list.forEach { service.greet(it) }
        assertThat(service.users).containsAll(list)
    }

    @Test
    fun greet() {
        val name = "testName"
        assertEquals(service.greet(name), "Hello, TestName")
    }
}