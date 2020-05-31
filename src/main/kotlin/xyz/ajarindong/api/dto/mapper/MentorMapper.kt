package xyz.ajarindong.api.dto.mapper

import xyz.ajarindong.api.dto.MentorDto
import xyz.ajarindong.api.model.Mentor

object MentorMapper {
    fun toMentorDto(o: Mentor): MentorDto {
        val dto = MentorDto()
        dto.id = o.id
        dto.rating = o.rating
        dto.email = o.email
        dto.name = o.name
        dto.bio = o.bio
        dto.dateOfBirth = o.dateOfBirth.toString()
        dto.createdAt = o.createdAt.toString()
        dto.updatedAt = o.updatedAt.toString()
        return dto
    }

    fun toMinimalMentorDto(o: Mentor): MentorDto {
        val dto = MentorDto()
        dto.id = o.id
        dto.rating = o.rating
        dto.name = o.name
        dto.bio = o.bio
        dto.createdAt = o.createdAt.toString()
        return dto
    }
}