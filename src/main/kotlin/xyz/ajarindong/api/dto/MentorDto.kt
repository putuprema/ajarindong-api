package xyz.ajarindong.studentservice.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel

@JsonInclude(JsonInclude.Include.NON_NULL)
class MentorDto : RepresentationModel<MentorDto>() {
    var email: String? = null
    var name: String? = null
    var dateOfBirth: String? = null
    var bio: String? = null
    var rating: Double? = null
    var id: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}