package xyz.ajarindong.api.dto.mapper

import xyz.ajarindong.api.dto.StudentDto
import xyz.ajarindong.api.model.Student

object StudentMapper {
    fun toStudentDto(stud: Student): StudentDto = StudentDto()
            .email(stud.email)
            .name(stud.name)
            .dateOfBirth(stud.dateOfBirth.toString())
            .level(stud.level)
            .experience(stud.experience)
            .id(stud.id)
            .createdAt(stud.createdAt.toString())
            .updatedAt(stud.updatedAt.toString())

    fun toMinimalStudentDto(stud: Student): StudentDto = StudentDto()
            .email(stud.email)
            .name(stud.name)
            .level(stud.level)
            .id(stud.id)
            .createdAt(stud.createdAt.toString())
}