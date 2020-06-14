package xyz.ajarindong.api.dto.form

import javax.validation.constraints.NotEmpty

class CourseEnrollmentForm(
        @field:NotEmpty(message = "Course id tidak boleh kosong")
        var courseId: String? = null,
        @field:NotEmpty(message = "Mentor id tidak boleh kosong")
        var mentorId: String? = null,
        @field:NotEmpty(message = "Payment method id tidak boleh kosong")
        var paymentMethodId: String? = null
)