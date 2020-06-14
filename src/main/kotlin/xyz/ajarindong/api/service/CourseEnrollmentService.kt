package xyz.ajarindong.api.service

import xyz.ajarindong.api.dto.CourseEnrollmentDto
import xyz.ajarindong.api.dto.form.CourseEnrollmentForm

interface CourseEnrollmentService {
    fun enrollCourse(form: CourseEnrollmentForm): CourseEnrollmentDto
    fun getEnrolledCourse(): List<CourseEnrollmentDto>
    fun getEnrolledCourseDetail(enrollmentId: String): CourseEnrollmentDto
}