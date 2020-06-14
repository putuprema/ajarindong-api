package xyz.ajarindong.api.dto.mapper

import xyz.ajarindong.api.dto.CourseDto
import xyz.ajarindong.api.model.Course

object CourseMapper {
    fun toCourseDto(c: Course): CourseDto {
        val dto = CourseDto()
        dto.category = CourseCategoryMapper.toMinimalCourseCategoryDto(c.category)
        dto.name = c.name
        dto.description = c.description
        dto.price = c.price
        dto.backgroundImgUrl = c.backgroundImgUrl
        dto.id = c.id
        dto.benefits = c.benefits
        dto.mentors = c.mentors?.map { m -> MentorMapper.toMinimalMentorDto(m) }
        return dto
    }

    fun toMinimalCourseDto(c: Course): CourseDto {
        val dto = CourseDto()
        dto.category = CourseCategoryMapper.toMinimalCourseCategoryDto(c.category)
        dto.name = c.name
        dto.price = c.price
        dto.id = c.id
        dto.backgroundImgUrl = c.backgroundImgUrl
        return dto
    }
}