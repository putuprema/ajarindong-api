package xyz.ajarindong.api.dto.mapper

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.linkTo
import xyz.ajarindong.api.controller.v1.StudentController
import xyz.ajarindong.api.dto.StudentDto
import xyz.ajarindong.api.model.Student

object StudentMapper {
    fun toStudentDto(stud: Student): StudentDto {
        val dto = StudentDto()
                .email(stud.email)
                .name(stud.name)
                .dateOfBirth(stud.dateOfBirth.toString())
                .level(stud.level)
                .experience(stud.experience)
                .id(stud.id)
                .createdAt(stud.createdAt.toString())
                .updatedAt(stud.updatedAt.toString())
        return dto
    }

    fun toMinimalStudentDto(stud: Student): StudentDto {
        val dto = StudentDto()
                .email(stud.email)
                .name(stud.name)
                .level(stud.level)
                .id(stud.id)
                .createdAt(stud.createdAt.toString())
        return dto
    }
}