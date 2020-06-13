package xyz.ajarindong.api.config.security

import org.springframework.security.core.userdetails.UserDetails

interface UserDetailsService {
    fun loadByEmail(email: String): UserDetails
}