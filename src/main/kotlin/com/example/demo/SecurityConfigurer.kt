package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(prefix = "rest.security", value = ["enabled"], havingValue = "true")
@Import(SecurityProperties::class)
class SecurityConfigurer @Autowired constructor(
    private val securityProperties: SecurityProperties,
    private val resourceServerProperties: ResourceServerProperties,
) : ResourceServerConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId(resourceServerProperties.resourceId)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors()
            .configurationSource(corsConfigurationSource())
            .and()
            .headers()
            .frameOptions()
            .disable()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(securityProperties.apiMatcher)
            .authenticated()
    }

    @Bean
    fun corsConfigurationSource() = UrlBasedCorsConfigurationSource().also {
        it.registerCorsConfiguration("/**", securityProperties.corsConfiguration)
    }


    @Bean
    fun jwtAccessTokenCustomizer(mapper: ObjectMapper?) = JwtAccessTokenCustomizer(mapper!!)
}