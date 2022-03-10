package com.numpi.numpiserver.secutiry.config

import com.numpi.numpiserver.models.roles.UserRoles.*
import com.numpi.numpiserver.secutiry.jwt.JwtUserAndPasswordAuthenticationFilter
import com.numpi.numpiserver.services.AppUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig (
    private val appUserService: AppUserService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : WebSecurityConfigurerAdapter(false) {

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(JwtUserAndPasswordAuthenticationFilter(authenticationManager()))
            .authorizeRequests()
            .antMatchers("/images/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/onboard").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v*/oauth2/**").permitAll()
            .antMatchers(HttpMethod.POST,"/api/v*/user/signup").permitAll()
            .antMatchers(HttpMethod.GET,"/api/v*/user/confirm-token").permitAll()
            .antMatchers("/api/v*/user/hola").hasRole(USER.name)
            .anyRequest()
            .authenticated()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(daoAuthenticationProvider())
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(bCryptPasswordEncoder)
        provider.setUserDetailsService(appUserService)

        return provider
    }
}