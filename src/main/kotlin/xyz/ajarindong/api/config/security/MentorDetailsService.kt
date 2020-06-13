package xyz.ajarindong.api.config.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import xyz.ajarindong.api.repository.MentorRepository

@Service
class MentorDetailsService(
        private val mentorRepository: MentorRepository
) : UserDetailsService {
    override fun loadByEmail(email: String): UserDetails {
        val s = mentorRepository.findByEmail(email).orElseThrow { UsernameNotFoundException("Bad Credentials") }
        return MentorDetails.build(s)
    }
}