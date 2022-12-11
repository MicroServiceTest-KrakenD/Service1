package com.example.demo


import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration

@Configuration
@ConfigurationProperties("rest.security")
@ConstructorBinding
data class SecurityProperties(
    var enabled: Boolean = false,
    var apiMatcher: String? = null,
    var cors: Cors? = null,
    var issuerUri: String? = null,
) {
    val corsConfiguration: CorsConfiguration
        get() = CorsConfiguration().apply {
                cors?.let {
                    allowedOrigins = it.allowedOrigins
                    allowedMethods = it.allowedMethods
                    allowedHeaders = it.allowedHeaders
                    exposedHeaders = it.exposedHeaders
                    allowCredentials = it.allowCredentials
                    maxAge = it.maxAge
                }
            }

    data class Cors(
        var allowedOrigins: List<String>? = null,
        var allowedMethods: List<String>? = null,
        var allowedHeaders: List<String>? = null,
        var exposedHeaders: List<String>? = null,
        var allowCredentials: Boolean? = null,
        var maxAge: Long? = null
    )
}