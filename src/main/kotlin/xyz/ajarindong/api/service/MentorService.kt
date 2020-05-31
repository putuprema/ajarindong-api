package xyz.ajarindong.api.service

import xyz.ajarindong.api.model.Mentor
import xyz.ajarindong.studentservice.dto.MentorDto
import xyz.ajarindong.studentservice.dto.MentorProfileUpdateDto
import xyz.ajarindong.studentservice.dto.MentorRegistrationDto

interface MentorService {
    fun findById(id: String): Mentor
    fun getMentors(): List<MentorDto>
    fun getDetails(id: String): MentorDto
    fun editProfile(id: String, form: MentorProfileUpdateDto): MentorDto
    fun register(form: MentorRegistrationDto): MentorDto
}