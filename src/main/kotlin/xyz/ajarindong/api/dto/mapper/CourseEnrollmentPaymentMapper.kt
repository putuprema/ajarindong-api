package xyz.ajarindong.api.dto.mapper

import xyz.ajarindong.api.dto.CourseEnrollmentPaymentDto
import xyz.ajarindong.api.model.CourseEnrollmentPayment

object CourseEnrollmentPaymentMapper {
    fun toCourseEnrollmentPaymentDto(c: CourseEnrollmentPayment): CourseEnrollmentPaymentDto {
        val dto = CourseEnrollmentPaymentDto()
        dto.id = c.id
        dto.status = c.status
        dto.paymentMethod = PaymentMethodMapper.toPaymentMethodDto(c.paymentMethod)
        dto.paymentNumber = c.paymentNumber
        dto.validUntil = c.validUntil.toString()
        return dto
    }
}