package xyz.ajarindong.api.config.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import xyz.ajarindong.api.repository.StudentRepository

@Service
class StudentDetailsService(
        private val studentRepository: StudentRepository
) : UserDetailsService {
    override fun loadByEmail(email: String): UserDetails {
        val s = studentRepository.findByEmail(email).orElseThrow { UsernameNotFoundException("Bad Credentials") }
        return StudentDetails.build(s)
    }
}