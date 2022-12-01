package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WelcomeServiceImpl @Autowired constructor(
    private val _users: UserRepository
) : WelcomeService {
    override val users get() = _users.findAll().map { it.name }.toSet()

    override fun greet(name: String) = name.replaceFirstChar(Char::uppercaseChar).let {
        _users.save(User(it))
        "Hello, $it"
    }
}