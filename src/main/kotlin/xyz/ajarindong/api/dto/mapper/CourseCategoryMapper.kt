package xyz.ajarindong.api.dto.mapper

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.linkTo
import xyz.ajarindong.api.controller.v1.StudentController
import xyz.ajarindong.api.dto.CourseCategoryDto
import xyz.ajarindong.api.model.CourseCategory

object CourseCategoryMapper {
    fun toCourseCategoryDto(c: CourseCategory): CourseCategoryDto {
        val dto = CourseCategoryDto()
        dto.name = c.name
        dto.color = c.color
        dto.id = c.id
        dto.createdAt = c.createdAt.toString()
        dto.updatedAt = c.updatedAt.toString()
        return dto
    }

    fun toMinimalCourseCategoryDto(c: CourseCategory): CourseCategoryDto {
        val dto = CourseCategoryDto()
        dto.name = c.name
        dto.color = c.color
        dto.id = c.id
        return dto
    }
}