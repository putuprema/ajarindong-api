package xyz.ajarindong.api.repository.course

import org.springframework.data.jpa.repository.JpaRepository
import xyz.ajarindong.api.model.Course
import xyz.ajarindong.api.model.CourseCategory

interface CourseRepository : JpaRepository<Course, String> {
    fun findAllByCategory(c: CourseCategory): List<Course>
    fun findAllByNameLike(name: String): List<Course>
}