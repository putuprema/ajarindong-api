package xyz.ajarindong.api.service

import xyz.ajarindong.api.dto.StudentDto
import xyz.ajarindong.api.dto.form.StudentProfileUpdateDto
import xyz.ajarindong.api.dto.form.StudentRegistrationDto

interface StudentService {
    fun getStudents(): List<StudentDto>
    fun getProfile(id: String): StudentDto
    fun editProfile(id: String, form: StudentProfileUpdateDto): StudentDto
    fun register(form: StudentRegistrationDto): StudentDto
}