package xyz.ajarindong.api.service

import org.springframework.http.ResponseEntity
import xyz.ajarindong.api.dto.PaymentMethodDto

interface PaymentMethodService {
    fun getPaymentMethods(): List<PaymentMethodDto>
    fun getIcon(id: String): ResponseEntity<ByteArray>
}