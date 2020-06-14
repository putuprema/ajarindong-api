package xyz.ajarindong.api.dto.mapper

import xyz.ajarindong.api.dto.CourseSubjectDto
import xyz.ajarindong.api.model.CourseSubject

object CourseSubjectMapper {
    fun toCourseSubjectDto(c: CourseSubject): CourseSubjectDto {
        val dto = CourseSubjectDto()
        dto.number = c.number
        dto.title = c.title
        dto.description = c.description
        dto.color = c.color
        return dto
    }
}