package xyz.ajarindong.api.dto.mapper

import xyz.ajarindong.api.dto.PaymentMethodDto
import xyz.ajarindong.api.model.PaymentMethod

object PaymentMethodMapper {
    fun toPaymentMethodDto(o: PaymentMethod): PaymentMethodDto {
        val dto = PaymentMethodDto()
        dto.id = o.id
        dto.name = o.name
        dto.description = o.description
        return dto
    }
}