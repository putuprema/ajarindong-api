package xyz.ajarindong.api.service

import xyz.ajarindong.studentservice.dto.StudentDto
import xyz.ajarindong.studentservice.dto.StudentProfileUpdateDto
import xyz.ajarindong.studentservice.dto.StudentRegistrationDto

interface StudentService {
    fun getStudents(): List<StudentDto>
    fun getProfile(id: String): StudentDto
    fun editProfile(id: String, form: StudentProfileUpdateDto): StudentDto
    fun register(form: StudentRegistrationDto): StudentDto
}