package xyz.ajarindong.api.dto

import xyz.ajarindong.api.model.CourseEnrollmentPayment

class CourseEnrollmentPaymentDto {
    var id: String? = null
    var status: CourseEnrollmentPayment.Status? = null
    var paymentMethod: PaymentMethodDto? = null
    var paymentNumber: String? = null
    var validUntil: String? = null
}