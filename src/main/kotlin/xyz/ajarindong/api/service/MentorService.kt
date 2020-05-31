package xyz.ajarindong.api.service

import xyz.ajarindong.api.dto.MentorDto
import xyz.ajarindong.api.dto.form.MentorProfileUpdateDto
import xyz.ajarindong.api.dto.form.MentorRegistrationDto
import xyz.ajarindong.api.model.Mentor

interface MentorService {
    fun findById(id: String): Mentor
    fun getMentors(): List<MentorDto>
    fun getDetails(id: String): MentorDto
    fun editProfile(id: String, form: MentorProfileUpdateDto): MentorDto
    fun register(form: MentorRegistrationDto): MentorDto
}