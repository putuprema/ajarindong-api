package xyz.ajarindong.api.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class CourseEnrollmentDto {
    var id: String? = null
    var student: StudentDto? = null
    var mentor: MentorDto? = null
    var course: CourseDto? = null
    var payment: CourseEnrollmentPaymentDto? = null
    var completionPercent: Int? = null
    var progress: List<CourseEnrollmentProgressDto>? = null
}