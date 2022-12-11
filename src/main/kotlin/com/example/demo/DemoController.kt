package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("api")
class DemoController @Autowired constructor(
    private val service: WelcomeService
) {
    @GetMapping("")
    fun hello(@RequestParam(required = false, defaultValue = "World") name: String)
        = service.greet(name)

    @GetMapping("list")
    fun list() = service.users

    @GetMapping("health") fun health() {}

    @GetMapping(path = ["/whoami"])
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    fun getAuthorizedUserName() = UserResponse(
        name=SecurityContextUtils.userName,
        role=SecurityContextUtils.userRoles.toList(),
    )

    data class UserResponse(
        val name: String,
        val role: List<String>,
    )
}
