package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


@Component
object SecurityContextUtils {
    private val LOGGER = LoggerFactory.getLogger(SecurityContextUtils::class.java)
    private const val ANONYMOUS = "anonymous"
    val userName: String
        get() {
            val securityContext = SecurityContextHolder.getContext()
            val authentication = securityContext.authentication
            var username = ANONYMOUS
            authentication?.let {
                when (it.principal) {
                    is UserDetails -> {
                        username = (it.principal as UserDetails).username
                    }
                    is String -> { username = it.principal as String }
                    else -> {
                        LOGGER.debug("User details not found in Security Context")
                    }
                }
            } ?: run {
                LOGGER.debug("Request not authenticated, hence no user name available")
            }
            return username
        }
    val userRoles: Set<String>
        get() {
            val securityContext = SecurityContextHolder.getContext()
            val authentication = securityContext.authentication
            val roles: MutableSet<String> = HashSet()
            authentication?.authorities?.forEach { e: GrantedAuthority? -> roles.add(e!!.authority) }
            return roles
        }
}