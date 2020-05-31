package xyz.ajarindong.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.multipart.commons.CommonsMultipartResolver

@Configuration
class AppConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    @Primary
    fun jacksonMessageConverter(): MappingJackson2HttpMessageConverter {
        val module = Hibernate5Module().enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING)

        val mapper = ObjectMapper().registerModule(module)
        mapper.dateFormat = StdDateFormat()

        val messageConverter = MappingJackson2HttpMessageConverter()
        messageConverter.objectMapper = mapper

        return messageConverter
    }

    @Bean
    fun multipartResolver(): CommonsMultipartResolver = CommonsMultipartResolver()
}