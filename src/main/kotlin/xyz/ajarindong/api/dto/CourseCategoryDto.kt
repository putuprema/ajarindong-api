package xyz.ajarindong.api.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel

@JsonInclude(JsonInclude.Include.NON_NULL)
class CourseCategoryDto : RepresentationModel<CourseCategoryDto>() {
    var name: String? = null
    var color: String? = null
    var id: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}