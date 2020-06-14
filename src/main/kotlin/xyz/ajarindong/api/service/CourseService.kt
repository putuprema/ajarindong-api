package xyz.ajarindong.api.service

import xyz.ajarindong.api.dto.CourseDto
import xyz.ajarindong.api.dto.form.CourseQueryForm

interface CourseService {
    fun getCourses(form: CourseQueryForm): List<CourseDto>
    fun getCourseDetail(id: String): CourseDto
}