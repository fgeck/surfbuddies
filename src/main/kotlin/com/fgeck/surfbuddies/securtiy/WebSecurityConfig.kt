package com.fgeck.surfbuddies.config


import com.fgeck.surfbuddies.securtiy.JwtAuthFilter
import com.fgeck.surfbuddies.services.UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class WebConfig(
    private val securityConfig: SecurityConfig,
    private val userDetailsService: UserDetailsService,
    private val jwtAuthFilter: JwtAuthFilter,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder

) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no sessions
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/users").authenticated()
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder)
        return authenticationProvider
    }

//    @Bean
//    @Throws(Exception::class)
//    fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
//        authenticationManagerBuilder.userDetailsService(userDetailsService)
//            .passwordEncoder(securityConfig.bCryptPasswordEncoder())
//    }

//    @Bean
//    @Throws(Exception::class)
//    fun authenticationManager(
//        http: HttpSecurity
//    ): AuthenticationManager {
//        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
//            .userDetailsService(this.userDetailsService)
//            .passwordEncoder(SecurityConfig().bCryptPasswordEncoder())
//            .and()
//            .build()
//    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource = UrlBasedCorsConfigurationSource().also { cors ->
        CorsConfiguration().apply {
            allowedOrigins = listOf("*")
            allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
            allowedHeaders = listOf(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
            )
            exposedHeaders = listOf(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials",
                "Authorization",
                "Content-Disposition"
            )
            maxAge = 3600
            cors.registerCorsConfiguration("/**", this)
        }
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return CustomWebMvcConfigurer {

            @Override
            fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedMethods(
                    HttpMethod.POST.toString(),
                    HttpMethod.DELETE.toString(),
                    HttpMethod.GET.toString(),
                    HttpMethod.PUT.toString()
                )
            }
        }
    }
}

class CustomWebMvcConfigurer(function: () -> Unit) : WebMvcConfigurer
