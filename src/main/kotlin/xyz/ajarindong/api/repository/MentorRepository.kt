package xyz.ajarindong.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.ajarindong.api.model.Mentor
import java.util.*

interface MentorRepository : JpaRepository<Mentor, String> {
    fun findByEmail(email: String): Optional<Mentor>
}