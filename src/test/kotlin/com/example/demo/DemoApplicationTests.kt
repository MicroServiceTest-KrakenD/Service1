package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests @Autowired constructor(
    private val repository: UserRepository?,
    private val service: WelcomeService?,
    private val controller: DemoController?,
    private val mockMvc: MockMvc,
) {
    @Test
    fun contextLoads() {
        assertThat(repository).isNotNull
        assertThat(service).isNotNull
        assertThat(controller).isNotNull
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnDefaultMessage() {
        mockMvc.perform(get("/api/")).andDo(print()).andExpect(status().isOk)
            .andExpect(content().string(containsString("Hello, World")))
    }
}
