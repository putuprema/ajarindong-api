package xyz.ajarindong.api.config.security

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class ApiStudentAuthenticationProvider(
        private val passwordEncoder: PasswordEncoder,
        private val studentDetailsService: StudentDetailsService
) : AbstractUserDetailsAuthenticationProvider() {
    override fun retrieveUser(s: String, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken): UserDetails {
        return studentDetailsService.loadByEmail(s)
    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken) {
        val presentedPassword = usernamePasswordAuthenticationToken.credentials.toString()
        if (!passwordEncoder.matches(presentedPassword, userDetails.password)) {
            throw BadCredentialsException("Bad Credentials")
        }
    }
}