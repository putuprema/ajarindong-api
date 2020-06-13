package xyz.ajarindong.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.ajarindong.api.model.CourseCategory

interface CourseCategoryRepository : JpaRepository<CourseCategory, String> {
}