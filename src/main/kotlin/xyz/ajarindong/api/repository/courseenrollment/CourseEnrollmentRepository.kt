package xyz.ajarindong.api.repository.courseenrollment

import org.springframework.data.jpa.repository.JpaRepository
import xyz.ajarindong.api.model.CourseEnrollment
import xyz.ajarindong.api.model.Student

interface CourseEnrollmentRepository : JpaRepository<CourseEnrollment, String>, CourseEnrollmentRepositoryCustom {
    fun getAllByStudent(s: Student): List<CourseEnrollment>
}