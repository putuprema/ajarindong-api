package xyz.ajarindong.api.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import xyz.ajarindong.api.model.CourseSubject

@JsonInclude(JsonInclude.Include.NON_NULL)
class CourseDto {
    var category: CourseCategoryDto? = null
    var name: String? = null
    var description: String? = null
    var price: Long? = null
    var backgroundImgUrl: String? = null
    var id: String? = null
    var benefits: List<String>? = null
    var mentors: List<MentorDto>? = null
//    var subjects: List<CourseSubject>? = null
}