package xyz.ajarindong.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import xyz.ajarindong.api.config.security.ApiAccessDeniedHandler
import xyz.ajarindong.api.config.security.ApiAuthenticationEntryPoint
import xyz.ajarindong.api.config.security.ApiStudentAuthenticationProvider
import xyz.ajarindong.api.config.security.StudentJwtAuthenticationFilter

@Configuration
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApiStudentSecurityConfig(
        private val apiAuthenticationEntryPoint: ApiAuthenticationEntryPoint,
        private val jwtAuthenticationFilter: StudentJwtAuthenticationFilter,
        private val apiAccessDeniedHandler: ApiAccessDeniedHandler,
        private val apiStudentAuthenticationProvider: ApiStudentAuthenticationProvider
) : WebSecurityConfigurerAdapter() {
    @Bean(name = ["studentAuthManager"])
    @Primary
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(apiStudentAuthenticationProvider)
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
                .requestMatchers()
                .antMatchers("/v1/**")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(apiAuthenticationEntryPoint)
                .accessDeniedHandler(apiAccessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v1/student/register", "/v1/student/auth/**").permitAll()
                .anyRequest().authenticated()

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}