package xyz.ajarindong.api.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel

@JsonInclude(JsonInclude.Include.NON_NULL)
class StudentDto(
        var email: String? = null,
        var name: String? = null,
        var dateOfBirth: String? = null,
        var level: Int? = null,
        var experience: Long? = null,
        var id: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
) : RepresentationModel<StudentDto>() {
    fun email(s: String) = apply { email = s }
    fun name(s: String) = apply { name = s }
    fun dateOfBirth(s: String) = apply { dateOfBirth = s }
    fun level(s: Int) = apply { level = s }
    fun experience(s: Long) = apply { experience = s }
    fun id(s: String) = apply { id = s }
    fun createdAt(s: String) = apply { createdAt = s }
    fun updatedAt(s: String) = apply { updatedAt = s }
}