//package com.fgeck.surfbuddies.config
//
//
//import com.fgeck.surfbuddies.securtiy.JwtAuthenticationFilter
//import com.fgeck.surfbuddies.securtiy.JwtAuthorizationFilter
//
//import com.fgeck.surfbuddies.services.AppAuthenticationManager
//import com.fgeck.surfbuddies.securtiy.JwtTokenProvider
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.web.cors.CorsConfiguration
//import org.springframework.web.cors.CorsConfigurationSource
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource
//
//@Configuration
//class WebConfig(
//    val securityConfig: SecurityConfig,
//    val authenticationManager: AppAuthenticationManager,
//    val tokenProvider: JwtTokenProvider
//) {
//    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
//        return http
//            .cors()
//            .and()
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no sessions
//            .and()
//            .authorizeRequests()
////            .antMatchers("/api/**").permitAll()
////            .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
////            .antMatchers(HttpMethod.POST, "/login").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .addFilter(JwtAuthenticationFilter(authenticationManager, securityConfig, tokenProvider))
//            .addFilter(JwtAuthorizationFilter(authenticationManager, securityConfig, tokenProvider))
//            .build()
//    }
//
//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource = UrlBasedCorsConfigurationSource().also { cors ->
//        CorsConfiguration().apply {
//            allowedOrigins = listOf("*")
//            allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
//            allowedHeaders = listOf(
//                "Authorization",
//                "Content-Type",
//                "X-Requested-With",
//                "Accept",
//                "Origin",
//                "Access-Control-Request-Method",
//                "Access-Control-Request-Headers"
//            )
//            exposedHeaders = listOf(
//                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Authorization", "Content-Disposition"
//            )
//            maxAge = 3600
//            cors.registerCorsConfiguration("/**", this)
//        }
//    }
//
////    @Bean
////    fun corsConfigurer(): WebMvcConfigurer {
////        return WebMvcConfigurer() {
////
////            @Override
////            fun addCorsMappings(registry: CorsRegistry) {
////                registry.addMapping("/**").allowedMethods(HttpMethod.POST.toString(), HttpMethod.DELETE.toString(), HttpMethod.GET.toString(), HttpMethod.PUT.toString())
////            }
////        }
////    }
//}