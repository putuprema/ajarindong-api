package xyz.ajarindong.api.dto.mapper

import xyz.ajarindong.api.dto.CourseEnrollmentProgressDto
import xyz.ajarindong.api.model.CourseEnrollmentProgress

object CourseEnrollmentProgressMapper {
    fun toCourseEnrollmentProgressDto(c: CourseEnrollmentProgress): CourseEnrollmentProgressDto {
        val dto = CourseEnrollmentProgressDto()
        dto.id = c.id
        dto.subject = CourseSubjectMapper.toCourseSubjectDto(c.courseSubject)
        dto.completed = c.completed
        return dto
    }
}