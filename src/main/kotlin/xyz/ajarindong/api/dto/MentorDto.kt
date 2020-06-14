package xyz.ajarindong.api.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel

@JsonInclude(JsonInclude.Include.NON_NULL)
class MentorDto {
    var email: String? = null
    var name: String? = null
    var dateOfBirth: String? = null
    var job: String? = null
    var bio: String? = null
    var rating: Double? = null
    var id: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}