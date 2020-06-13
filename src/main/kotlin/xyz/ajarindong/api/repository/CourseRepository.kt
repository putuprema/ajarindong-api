package xyz.ajarindong.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.ajarindong.api.model.Course

interface CourseRepository : JpaRepository<Course, String> {
}