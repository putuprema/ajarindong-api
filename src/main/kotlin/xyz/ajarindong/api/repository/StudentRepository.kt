package xyz.ajarindong.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.ajarindong.api.model.Student
import java.util.*

interface StudentRepository : JpaRepository<Student, String> {
    fun findByEmail(email: String): Optional<Student>
}